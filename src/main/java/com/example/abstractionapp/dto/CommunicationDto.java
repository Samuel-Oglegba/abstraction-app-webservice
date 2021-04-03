package com.example.abstractionapp.dto;

import com.example.abstractionapp.models.AbstractType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommunicationDto {

    private long id;

    private String uuid;

    @NotNull
    private String variableName;

    private boolean active;

    private AbstractType abstractType;

    private long createdBy;
}
