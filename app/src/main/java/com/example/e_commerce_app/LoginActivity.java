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

        ImageButton backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

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

        if (forgotPassword != null) {
            forgotPassword.setOnClickListener(v -> {
                try {
                    View dialogView = getLayoutInflater().inflate(R.layout.forgot_password, null);

                    EditText resetEmailInput = dialogView.findViewById(R.id.resetEmailInput);
                    Button btnCancel = dialogView.findViewById(R.id.btnCancel);
                    Button btnSendLink = dialogView.findViewById(R.id.btnSendLink);

                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setView(dialogView)
                            .setCancelable(true)
                            .create();

                    if (dialog.getWindow() != null) {
                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    }

                    if (btnCancel != null) {
                        btnCancel.setOnClickListener(view -> dialog.dismiss());
                    }

                    if (btnSendLink != null) {
                        btnSendLink.setOnClickListener(view -> {
                            String email = resetEmailInput != null
                                    ? resetEmailInput.getText().toString().trim()
                                    : "";
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

        if (loginButton != null) {
            loginButton.setOnClickListener(v -> {
                String email = emailInput != null ? emailInput.getText().toString().trim() : "";
                String password = passwordInput != null ? passwordInput.getText().toString().trim() : "";

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            });
        }

        if (signUpButton != null) {
            signUpButton.setOnClickListener(v ->
                    startActivity(new Intent(LoginActivity.this, SignUpActivity.class))
            );
        }
    }
}