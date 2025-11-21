package com.example._monthproject.service;

import com.example._monthproject.dto.UserRequest;
import com.example._monthproject.dto.UserResponse;
import com.example._monthproject.exceptions.UserAlreadyExist;
import com.example._monthproject.exceptions.UserNotFound;
import com.example._monthproject.mapper.UserMapper;
import com.example._monthproject.model.HabitEntry;
import com.example._monthproject.model.User;
import com.example._monthproject.myenum.Levels;
import com.example._monthproject.repository.HabitEntryRepository;
import com.example._monthproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HabitEntryRepository habitEntryRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, HabitEntryRepository habitEntryRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.habitEntryRepository = habitEntryRepository;
    }

    public UserResponse addUser(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isPresent()) {throw new UserAlreadyExist("User already exist");}
        userRepository.save(user);
        UserResponse userResponse = userMapper.toUserResponse(user);
        return userResponse;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("İstifadəçi tapılmadı"));
        return userMapper.toUserResponse(user);
    }public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
        return userResponses;
    }

    private UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setHabits(user.getHabits());
        return response;
    }
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound("User Not Found"));
        userMapper.updateUser(userRequest, user);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public Levels habitCounter(Long userId) {
        List<HabitEntry> habitEntries = habitEntryRepository.findAllByUserId(userId);
        long completedHabits = habitEntries.stream().filter(HabitEntry::isCompleted).count();
        double completionPercentage = ((double) completedHabits / habitEntries.size()) * 100;

        if (completionPercentage >= 80){
            return Levels.EXCELLENT;
        } else if (completionPercentage >= 60){
            return Levels.GOOD;
        } else if (completionPercentage >= 40){
            return Levels.MEDIUM;
        } else if (completionPercentage >= 20){
            return Levels.BAD;
        } else {
            return Levels.VERY_BAD;
        }
    }
}
