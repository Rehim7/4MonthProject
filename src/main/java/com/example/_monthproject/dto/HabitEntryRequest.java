package com.example._monthproject.dto;

import com.example._monthproject.model.Habits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class HabitEntryRequest {

    @NotBlank(message = "HabitId cant be empty")
    private Habits habitId;
    @NotNull(message = "Date cant be null")
    private LocalDate date;



}
