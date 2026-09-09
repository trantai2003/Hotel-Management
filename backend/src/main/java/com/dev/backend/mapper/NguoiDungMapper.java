package com.dev.backend.mapper;

import com.dev.backend.dto.request.RegisterRequest;
import com.dev.backend.dto.response.NguoiDungResponse;
import com.dev.backend.entity.NguoiDung;
import com.dev.backend.entity.NguoiDungVaiTro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * unmappedTargetPolicy = IGNORE de khoi phai liet ke @Mapping(ignore) cho
 * hang chuc truong ky thuat cua entity (status, createdAt, cac quan he...).
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NguoiDungMapper {

    @Mapping(source = "vaiTros", target = "roles", qualifiedByName = "mapRoles")
    NguoiDungResponse toResponse(NguoiDung nguoiDung);

    /** Chi map email, fullName, phone. passwordHash va status do service tu set. */
    NguoiDung toEntity(RegisterRequest request);

    @Named("mapRoles")
    default List<String> mapRoles(List<NguoiDungVaiTro> vaiTros) {
        if (vaiTros == null) {
            return List.of();
        }
        return vaiTros.stream()
                .map(ndvt -> ndvt.getVaiTro().getCode())
                .toList();
    }
}
