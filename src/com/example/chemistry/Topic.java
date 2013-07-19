/************************************************************************
 *   Copyright 2013 Derek Li, Michael Ang
 *
 *   This file is part of The Jesse Hill Study Guide (TJHSG).
 *
 *   QuizApp is free software created by Seidenberg Creative Laboratory 
 *   for non-commercial use.
 *   
 *   Github account ///////
 ************************************************************************/

package com.example.chemistry;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Topic implements Parcelable {

	private List<Question> question = new ArrayList<Question>();
	private int score = 0;

	public List<Question> getQuestion() {
		return question;
	}

	public void incScore() {
		this.score++;
	}

	public int getScore() {
		return score;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public void setScore(int score) {
		this.score = score;
	}

	Topic() {
		question = new ArrayList<Question>();
	}

	public Topic(List<Question> question) {
		this.question = question;
	}

	public Topic(Parcel in) {
		score = in.readInt();
		in.readTypedList(question, Question.CREATOR);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel outParcel, int flags) {
		outParcel.writeInt(score);
		outParcel.writeTypedList(question);
	}

	public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>() {

		@Override
		public Topic createFromParcel(Parcel in) {
			return new Topic(in);
		}

		@Override
		public Topic[] newArray(int size) {
			return new Topic[size];
		}
	};

}
