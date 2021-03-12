package com.example.abstractionapp.models;

import com.example.abstractionapp.models.abstractOrCommon.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "operation_implementations")
@NoArgsConstructor
public class OperationImplementation extends Model {

    @ManyToOne
    @JsonIgnore
    private Task task;

    @ManyToOne
    @JsonIgnore
    private Operation operation;

    @ManyToOne
    @JsonIgnore
    private Communication communication;

    private String attributes;
}
