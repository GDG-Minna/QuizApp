package com.oga.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioButton.OnClickListener{

    private TextView questionNumberTextView, questionTextView;
    private RadioButton optionARadio, optionBRadio,optionCRadio,optionDRadio;
    private Button nextBtn, prevBtn;

    private int currentQuestionNumber = 0;

    private Question currentQuestion;

    private ArrayList<AnswerQuestions> answerdQuestions;
    private String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerdQuestions = new ArrayList<>();

        questionNumberTextView = findViewById(R.id.question_number_text_view);
        questionTextView = findViewById(R.id.question_text_view);
        optionARadio = findViewById(R.id.optiona_radio);
        optionBRadio = findViewById(R.id.optionb_radio);
        optionCRadio = findViewById(R.id.optionc_radio);
        optionDRadio = findViewById(R.id.optiond_radio);

        nextBtn = findViewById(R.id.next_btn);
        prevBtn = findViewById(R.id.prev_btn);

        optionARadio.setOnClickListener(this);
        optionBRadio.setOnClickListener(this);
        optionCRadio.setOnClickListener(this);
        optionDRadio.setOnClickListener(this);

        nextBtn.setText("Next");
        nextBtn.setEnabled(false);
        nextBtn.setOnClickListener((view) -> {
            answerdQuestions.add(new AnswerQuestions(questions.get(currentQuestionNumber), answer));

            if(nextBtn.getText().equals("Finish")) {

                Intent quizResultIntent = new Intent(MainActivity.this, QuizResultActivity.class);
                quizResultIntent.putParcelableArrayListExtra(QuizResultActivity.ANSWERED_QUESTION_EXTRA, answerdQuestions);
                startActivity(quizResultIntent);

                finish();

            }else{
                currentQuestionNumber++;
                loadQuestions();
            }

        });

        prevBtn.setText("Prev");
        prevBtn.setVisibility(View.INVISIBLE);
        prevBtn.setOnClickListener((view) -> {currentQuestionNumber--; loadQuestions();});

        Collections.shuffle(questions);
        loadQuestions();
    }

    private void loadQuestions() {
        questionNumberTextView.setText(String.format("Question %d  of %d", currentQuestionNumber + 1, questions.size()));
        currentQuestion = questions.get(currentQuestionNumber);

        questionTextView.setText(currentQuestion.getQuestion());
        optionARadio.setText(currentQuestion.getOptionA());
        optionBRadio.setText(currentQuestion.getOptionB());
        optionCRadio.setText(currentQuestion.getOptionC());
        optionDRadio.setText(currentQuestion.getOptionD());

        optionARadio.setChecked(false);
        optionBRadio.setChecked(false);
        optionCRadio.setChecked(false);
        optionDRadio.setChecked(false);

        nextBtn.setEnabled(false);
        if(questions.size() == currentQuestionNumber+1){
            nextBtn.setText("Finish");
        }else{
            nextBtn.setText("Next");
        }

        if(currentQuestionNumber == 0) {
            prevBtn.setVisibility(View.INVISIBLE);
        }else {
            prevBtn.setVisibility(View.VISIBLE);
        }


    }/**
     * The Underlisted lines of code are the questions that I would be using for my Educational Quiz
     *
     * The Questions runs from 1 to 10
     *
     */

    List<Question> questions = Arrays.asList(
            new Question("What is the range of short data type in Java?", "b",
                    "-128 to 127", "-32768 to 32767", "-2147483648 to 2147483647", "None of the mentioned"),

            new Question("What is the range of byte data type in Java?", "a",
                    "-128 to 127", "-32768 to 32767", "-2147483648 to 2147483647", "None of the mentioned"),

            new Question("Which of the following are legal lines of Java code?\n" +
                    "1. int w = (int)888.8;\n" +
                    "     2. byte x = (byte)100L;\n" +
                    "     3. long y = (byte)100;\n" +
                    "     4. byte z = (byte)100L;", "d",
                    "1 and 2", "2 and 3", "3 and 4", "All statements are correct."),

            new Question("An expression involving byte, int, and literal numbers is promoted to which of these?", "a",
                    "int", "long", "byte", "float"),

            new Question("Which of these literals can be contained in float data type variable?", "b",
                    "-1.7e+308", "-3.4e+038", "+1.7e+308", "-3.4e+050"),

            new Question("Which data type value is returned by all transcendental math functions?", "c",
                    "int", "float", "double", "long"),

            new Question("What is the stored in the object obj in following lines of code? \n\nbox obj;", "b",
                    "Memory address of allocated memory of object", "NULL", "Any arbitrary pointer", "Garbage"),

            new Question("Which of these keywords is used to make a class?", "a",
                    "class", "struct", "int", "none of the mentioned"),

            new Question("Which of the following is a valid declaration of an object of class Box?", "a",
                    "Box obj = new Box();", "Box obj = new Box;", "obj = new Box();", "new Box obj;"),

            new Question("Which of these operators is used to allocate memory for an object?", "c",
                    "malloc", "alloc", "new", "give")
    );

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.optiona_radio:
//                optionARadio.setChecked(true);
                optionBRadio.setChecked(false);
                optionCRadio.setChecked(false);
                optionDRadio.setChecked(false);
                answer = "a";
                break;

            case R.id.optionb_radio:
//                optionBRadio.setChecked(true);
                optionARadio.setChecked(false);
                optionCRadio.setChecked(false);
                optionDRadio.setChecked(false);
                answer = "b";
                break;


            case R.id.optionc_radio:
//                optionCRadio.setChecked(true);
                optionARadio.setChecked(false);
                optionBRadio.setChecked(false);
                optionDRadio.setChecked(false);
                answer = "c";
                break;


            case R.id.optiond_radio:
//                optionDRadio.setChecked(true);
                optionARadio.setChecked(false);
                optionBRadio.setChecked(false);
                optionCRadio.setChecked(false);
                answer = "d";
                break;

        }

        nextBtn.setEnabled(true);
    }
}
