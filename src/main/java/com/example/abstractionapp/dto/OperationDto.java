package com.example.abstractionapp.dto;

import com.example.abstractionapp.models.AbstractType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OperationDto {

    private Long id;

    private String uuid;

    @NotNull
    private String name;

    private boolean active;

    private String inputType;

    private String outputType;

    private AbstractType abstractType;

    private long createdBy;

}
