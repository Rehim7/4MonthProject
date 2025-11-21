package com.example._monthproject.dto;

import com.example._monthproject.model.User;
import com.example._monthproject.myenum.Frequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HabitsRequest {


    @NotNull
    private Frequency frequency;
    @NotBlank
    @Size(min=1, max=30)
    private String habitName;
    @NotBlank
    @Size(min=1, max=30)
    private String habitDescription;

    @NotBlank
    private User user;

}
