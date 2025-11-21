package com.example._monthproject.habitcontroller;

import com.example._monthproject.dto.HabitEntryRequest;
import com.example._monthproject.dto.HabitEntryResponse;
import com.example._monthproject.mapper.HabitEntryMapper;
import com.example._monthproject.service.HabitEntryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitTrackerAPI/habitEntry")
public class HabitEntryController {
    private final HabitEntryService habitEntryService;
    private final HabitEntryMapper  habitEntryMapper;

    public HabitEntryController(HabitEntryService habitEntryService, HabitEntryMapper habitEntryMapper) {
        this.habitEntryService = habitEntryService;
        this.habitEntryMapper = habitEntryMapper;
    }
    @PostMapping("/markHabitAsCompleted")
    @Operation(summary = "Habiti tamamlayir", description = "Habitin tamamlandigini gosterir (Streak-i yeniləyir)")
    public ResponseEntity<HabitEntryResponse> markHabitAsCompleted(@RequestBody HabitEntryRequest habitEntryRequest) {
        HabitEntryResponse habitEntryResponse = habitEntryService.markHabitAsCompleted(habitEntryRequest);
        return new ResponseEntity<>(habitEntryResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteEntry/{id}")
    @Operation(summary = "Entryni silir",description = "Entryni silir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable Long id){
        habitEntryService.deleteEntry(id);
    }
    @GetMapping("/getEntriesByHabitId/{id}")
    @Operation(summary = "Entryleri getirir",description = "Habit id sine gore entryleri getirir")
    public ResponseEntity<List<HabitEntryResponse>> getEntriesByHabitId(@PathVariable Long id){
        List<HabitEntryResponse> entriesByHabitId = habitEntryService.getEntriesByHabitId(id);
        return ResponseEntity.ok(entriesByHabitId);
    }

}
