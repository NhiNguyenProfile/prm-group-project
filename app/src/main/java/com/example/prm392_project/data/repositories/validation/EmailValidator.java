package com.example.prm392_project.data.repositories.validation;

import android.util.Patterns;

import com.example.prm392_project.data.repositories.validation.interfaces.Validator;

public class EmailValidator implements Validator {
    @Override
    public String validate(String input) {
        if (!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            return "Invalid email format";
        }
        return null;
    }
}
