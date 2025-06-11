package com.example.newsfot;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    ImageView backBtn, imgUser;
    TextView tvUserInfo;
    Button btnEditInfo, btnSignOut;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Bind views
        backBtn = findViewById(R.id.backBtn);
        imgUser = findViewById(R.id.imgUser);
        tvUserInfo = findViewById(R.id.tvUserInfo);
        btnEditInfo = findViewById(R.id.btnEditInfo);
        btnSignOut = findViewById(R.id.btnSignOut);

        // SharedPreferences
        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Show user info
        loadUserData();

        // Back button
        backBtn.setOnClickListener(v -> finish());

        // Edit info
        btnEditInfo.setOnClickListener(v -> showEditDialog());

        // Sign out
        btnSignOut.setOnClickListener(v -> {
            // Optional: Clear SharedPreferences if you want
            // prefs.edit().clear().apply();
            // Go back to login screen
            Intent intent = new Intent(UserInfoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loadUserData() {
        String username = prefs.getString("username", "User");
        String email = prefs.getString("email", "example@email.com");
        tvUserInfo.setText("Name: " + username + "\n\nEmail: " + email);
    }

    private void showEditDialog() {
        // Inflate the dialog layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_info, null);
        EditText etName = dialogView.findViewById(R.id.etEditName);
        EditText etEmail = dialogView.findViewById(R.id.etEditEmail);

        // Pre-fill current values
        etName.setText(prefs.getString("username", ""));
        etEmail.setText(prefs.getString("email", ""));

        new AlertDialog.Builder(this)
                .setTitle("Edit Info")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newName = etName.getText().toString().trim();
                    String newEmail = etEmail.getText().toString().trim();

                    if (!newName.isEmpty() && !newEmail.isEmpty()) {
                        prefs.edit()
                                .putString("username", newName)
                                .putString("email", newEmail)
                                .apply();

                        loadUserData();
                        Toast.makeText(this, "Info updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
