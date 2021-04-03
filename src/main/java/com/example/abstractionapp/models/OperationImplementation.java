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

    public OperationImplementation(Task task, Operation operation, Communication communication,
                                   String attributes, long createdBy){

        this.setTask(task);
        this.setOperation(operation);
        this.setCommunication(communication);
        this.setAttributes(attributes);
        this.setCreatedBy(createdBy);

    }//OperationImplementation

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
