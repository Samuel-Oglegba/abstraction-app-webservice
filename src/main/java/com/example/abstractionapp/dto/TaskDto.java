package com.example.abstractionapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskDto {

    private long id;

    private String uuid;

    @NotNull
    private String name;

    private boolean active;

    private long attributeId;

    private long createdBy;

}
