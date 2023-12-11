package com.forum.auth.web.dto;

public class UserCredentialsDTO {
    private String email;

    private String password;

    public UserCredentialsDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
