/************************************************************************
 *   Copyright 2013 Derek Li, Michael Ang
 *
 *   This file is part of The Jesse Hill Study Guide (TJHSG).
 *
 *   QuizApp is free software created by Seidenberg Creative Laboratory 
 *   for non-commercial use.
 *   
 *   Github account: https://github.com/MichaelAng/TJHSG
 ************************************************************************/

package com.example.chemistry;

public class Score {
	private String topic;
	private int amountCorrect;
	private int totalQuestionAnswered;
	private String dateTime;

	public Score(String topic, int amountCorrect, int totalQuestionAnswered, String dateTime) {
		super();
		this.topic = topic;
		this.amountCorrect = amountCorrect;
		this.totalQuestionAnswered = totalQuestionAnswered;
		this.dateTime = dateTime;
	}

	public String getTopic() {
		return topic;
	}

	public int getAmountCorrect() {
		return amountCorrect;
	}

	public int getTotalQuestionAnswered() {
		return totalQuestionAnswered;
	}

	public String getDateTime() {
		return dateTime;
	}

}
