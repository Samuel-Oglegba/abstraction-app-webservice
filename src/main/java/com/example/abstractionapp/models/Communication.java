package com.example.abstractionapp.models;

import com.example.abstractionapp.models.abstractOrCommon.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "communications")
@NoArgsConstructor
public class Communication extends Model {

    @ManyToOne
    @JsonIgnore
    private AbstractType abstractType;

    private String variableName;

    @PrePersist
    public void onPrePersistChild() {

        this.setActive(true);
        this.setVariableName(this.getVariableName().toUpperCase());

    }//onPrePersistChild

    @PreUpdate
    void onPreUpdateChild() {

        this.setVariableName(this.getVariableName().toUpperCase());

    }//onPreUpdateChild


}
