package com.todolist.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateTaskDTO {
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blanked")
    @Size(max = 64, message = "Title size must be less than 64 characters")
    private String title;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blanked")
    private String description;

    protected CreateTaskDTO() {
    }

    public CreateTaskDTO(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
