package com.shasly.common.bean;

public class UserActivate {
    private String username ;
    private String email ;
    private String activation_code ;

    public UserActivate() {

    }

    public UserActivate(String username, String email, String activation_code) {
        this.username = username;
        this.email = email;
        this.activation_code = activation_code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    @Override
    public String toString() {
        return "UserActivate{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", activation_code='" + activation_code + '\'' +
                '}';
    }
}
