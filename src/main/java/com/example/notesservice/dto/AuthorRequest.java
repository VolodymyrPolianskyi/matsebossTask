package com.example.notesservice.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorRequest {
    @NotBlank(message = "Name is required")
    private String name;

    public AuthorRequest() {}

    public AuthorRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
