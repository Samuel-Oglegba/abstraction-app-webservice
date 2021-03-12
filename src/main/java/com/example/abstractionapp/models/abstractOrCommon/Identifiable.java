package com.example.abstractionapp.models.abstractOrCommon;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> extends Serializable {
    T getId();

    void setId(T id);

    default void prePersist() {
    }

    default void preUpdate() {
    }
}
