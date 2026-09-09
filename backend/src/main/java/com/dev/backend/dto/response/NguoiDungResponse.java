package com.dev.backend.dto.response;

import com.dev.backend.constant.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NguoiDungResponse {
    private String id;
    private String email;
    private String fullName;
    private String phone;
    private UserStatus status;
    private List<String> roles;
}
