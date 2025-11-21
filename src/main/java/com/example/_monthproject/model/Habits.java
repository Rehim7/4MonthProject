package com.example._monthproject.model;

import com.example._monthproject.myenum.Frequency;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "habits")
public class Habits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Column(name = "habit_name")
    private String habitName;
    @Column(name = "habit_description")
    private String habitDescription;

    private Integer currentStreak = 0;
    private Integer longestStreak = 0;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
