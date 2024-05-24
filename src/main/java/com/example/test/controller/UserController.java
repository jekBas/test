package com.example.test.controller;

import com.example.test.dto.UserDto;
import com.example.test.service.UserService;
import com.example.test.utils.validator.BalanceConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Validated
@RestController
@RequestMapping("/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/set-users-balance")
    public ResponseEntity<?> updateUsersBalances(@RequestBody @BalanceConstraint Map<Integer, Integer> userBalances) {
        userService.updateUserBalances(userBalances);
        return ResponseEntity.ok().build();
    }
}
