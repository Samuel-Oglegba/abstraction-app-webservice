package com.example.abstractionapp.models.abstractOrCommon;

import com.example.abstractionapp.models.abstractOrCommon.Identifiable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@ToString
public abstract class Audit <ID extends Serializable> implements Identifiable<ID> {

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private boolean active;

    public Audit() {
    }

    public void delete(){
        active = false;
    }

    public void activate(){
        active = true;
    }
}
