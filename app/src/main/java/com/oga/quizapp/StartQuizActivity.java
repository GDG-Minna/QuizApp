package com.oga.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class StartQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        Button startQuizBtn = findViewById(R.id.start_quiz_button);
        startQuizBtn.setOnClickListener((view -> {
            startActivity(new Intent(StartQuizActivity.this, MainActivity.class));
            finish();
        }));
    }
}
