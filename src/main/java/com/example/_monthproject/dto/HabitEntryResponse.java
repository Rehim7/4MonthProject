package com.example._monthproject.dto;

import com.example._monthproject.model.Habits;
import lombok.Data;

import java.time.LocalDate;
@Data
public class HabitEntryResponse {
    private Long id;
    private Habits habitId;
    private LocalDate date;
    private boolean isCompleted;


}
