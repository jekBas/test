package com.example.test.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public record UserDto (
    Long id,

    @NotNull
    @Size(min = 3, max = 255)
    String name,

    @NotNull
    @Positive
    Integer balance
){}
