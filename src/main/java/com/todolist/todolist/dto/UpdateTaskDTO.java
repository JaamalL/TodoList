package com.todolist.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateTaskDTO {
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blanked")
    @Size(max = 64, message = "Title size must be less than 64 characters")
    private String title;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blanked")
    private String description;

    @NotNull(message = "Completed cannot be null")
    private Boolean completed;

    protected UpdateTaskDTO() {
    }

    public UpdateTaskDTO(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
