package com.example.e_commerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("Sign Up");
        }

        EditText nameInput = findViewById(R.id.nameInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        ImageButton passwordToggle = findViewById(R.id.passwordToggle);
        ImageButton confirmPasswordToggle = findViewById(R.id.confirmPasswordToggle);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        TextView loginText = findViewById(R.id.loginText);

        if (passwordToggle != null && passwordInput != null) {
            passwordToggle.setImageResource(R.drawable.ic_eye_off);
            passwordToggle.setOnClickListener(v -> {
                if (isPasswordVisible) {
                    passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordToggle.setImageResource(R.drawable.ic_eye_off);
                    isPasswordVisible = false;
                } else {
                    passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordToggle.setImageResource(R.drawable.ic_eye);
                    isPasswordVisible = true;
                }
                passwordInput.setSelection(passwordInput.getText().length());
            });
        }

        if (confirmPasswordToggle != null && confirmPasswordInput != null) {
            confirmPasswordToggle.setImageResource(R.drawable.ic_eye_off);
            confirmPasswordToggle.setOnClickListener(v -> {
                if (isConfirmPasswordVisible) {
                    confirmPasswordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmPasswordToggle.setImageResource(R.drawable.ic_eye_off);
                    isConfirmPasswordVisible = false;
                } else {
                    confirmPasswordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmPasswordToggle.setImageResource(R.drawable.ic_eye);
                    isConfirmPasswordVisible = true;
                }
                confirmPasswordInput.setSelection(confirmPasswordInput.getText().length());
            });
        }

        if (createAccountButton != null) {
            createAccountButton.setOnClickListener(v -> {
                String name = nameInput != null ? nameInput.getText().toString().trim() : "";
                String email = emailInput != null ? emailInput.getText().toString().trim() : "";
                String password = passwordInput != null ? passwordInput.getText().toString().trim() : "";
                String confirmPassword = confirmPasswordInput != null ? confirmPasswordInput.getText().toString().trim() : "";

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                finish();
            });
        }

        if (loginText != null) {
            loginText.setOnClickListener(v -> finish());
        }
    }
}