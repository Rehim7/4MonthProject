package com.example._monthproject.service;

import com.example._monthproject.dto.HabitsRequest;
import com.example._monthproject.dto.HabitsResponse;
import com.example._monthproject.mapper.HabitsMapper;
import com.example._monthproject.model.Habits;
import com.example._monthproject.repository.HabitsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabitsService {
    private final HabitsRepository habitsRepository;
    private final HabitsMapper habitsMapper;

    public HabitsService(HabitsRepository habitsRepository, HabitsMapper habitsMapper) {
        this.habitsRepository = habitsRepository;

        this.habitsMapper = habitsMapper;
    }

    public HabitsResponse createHabit(HabitsRequest habitsRequest) {
        Habits habits = habitsMapper.toHabits(habitsRequest);
        habitsRepository.save(habits);
        return habitsMapper.toHabitsResponse(habits);
    }

    public void deleteHabit(Long id ) {
        habitsRepository.deleteById(Math.toIntExact(id));
    }

    public HabitsResponse updateHabit(Long id,HabitsRequest habitsRequest) {
        Habits habits = habitsRepository.findById(id);
        habitsMapper.updateHabits(habitsRequest,habits);
        habitsRepository.save(habits);
        return habitsMapper.toHabitsResponse(habits);
    }
    public Optional<Habits> getHabit(Long id) {
        return habitsRepository.findById(Math.toIntExact(id));
    }
    public List<HabitsResponse> getAllHabits() {
    return habitsRepository.findAll().stream().map(habitsMapper::toHabitsResponse).collect(Collectors.toList());
    }





    }



