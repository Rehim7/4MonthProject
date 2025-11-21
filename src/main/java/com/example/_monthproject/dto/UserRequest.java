package com.example._monthproject.dto;

import com.example._monthproject.model.Habits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Data
public class UserRequest {

    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
    @Email(message = "Email should be in right form")
    private String email;
    @NotNull
    @Size(min = 1, max = 30)
    private List<Habits> habits;

}
