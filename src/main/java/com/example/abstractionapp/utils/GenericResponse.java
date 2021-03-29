package com.example.abstractionapp.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class GenericResponse {

    private String message;
    private String error;
    private String responseCode;
    private String responseDescription;
    private List<GenericParam> additionalInfo;

    public GenericResponse(final String message) {
        super();
        this.message = message;
    }

    public GenericResponse(final String message, final String error) {
        super();
        this.message = message;
        this.error = error;
    }

    public GenericResponse(final String message, final String error, final List<GenericParam> additionalInfo) {
        super();
        this.message = message;
        this.error = error;
        this.additionalInfo = additionalInfo;
    }

    public GenericResponse(List<ObjectError> allErrors, String error) {
        this.error = error;
        this.message = allErrors.stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.joining(","));
    }
}