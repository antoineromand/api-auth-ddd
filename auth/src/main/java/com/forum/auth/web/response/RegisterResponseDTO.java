package com.forum.auth.web.response;

public class RegisterResponseDTO {
    private String message;
    private Integer status;

    public RegisterResponseDTO(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public RegisterResponseDTO() {
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
