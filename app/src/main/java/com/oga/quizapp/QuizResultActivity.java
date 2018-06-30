package com.oga.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class QuizResultActivity extends AppCompatActivity {

    public static final String ANSWERED_QUESTION_EXTRA = "answer_questions_extra";

    private TextView scoreTextView;
    private RecyclerView correctAnswerRecyclerView;
    private Button retakeQuizeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        correctAnswerRecyclerView = findViewById(R.id.corect_answer_recycler_view);
        correctAnswerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        retakeQuizeBtn = findViewById(R.id.retake_quiz_button);
        retakeQuizeBtn.setOnClickListener((view -> {
            startActivity(new Intent(QuizResultActivity.this, StartQuizActivity.class));
        }));

        scoreTextView = findViewById(R.id.final_score_textview);

        int score = 0;

        List<AnswerQuestions> answerQuestionsList = getIntent().getParcelableArrayListExtra(ANSWERED_QUESTION_EXTRA);
        for(AnswerQuestions answerQuestions: answerQuestionsList){
            if(answerQuestions.getAnswer().equalsIgnoreCase(answerQuestions.getQuestion().getAnswer()))
                score++;
        }

        scoreTextView.setText(String.format(Locale.getDefault(), "%d of %d", score, answerQuestionsList.size()));

        correctAnswerRecyclerView.setAdapter(new QuestionAnswerAdapter(answerQuestionsList));
    }
}
