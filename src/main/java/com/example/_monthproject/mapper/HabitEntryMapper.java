package com.example._monthproject.mapper;

import com.example._monthproject.dto.HabitEntryRequest;
import com.example._monthproject.dto.HabitEntryResponse;
import com.example._monthproject.model.HabitEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HabitEntryMapper {
    HabitEntryResponse toHabitEntryResponse(HabitEntry habitEntry);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", ignore = true)
    HabitEntry toHabitEntry(HabitEntryRequest habitEntryRequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", ignore = true)
    HabitEntry updateHabitEntry(HabitEntryRequest habitEntryRequest, @MappingTarget HabitEntry habitEntry);
}
