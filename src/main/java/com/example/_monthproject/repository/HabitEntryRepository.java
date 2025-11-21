package com.example._monthproject.repository;

import com.example._monthproject.model.HabitEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabitEntryRepository extends JpaRepository<HabitEntry, Long> {

    @Query("SELECT he FROM HabitEntry he WHERE he.habitId.user.id = :userId")
    List<HabitEntry> findAllByUserId(@Param("userId") Long userId);

    List<HabitEntry> findByHabitId_IdAndIsCompletedTrue(Long habitId);

    List<HabitEntry> findByHabitId_Id(Long habitId);

}