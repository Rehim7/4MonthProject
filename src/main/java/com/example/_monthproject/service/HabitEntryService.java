package com.example._monthproject.service;

import com.example._monthproject.dto.HabitEntryRequest;
import com.example._monthproject.dto.HabitEntryResponse;
import com.example._monthproject.exceptions.HabitNotFound;
import com.example._monthproject.mapper.HabitEntryMapper;
import com.example._monthproject.model.HabitEntry;
import com.example._monthproject.model.Habits;
import com.example._monthproject.repository.HabitEntryRepository;
import com.example._monthproject.repository.HabitsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabitEntryService {
    private final HabitEntryRepository habitEntryRepository;
    private final HabitEntryMapper habitEntryMapper;
    private final HabitsRepository habitsRepository;

    public HabitEntryService(HabitEntryRepository habitEntryRepository, HabitEntryMapper habitEntryMapper, HabitsRepository habitsRepository) {
        this.habitEntryRepository = habitEntryRepository;
        this.habitEntryMapper = habitEntryMapper;
        this.habitsRepository = habitsRepository;
    }

    public HabitEntryResponse markHabitAsCompleted(HabitEntryRequest request) {
        HabitEntry habitEntry = habitEntryMapper.toHabitEntry(request);
        habitEntry.setCompleted(true);
        HabitEntry savedEntry = habitEntryRepository.save(habitEntry);

        Long habitId = savedEntry.getHabitId().getId();
        updateStreak(habitId);

        return habitEntryMapper.toHabitEntryResponse(savedEntry);
    }

    public void deleteEntry(Long entryId) {
        Optional<HabitEntry> entryToDelete = habitEntryRepository.findById(entryId);
                entryToDelete.orElseThrow(() -> new HabitNotFound("Silinəcək qeyd tapılmadı: " + entryId));

        Long habitId = entryToDelete.get().getId();

        habitEntryRepository.deleteById(entryId);

        updateStreak(habitId);
    }

    public List<HabitEntryResponse> getEntriesByHabitId(Long habitId) {
        List<HabitEntry> entries = habitEntryRepository.findByHabitId_Id(habitId);
        return entries.stream()
                .map(habitEntryMapper::toHabitEntryResponse)
                .collect(Collectors.toList());
    }

    private void updateStreak(Long habitId) {
        Habits habit = habitsRepository.findById(Math.toIntExact(habitId))
                .orElseThrow(() -> new HabitNotFound("Vərdiş tapılmadı: " + habitId));

        List<HabitEntry> entries = habitEntryRepository.findByHabitId_IdAndIsCompletedTrue(habitId);

        List<LocalDate> completedDates = entries.stream()
                .map(HabitEntry::getDate)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        if (completedDates.isEmpty()) {
            habit.setCurrentStreak(0);
            habit.setLongestStreak(0);
            habitsRepository.save(habit);
            return;
        }

        int[] streaks = calculateStreaksLogic(completedDates);

        habit.setCurrentStreak(streaks[0]);
        habit.setLongestStreak(streaks[1]);
        habitsRepository.save(habit);
    }

    private int[] calculateStreaksLogic(List<LocalDate> completedDates) {
        if (completedDates.isEmpty()) {
            return new int[]{0, 0};
        }

        int longestStreak = 0;
        int currentSequence = 1;

        LocalDate lastDate = completedDates.get(0);
        for (int i = 1; i < completedDates.size(); i++) {
            LocalDate currentDate = completedDates.get(i);

            if (currentDate.isEqual(lastDate.plusDays(1))) {
                currentSequence++;
            } else if (!currentDate.isEqual(lastDate)) {
                longestStreak = Math.max(longestStreak, currentSequence);
                currentSequence = 1;
            }
            lastDate = currentDate;
        }
        longestStreak = Math.max(longestStreak, currentSequence);

        int currentStreak = 0;
        LocalDate today = LocalDate.now();
        LocalDate expectedDate = today;

        LocalDate lastCompletedDate = completedDates.get(completedDates.size() - 1);

        if (lastCompletedDate.isBefore(today.minusDays(1))) {
            return new int[]{0, longestStreak};
        }

        for (int i = completedDates.size() - 1; i >= 0; i--) {
            LocalDate date = completedDates.get(i);

            if (date.isEqual(expectedDate)) {
                currentStreak++;
                expectedDate = expectedDate.minusDays(1);
            } else if (date.isEqual(expectedDate.minusDays(1))) {
                expectedDate = expectedDate.minusDays(1);
            } else if (date.isBefore(expectedDate)) {
                break;
            }
        }

        return new int[]{currentStreak, longestStreak};
    }
}