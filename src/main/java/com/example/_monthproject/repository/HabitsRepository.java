package com.example._monthproject.repository;

import com.example._monthproject.model.Habits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitsRepository extends JpaRepository<Habits, Integer> {
    void deleteById(Habits findbyd);

    Habits findById(Long id);
}
