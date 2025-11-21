package com.example._monthproject.mapper;

import com.example._monthproject.dto.UserRequest;
import com.example._monthproject.dto.UserResponse;
import com.example._monthproject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest userRequest);
    @Mapping(target = "id", ignore = true)
    User updateUser(UserRequest userRequest, @MappingTarget User user);
}
