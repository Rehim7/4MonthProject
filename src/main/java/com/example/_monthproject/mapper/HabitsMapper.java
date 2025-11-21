package com.example._monthproject.mapper;

import com.example._monthproject.dto.HabitsRequest;
import com.example._monthproject.dto.HabitsResponse;
import com.example._monthproject.model.Habits;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HabitsMapper {
    HabitsResponse toHabitsResponse(Habits habits);
    @Mapping(target = "id", ignore = true)
    Habits toHabits(HabitsRequest habitsRequest);
    @Mapping(target = "id", ignore = true)
    Habits  updateHabits(HabitsRequest habitsRequest, @MappingTarget Habits habits);
}
