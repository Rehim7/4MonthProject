package com.example._monthproject.habitcontroller;

import com.example._monthproject.dto.UserRequest;
import com.example._monthproject.dto.UserResponse;
import com.example._monthproject.myenum.Levels;
import com.example._monthproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/habitTrackerAPI/user")
@RestController

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @Operation(summary = "Yeni User yaratmaq", description = "Yeni User yaradir")
    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }


    @Operation(summary = "Habit tamamlanma Leveli", description = "Habiti nece tamamlandiginizi yoxlayir")
    @PostMapping("/calculate-level/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> calculateHabitLevel(@Valid @PathVariable Long userId) {
        Levels calculatedLevel = userService.habitCounter(userId);
        return Map.of(
                "level", calculatedLevel.name(),
                "message", calculatedLevel.getDisplayMessage()
        );
    }

    @Operation(summary = "User silir", description = "Verilen id-e gore User-i silir")
    @DeleteMapping("/deleteUser/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@Valid @PathVariable Long id) {
        userService.deleteUser(id);
    }

    @Operation(summary = "Bütün Userləri verir", description = "Bütün Userləri səhifələnmiş formada göstərir")
    @GetMapping("/getAllUsers")
    @ResponseStatus(HttpStatus.OK)
//    @ParameterObject @PageableDefault(page = 0, size = 4, sort = "id", direction = Sort.Direction.ASC)
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }


    @Operation(summary = "Useri tapir", description = "Verilen id-e gore useri tapir")
    @GetMapping("/getUserById/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public UserResponse getUserById(@Valid @PathVariable Long id) {
        return userService.getUserById(id);
    }


    @Operation(summary = "Useri update edir", description = "Verilen id-e gore useri tapib yenileyir")
    @PutMapping("/updateUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@Valid @PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }


}


