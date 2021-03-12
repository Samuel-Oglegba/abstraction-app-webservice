package com.example.abstractionapp.models.abstractOrCommon;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@MappedSuperclass
public class NamedModel extends Model {

    @Column(unique = true, nullable = false)
    @NotNull(message = "Name Cannot be null")
    @Size(min=3)
    private String name;
}
