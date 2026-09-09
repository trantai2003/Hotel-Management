package com.dev.backend.controller;

import com.dev.backend.dto.request.LoginRequest;
import com.dev.backend.dto.request.RegisterRequest;
import com.dev.backend.dto.response.AuthResponse;
import com.dev.backend.dto.response.NguoiDungResponse;
import com.dev.backend.mapper.NguoiDungMapper;
import com.dev.backend.repository.NguoiDungRepository;
import com.dev.backend.security.CustomUserDetails;
import com.dev.backend.service.entities.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Đăng ký, đăng nhập, thông tin tài khoản")
public class AuthController {

    private final AuthService authService;
    private final NguoiDungRepository nguoiDungRepository;
    private final NguoiDungMapper nguoiDungMapper;

    @PostMapping("/register")
    @Operation(summary = "Đăng ký tài khoản khách hàng")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Đăng nhập, trả về access token và refresh token")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    @Operation(summary = "Thông tin tài khoản đang đăng nhập (cần Bearer token)")
    public ResponseEntity<NguoiDungResponse> me(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return nguoiDungRepository.findByEmail(userDetails.getUsername()).map(nguoiDungMapper::toResponse).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
