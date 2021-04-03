package com.example.abstractionapp.models;

import com.example.abstractionapp.models.abstractOrCommon.NamedModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class Task extends NamedModel {

    public Task(String name, long createdBy){

        this.setName(name);
        this.setCreatedBy(createdBy);

    }//Task

    @ManyToOne
    @JsonIgnore
    private Attribute attribute;

    @PrePersist
    public void onPrePersistChild() {

        this.setActive(true);
        super.setName(this.getName().toUpperCase());

    }//onPrePersistChild

    @PreUpdate
    void onPreUpdateChild() {

        super.setName(this.getName().toUpperCase());

    }//onPreUpdateChild
}
