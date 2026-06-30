package com.whoopstack.devis.userAuth.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoopstack.devis.userAuth.auth.dto.AuthResponse;
import com.whoopstack.devis.userAuth.auth.dto.ForgotPasswordRequest;
import com.whoopstack.devis.userAuth.auth.dto.LoginRequest;
import com.whoopstack.devis.userAuth.auth.dto.RegisterRequest;
import com.whoopstack.devis.userAuth.auth.dto.ResetPasswordRequest;
import com.whoopstack.devis.userAuth.auth.dto.UpdatePasswordRequest;
import com.whoopstack.devis.userAuth.passwordReset.PasswordResetService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    public AuthController(AuthService authService, PasswordResetService passwordResetService) {
        this.authService = authService;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @DeleteMapping("/user/{id}")
    public AuthResponse deleteUser(@PathVariable Long id) {
        return authService.deleteUser(id);
    }

    @PutMapping("/user/{id}/email")
    public AuthResponse updateUserEmail(@PathVariable Long id, @RequestBody RegisterRequest request) {
        return authService.updateUserEmail(id, request.getEmail());
    }

    @PutMapping("/user/{id}/password")
    public AuthResponse updateNewPassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequest request) {
        return authService.updateNewPassword(id, request);
    }

    @PostMapping("/forgot-password")
    public AuthResponse forgotpassword(@RequestBody ForgotPasswordRequest request) {

        return passwordResetService.requestPasswordReset(request);
    }

    @PostMapping("/reset-password")
    public AuthResponse resetPassword(@RequestBody ResetPasswordRequest request) {
        return passwordResetService.resetPassword(request);
    }
}
