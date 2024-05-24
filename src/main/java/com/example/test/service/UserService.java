package com.example.test.service;

import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.repository.UserRepository;
import com.example.test.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toUserDto)
              .orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find User for ID = %s", id)));
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(UserMapper.toUserEntity(userDto));
        return UserMapper.toUserDto(user);
    }

    @Transactional
    public void updateUserBalances(Map<Integer, Integer> request) {
        List<User> userList = userRepository.findAllById(request.keySet());

        Map<Long, User> userMap = userList
              .stream()
              .filter(Objects::nonNull)
              .collect(Collectors.toMap(User::getId, Function.identity()));

        for (Map.Entry<Integer, Integer> userDto : request.entrySet()) {
            userMap.get(userDto.getKey().longValue()).setBalance(userDto.getValue());
        }

        userRepository.saveAll(userMap.values());
    }
}
