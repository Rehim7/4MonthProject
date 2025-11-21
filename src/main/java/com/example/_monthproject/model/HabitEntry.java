package com.example._monthproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "habit_entries")
public class HabitEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habits habitId;
    private LocalDate date;
    private boolean isCompleted;


}
