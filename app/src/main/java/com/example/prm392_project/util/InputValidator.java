package com.example.prm392_project.util;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.prm392_project.data.repositories.validation.interfaces.Validator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class InputValidator {
    public static TextWatcher getValidationWatcher(TextInputEditText textInputEditText, List<Validator> validators) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextInputLayout textInputLayout = (TextInputLayout) textInputEditText.getParent().getParent();

                for (Validator validator : validators) {
                    String errorMessage = validator.validate(s.toString());
                    if (errorMessage != null) {
                        textInputLayout.setError(errorMessage);
                        textInputLayout.setErrorEnabled(true);
                        return;
                    }
                }

                textInputLayout.setError(null);
                textInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    public static boolean validateField(TextInputEditText textInputEditText, List<Validator> validators) {
        TextInputLayout textInputLayout = (TextInputLayout) textInputEditText.getParent().getParent();
        String value = textInputEditText.getText().toString().trim();

        for (Validator validator : validators) {
            String errorMessage = validator.validate(value);
            if (errorMessage != null) {
                textInputLayout.setError(errorMessage);
                textInputLayout.setErrorEnabled(true);
                return false;
            }
        }

        return true;
    }

    private static void addClearErrorOnTyping(TextInputEditText textInputEditText, TextInputLayout textInputLayout) {
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    textInputLayout.setError(null);
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
