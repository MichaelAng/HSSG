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

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Question implements Parcelable {
	private String topic;
	private String questionTitle;
	private String question;
	private String answer;
	private String explanation;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;

	public Question() {
	}
	
	public Question(String topic, String questionTitle, String question, String answer,
			String explanation, String choiceA, String choiceB, String choiceC,
			String choiceD) {
		super();
		this.topic = topic;
		this.questionTitle = questionTitle;
		this.question = question;
		this.answer = answer;
		this.explanation = explanation;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public String getQuestionTitle() {
		return questionTitle;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public String getExplanation() {
		return explanation;
	}

	public String getChoiceA() {
		return choiceA;
	}

	public String getChoiceB() {
		return choiceB;
	}

	public String getChoiceC() {
		return choiceC;
	}

	public String getChoiceD() {
		return choiceD;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}

	// The Parcelable Information
	public Question(Parcel read) {
		topic = read.readString();
		questionTitle = read.readString();
		question = read.readString();
		answer = read.readString();
		explanation = read.readString();
		choiceA = read.readString();
		choiceB = read.readString();
		choiceC = read.readString();
		choiceD = read.readString();
	}

	public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {

		@Override
		public Question createFromParcel(Parcel source) {
			return new Question(source);
		}

		@Override
		public Question[] newArray(int size) {
			return new Question[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeString(topic);
		dest.writeString(questionTitle);
		dest.writeString(question);
		dest.writeString(answer);
		dest.writeString(explanation);
		dest.writeString(choiceA);
		dest.writeString(choiceB);
		dest.writeString(choiceC);
		dest.writeString(choiceD);
	}

}

// public Question() {
//
// }
//
// public Question(String questionTitle, String question, String answer,
// String explanation, String choiceA, String choiceB, String choiceC,
// String choiceD) {
// super();
// this.questionTitle = questionTitle;
// this.question = question;
// this.answer = answer;
// this.explanation = explanation;
// this.choiceA = choiceA;
// this.choiceB = choiceB;
// this.choiceC = choiceC;
// this.choiceD = choiceD;
// }
//
// public void setQuestionTitle(String questionTitle) {
// this.questionTitle = questionTitle;
// }
//
// public void setQuestion(String question) {
// this.question = question;
// }
//
// public void setAnswer(String answer) {
// this.answer = answer;
// }
//
// public void setExplanation(String explanation) {
// this.explanation = explanation;
// }
//
// public void setChoiceA(String choiceA) {
// this.choiceA = choiceA;
// }
//
// public void setChoiceB(String choiceB) {
// this.choiceB = choiceB;
// }
//
// public void setChoiceC(String choiceC) {
// this.choiceC = choiceC;
// }
//
// public void setChoiceD(String choiceD) {
// this.choiceD = choiceD;
// }
//
// public String getQuestionTitle() {
// return questionTitle;
// }
//
// public String getQuestion() {
// return question;
// }
//
// public String getAnswer() {
// return answer;
// }
//
// public String getExplanation() {
// return explanation;
// }
//
// public String getChoiceA() {
// return choiceA;
// }
//
// public String getChoiceB() {
// return choiceB;
// }
//
// public String getChoiceC() {
// return choiceC;
// }
//
// public String getChoiceD() {
// return choiceD;
// }
//
// public static Creator<Question> getCreator() {
// return CREATOR;
// }
//
// public Question(Parcel in) {
// readFromParcel(in);
// }
//
// public void writeToParcel(Parcel dest, int flags) {
// dest.writeString(questionTitle);
// dest.writeString(question);
// dest.writeString(answer);
// dest.writeString(explanation);
// dest.writeString(choiceA);
// dest.writeString(choiceB);
// dest.writeString(choiceC);
// dest.writeString(choiceD);
// }
//
// public static final Creator<Question> CREATOR = new Creator<Question>() {
//
// public Question createFromParcel(Parcel source) {
// return new Question(source);
// }
//
// public Question[] newArray(int size) {
// return new Question[size];
// }
// };
//
// private void readFromParcel(Parcel in) {
// questionTitle = in.readString();
// question = in.readString();
// answer = in.readString();
// explanation = in.readString();
// choiceA = in.readString();
// choiceB = in.readString();
// choiceC = in.readString();
// choiceD = in.readString();
// }
//
// @Override
// public int describeContents() {
// // TODO Auto-generated method stub
// return 0;
// }
//
// }
