package com.example.abstractionapp.dto;

import com.example.abstractionapp.models.AbstractType;
import com.example.abstractionapp.models.Communication;
import com.example.abstractionapp.models.Operation;
import com.example.abstractionapp.models.Task;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OperationImplementationDto {

    private Long id;

    private String uuid;

    @NotNull
    private Task task;

    @NotNull
    private Task task2;

    @NotNull
    private Operation operation;

    @NotNull
    private AbstractType abstractType;

    @NotNull
    private Communication communication;

    private boolean active;

    private String attributes;

    private long createdBy;

}
