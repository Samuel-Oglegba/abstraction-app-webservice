package com.example.abstractionapp.models;

import com.example.abstractionapp.models.abstractOrCommon.NamedModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "abstract_types")
@NoArgsConstructor
public class AbstractType extends NamedModel {

    public AbstractType(String name, long createdBy){

        //set the variables
        this.setName(name);
        this.setCreatedBy(createdBy);

    }//AbstractType

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
