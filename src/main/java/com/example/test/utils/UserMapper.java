package com.example.test.utils;

import com.example.test.dto.UserDto;
import com.example.test.entity.User;

import java.util.Objects;

public final class UserMapper {
    private UserMapper() {
    }

    /**
     * Converts a {@link User} object to a {@link UserDto} object.
     *
     * @param user the User object to be converted
     * @return a new {@link UserDto} object containing the data from the user, or null if user is null
     */
    public static UserDto toUserDto(User user) {
        if (Objects.isNull(user)) {
            return null;
        }

        return new UserDto(user.getId(), user.getName(), user.getBalance());
    }

    /**
     * Converts a {@link UserDto} object to a {@link User} object.
     *
     * @param userDto the UserDto object to be converted
     * @return a new User object containing the data from the userDto, or null if userDto is null
     */
    public static User toUserEntity(UserDto userDto) {
        return new User(userDto.id(), userDto.name(), userDto.balance());
    }
}
