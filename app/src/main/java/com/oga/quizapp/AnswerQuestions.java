package com.oga.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

public class AnswerQuestions implements Parcelable {
    private Question question;
    private String answer;

    public AnswerQuestions(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.question, flags);
        dest.writeString(this.answer);
    }

    protected AnswerQuestions(Parcel in) {
        this.question = in.readParcelable(Question.class.getClassLoader());
        this.answer = in.readString();
    }

    public static final Parcelable.Creator<AnswerQuestions> CREATOR = new Parcelable.Creator<AnswerQuestions>() {
        @Override
        public AnswerQuestions createFromParcel(Parcel source) {
            return new AnswerQuestions(source);
        }

        @Override
        public AnswerQuestions[] newArray(int size) {
            return new AnswerQuestions[size];
        }
    };
}
