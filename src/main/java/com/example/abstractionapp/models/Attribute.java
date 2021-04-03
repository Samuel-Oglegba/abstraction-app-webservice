package com.example.abstractionapp.models;


import com.example.abstractionapp.models.abstractOrCommon.NamedModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "attributes")
@NoArgsConstructor
public class Attribute extends NamedModel {

    private String value;
    /**
     * TODO: category should be an entity
     */
    private String categories;
}
