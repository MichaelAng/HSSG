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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends Activity implements View.OnClickListener {

	Button btnMultipleChoice, btnScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnMultipleChoice = (Button) findViewById(R.id.btnMultipleChoice);
		btnScore = (Button) findViewById(R.id.btnScore);

		btnMultipleChoice.setOnClickListener(this);
		btnScore.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMultipleChoice:
			startActivity(new Intent(this, TopicPage.class));
			break;
		case R.id.btnScore:
			startActivity(new Intent(this, ScorePage.class));
			break;
		}
	}
}
