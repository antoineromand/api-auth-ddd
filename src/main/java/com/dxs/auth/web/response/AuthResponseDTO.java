package com.dxs.auth.web.response;

public class AuthResponseDTO {
    private String message;
    private Integer status;

    public AuthResponseDTO(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public AuthResponseDTO() {
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
