package com.example.abstractionapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommunicationDto {

    private Long id;

    private String uuid;

    @NotNull
    private String variableName;

    private boolean active;

    private String attributes;
}
