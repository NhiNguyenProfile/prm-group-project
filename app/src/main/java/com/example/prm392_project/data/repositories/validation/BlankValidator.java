package com.example.prm392_project.data.repositories.validation;

import com.example.prm392_project.data.repositories.validation.interfaces.Validator;

public class BlankValidator implements Validator {
    @Override
    public String validate(String input) {
        if (input.trim().isEmpty()) {
            return "This field is required";
        }
        return null;
    }
}
