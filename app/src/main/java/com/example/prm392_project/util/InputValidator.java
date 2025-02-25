package com.example.prm392_project.util;

import android.widget.EditText;

public class InputValidator {
    public static boolean validateInputBlank(EditText editText) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            editText.setError("This field is required");
            return false;
        }
        return true;
    }
}
