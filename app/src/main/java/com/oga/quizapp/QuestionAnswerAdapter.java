package com.oga.quizapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class QuestionAnswerAdapter extends RecyclerView.Adapter<QuestionAnswerAdapter.QuestionAnswerHolder>{

    private List<AnswerQuestions> answerQuestionsList;

    public QuestionAnswerAdapter(List<AnswerQuestions> answerQuestionsList) {

        this.answerQuestionsList = answerQuestionsList;
    }

    @NonNull
    @Override
    public QuestionAnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionAnswerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAnswerHolder holder, int position) {
        AnswerQuestions answerQuestions = answerQuestionsList.get(position);
        holder.questionNumberTextView.setText(String.format(Locale.getDefault(), "Question %d of %d", position+1, answerQuestionsList.size()));
        holder.questionTextView.setText(answerQuestions.getQuestion().getQuestion());
        holder.optionARadio.setText(answerQuestions.getQuestion().getOptionA());
        holder.optionBRadio.setText(answerQuestions.getQuestion().getOptionB());
        holder.optionCRadio.setText(answerQuestions.getQuestion().getOptionC());
        holder.optionDRadio.setText(answerQuestions.getQuestion().getOptionD());

        if(answerQuestions.getQuestion().getAnswer().equalsIgnoreCase("a")){
            holder.optionARadio.setChecked(true);
        }else if(answerQuestions.getQuestion().getAnswer().equalsIgnoreCase("b")){
            holder.optionBRadio.setChecked(true);
        }else if(answerQuestions.getQuestion().getAnswer().equalsIgnoreCase("c")){
            holder.optionCRadio.setChecked(true);
        }else{
            holder.optionDRadio.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return answerQuestionsList.size();
    }

    public class QuestionAnswerHolder extends RecyclerView.ViewHolder{

        TextView questionNumberTextView, questionTextView;
        RadioButton optionARadio, optionBRadio, optionCRadio, optionDRadio;

        public QuestionAnswerHolder(View itemView) {
            super(itemView);

            questionNumberTextView = itemView.findViewById(R.id.question_number_text_view);
            questionTextView = itemView.findViewById(R.id.question_text_view);

            optionARadio = itemView.findViewById(R.id.optiona_radio);
            optionBRadio = itemView.findViewById(R.id.optionb_radio);
            optionCRadio = itemView.findViewById(R.id.optionc_radio);
            optionDRadio = itemView.findViewById(R.id.optiond_radio);

            optionARadio.setClickable(false);
            optionBRadio.setClickable(false);
            optionCRadio.setClickable(false);
            optionDRadio.setClickable(false);
        }
    }
}
