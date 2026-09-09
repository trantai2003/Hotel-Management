package com.dev.backend.service.entities;

import com.dev.backend.constant.enums.UserStatus;
import com.dev.backend.dto.request.LoginRequest;
import com.dev.backend.dto.request.RegisterRequest;
import com.dev.backend.dto.response.AuthResponse;
import com.dev.backend.dto.response.NguoiDungResponse;
import com.dev.backend.entity.NguoiDung;
import com.dev.backend.entity.NguoiDungVaiTro;
import com.dev.backend.entity.NguoiDungVaiTroId;
import com.dev.backend.entity.VaiTro;
import com.dev.backend.mapper.NguoiDungMapper;
import com.dev.backend.repository.NguoiDungRepository;
import com.dev.backend.mapper.VaiTroRepository;
import com.dev.backend.security.CustomUserDetails;
import com.dev.backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private static final String DEFAULT_ROLE = "GUEST";

    private final NguoiDungRepository nguoiDungRepository;
    private final VaiTroRepository vaiTroRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final NguoiDungMapper nguoiDungMapper;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (nguoiDungRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }

        VaiTro vaiTroGuest = vaiTroRepository.findByCode(DEFAULT_ROLE)
                .orElseThrow(() -> new IllegalStateException(
                        "Chưa có vai trò " + DEFAULT_ROLE + " trong bảng vai_tro"));

        NguoiDung nguoiDung = nguoiDungMapper.toEntity(request);
        nguoiDung.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        // Chua co luong xac thuc email nen kich hoat luon.
        // Khi lam UC gui mail xac thuc thi doi lai PENDING_VERIFICATION.
        nguoiDung.setStatus(UserStatus.ACTIVE);
        nguoiDung.setVaiTros(new ArrayList<>());

        nguoiDung = nguoiDungRepository.save(nguoiDung);

        NguoiDungVaiTro lienKet = NguoiDungVaiTro.builder()
                .id(new NguoiDungVaiTroId(nguoiDung.getId(), vaiTroGuest.getId()))
                .nguoiDung(nguoiDung)
                .vaiTro(vaiTroGuest)
                .assignedAt(LocalDateTime.now())
                .build();
        nguoiDung.getVaiTros().add(lienKet);
        nguoiDungRepository.save(nguoiDung);

        log.info("Đăng ký tài khoản mới: {}", nguoiDung.getEmail());
        return buildAuthResponse(CustomUserDetails.build(nguoiDung), nguoiDung);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Email hoặc mật khẩu không đúng");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy người dùng"));
        nguoiDung.setLastLoginAt(LocalDateTime.now());

        return buildAuthResponse(userDetails, nguoiDung);
    }

    private AuthResponse buildAuthResponse(CustomUserDetails userDetails, NguoiDung nguoiDung) {
        NguoiDungResponse userResponse = nguoiDungMapper.toResponse(nguoiDung);
        return AuthResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(userDetails))
                .refreshToken(jwtTokenProvider.generateRefreshToken(userDetails))
                .user(userResponse)
                .build();
    }
}
