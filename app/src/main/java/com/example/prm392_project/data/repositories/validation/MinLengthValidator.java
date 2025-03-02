package com.example.prm392_project.data.repositories.validation;

import com.example.prm392_project.data.repositories.validation.interfaces.Validator;

public class MinLengthValidator implements Validator {
    private final int minLength;

    public MinLengthValidator(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public String validate(String input) {
        if (input.length() < minLength) {
            return "Must be at least " + minLength + " characters";
        }
        return null;
    }
}
