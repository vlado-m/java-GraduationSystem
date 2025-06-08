package org.informatics.graduation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}