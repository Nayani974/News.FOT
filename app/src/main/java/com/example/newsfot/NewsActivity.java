package com.example.newsfot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    ImageView userIcon, infoIcon;
    TextView welcomeText, readMore1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Get username passed from Login or Signup
        String username = getIntent().getStringExtra("username");
        if (username == null || username.isEmpty()) {
            username = "User"; // fallback if missing
        }

        // Bind Views
        userIcon = findViewById(R.id.userIcon);
        infoIcon = findViewById(R.id.infoIcon);
        welcomeText = findViewById(R.id.welcomeText);
        readMore1 = findViewById(R.id.readMore1);

        // Set welcome message
        welcomeText.setText("Hi, " + username);

        // Go to User Info screen
        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
        });

        // ✅ Go to Developer Info screen when info icon clicked
        infoIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, DeveloperInfoActivity.class);
            startActivity(intent);
        });

        // Only first card's "Read more" goes to Clicked News screen
        readMore1.setOnClickListener(v -> {
            Intent intent = new Intent(this, ClickedNewsActivity.class);
            startActivity(intent);
        });
    }
}
