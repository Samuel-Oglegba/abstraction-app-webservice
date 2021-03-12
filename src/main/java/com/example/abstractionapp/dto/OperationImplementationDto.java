package com.example.abstractionapp.dto;

import javax.validation.constraints.NotNull;

public class OperationImplementationDto {

    private Long id;

    private String uuid;

    @NotNull
    private long taskId;

    @NotNull
    private long operationID;

    @NotNull
    private long communicationID;

    private boolean active;

    private String attributes;

}
