package com.example.abstractionapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DotInputInitializerDto {

    private long userId;

    @NotNull
    private String dotInput;
}
