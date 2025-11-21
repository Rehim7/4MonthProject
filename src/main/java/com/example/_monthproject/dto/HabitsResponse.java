package com.example._monthproject.dto;

import com.example._monthproject.model.User;
import com.example._monthproject.myenum.Frequency;
import lombok.Data;

@Data
public class HabitsResponse {
    private Long id;
    private String habitName;
    private String habitDescription;
    private User user;
    private Frequency frequency;
    private Integer currentStreak = 0;
    private Integer longestStreak = 0;

}
