package com.whoopstack.devis.userAuth.auth.dto;

public class AuthResponse {
    private Boolean success;
    private String message;
    private Long userId;
    private String email;

    public AuthResponse() {

    }

    public AuthResponse(Boolean success, String message, Long userId, String email) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.email = email;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
