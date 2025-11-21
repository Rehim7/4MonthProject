package com.example._monthproject.dto;

import com.example._monthproject.model.Habits;
import lombok.Data;

import java.util.List;
@Data
public class UserResponse {
    private String name;
    private String email;
    private List<Habits> habits;



}
