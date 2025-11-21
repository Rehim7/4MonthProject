package com.example._monthproject.habitcontroller;

import com.example._monthproject.dto.HabitsRequest;
import com.example._monthproject.dto.HabitsResponse;
import com.example._monthproject.mapper.HabitsMapper;
import com.example._monthproject.model.Habits;
import com.example._monthproject.repository.HabitsRepository;
import com.example._monthproject.service.HabitsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habitTrackerAPI/habit")
public class HabitController {
    private final HabitsService habitsService;
    private final HabitsRepository habitsRepository;
    private final HabitsMapper habitsMapper;
    public HabitController(HabitsService habitsService, HabitsRepository habitsRepository, HabitsMapper habitsMapper) {
        this.habitsService = habitsService;
        this.habitsRepository = habitsRepository;
        this.habitsMapper = habitsMapper;
    }

    @PostMapping("/createHabit")
    @Operation(summary = "Yeni Habit yaratmaq",description = "Yeni Habit yaradir")
    public ResponseEntity<HabitsResponse> createHabit(@Valid @RequestBody HabitsRequest habitsRequest) {
        HabitsResponse habit = habitsService.createHabit(habitsRequest);
        return ResponseEntity.ok().body(habit);
    }

    @GetMapping("/getAllHabits")
    @Operation(summary = "Butun habitleri getirir",description = "Butun habitleri getirir")
//    @ParameterObject
//    @PageableDefault(page = 0, size = 4, sort = "id", direction = Sort.Direction.ASC)
    public ResponseEntity<List<HabitsResponse>> getAllHabits() {
        List<HabitsResponse> allHabits = habitsService.getAllHabits();
        return  ResponseEntity.ok().body(allHabits);
    }
    @GetMapping("/getHabitById/{id}")
    @Operation(summary = "Habiti getirir", description = "Verilen id ye gore habiti getirir")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Habits> getHabitById(@PathVariable Long id) {
        return habitsService.getHabit(id);
    }

    @DeleteMapping("/deleteHabit/{id}")
    @Operation(summary = "Habit silmek",description = "Verilen id-ye gore habiti silir")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHabit(@PathVariable Long id) {
        habitsService.deleteHabit(id);
    }
    @PutMapping("/updateHabit/{id}")
    @Operation(summary = "Habiti yenilemek",description = "Verilen id-ye gore habiti yenileyir")
    public ResponseEntity<HabitsResponse> updateHabit(@PathVariable Long id, @Valid @RequestBody HabitsRequest habitsRequest) {
        HabitsResponse updatedHabit = habitsService.updateHabit(id, habitsRequest);
        return ResponseEntity.ok(updatedHabit);
    }

}
