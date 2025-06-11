package com.example.newsfot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class DeveloperInfoActivity extends AppCompatActivity {

    ImageView imgDeveloper, backBtn;
    Button btnExit;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info);

        imgDeveloper = findViewById(R.id.imgDeveloper);
        btnExit = findViewById(R.id.btnExit);
        backBtn = findViewById(R.id.backBtn);

        // Set developer info with bold labels using HTML
        TextView tvDevInfo = findViewById(R.id.tvDevInfo);
        String info = "<b>Name:</b> W.M.N.U. Wickramaarachchi<br><br>" +
                "<b>Student No:</b> 2022T01585<br><br>" +
                "<b>Personal Statement:</b> FOT News is a mobile app designed to share faculty news, events, and academic updates in an organized and user-friendly way.<br><br>" +
                "<b>Version:</b> 1.0.0";
        tvDevInfo.setText(Html.fromHtml(info));

        // Exit app
        btnExit.setOnClickListener(v -> {
            finishAffinity();
        });

        // Back to home screen
        backBtn.setOnClickListener(v -> {
            finish();
        });

        // Click image to change it
        imgDeveloper.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                imgDeveloper.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
