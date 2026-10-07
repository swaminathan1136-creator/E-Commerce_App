package com.example.e_commerce_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Toolbar title
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        if (toolbarTitle != null) {
            toolbarTitle.setText("Login");
        }

        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        ImageButton passwordToggle = findViewById(R.id.passwordToggle);
        TextView forgotPassword = findViewById(R.id.forgotPassword);
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signUpButton);

        // ===== Eye icon – show / hide password =====
        if (passwordToggle != null && passwordInput != null) {

            // Initial state: password is hidden
            passwordToggle.setImageResource(R.drawable.ic_eye_off);

            passwordToggle.setOnClickListener(v -> {
                if (isPasswordVisible) {
                    // Hide password
                    passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordToggle.setImageResource(R.drawable.ic_eye_off);
                    isPasswordVisible = false;
                } else {
                    // Show password
                    passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordToggle.setImageResource(R.drawable.ic_eye);
                    isPasswordVisible = true;
                }
                // Keep cursor at the end
                passwordInput.setSelection(passwordInput.getText().length());
            });
        }

        // ===== Forgot Password =====
        if (forgotPassword != null) {
            forgotPassword.setOnClickListener(v -> {
                try {
                    View dialogView = getLayoutInflater().inflate(R.layout.forgot_password, null);

                    EditText resetEmailInput = dialogView.findViewById(R.id.resetEmailInput);
                    Button btnCancelReset = dialogView.findViewById(R.id.btnCancelReset);
                    Button btnSendReset = dialogView.findViewById(R.id.btnSendReset);

                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setView(dialogView)
                            .setCancelable(true)
                            .create();

                    if (dialog.getWindow() != null) {
                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    }

                    if (btnCancelReset != null) {
                        btnCancelReset.setOnClickListener(view -> dialog.dismiss());
                    }

                    if (btnSendReset != null) {
                        btnSendReset.setOnClickListener(view -> {
                            String email = resetEmailInput != null ? resetEmailInput.getText().toString().trim() : "";
                            if (email.isEmpty()) {
                                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this,
                                        "Password reset link sent to " + email,
                                        Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });
                    }

                    dialog.show();

                } catch (Exception e) {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            });
        }

        // ===== Login =====
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });

        // ===== Sign Up =====
        signUpButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
    }
}