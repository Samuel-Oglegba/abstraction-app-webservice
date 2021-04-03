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
@Table(name = "operations")
@NoArgsConstructor
public class Operation extends NamedModel {

    public Operation(String name, long createdBy){

         this.setName(name);
         this.setCreatedBy(createdBy);

    }//Operation

    public Operation(String name, String inputType, String outputType,long createdBy, AbstractType abstractType){

        this.setName(name);
        this.setInputType(inputType);
        this.setOutputType(outputType);
        this.setCreatedBy(createdBy);
        this.setAbstractType(abstractType);

    }//Operation

    private String inputType;

    private String outputType;

    @ManyToOne
    @JsonIgnore
    private AbstractType abstractType;

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
