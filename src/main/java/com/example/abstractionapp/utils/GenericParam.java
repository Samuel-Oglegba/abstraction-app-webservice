package com.example.abstractionapp.utils;

import lombok.Data;

@Data
public class GenericParam {
    public GenericParam() {
    }

    public GenericParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key, value;
}
