package com.dev.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

/**
 * Lop cha cho moi entity: khoa chinh CHAR(36) sinh bang UUID.
 * equals/hashCode dua tren id va unwrap proxy Hibernate de an toan khi lazy loading.
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private String id;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> thisClass = this instanceof HibernateProxy hp
                ? hp.getHibernateLazyInitializer().getPersistentClass() : getClass();
        Class<?> otherClass = o instanceof HibernateProxy hp
                ? hp.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        if (!thisClass.equals(otherClass)) return false;
        BaseEntity other = (BaseEntity) o;
        return id != null && Objects.equals(id, other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
