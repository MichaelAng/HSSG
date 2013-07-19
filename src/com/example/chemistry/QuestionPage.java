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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionPage extends Activity implements OnCheckedChangeListener,
		OnClickListener {
	TextView question;
	Button submit;

	int position;
	int selectedAnswer = 4;

	String questionId, choiceAId, choiceBId, choiceCId, choiceDId, answerId,
			explanationId, explanation, answer;
	RadioButton choiceA, choiceB, choiceC, choiceD;
	RadioGroup mcGroup;
	int counter = 1;
	int topicPosition;

	Topic topic;
	Intent intent;

	File file = null;
	File path = null;
	BufferedReader reader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions_activity);

		Bundle b = getIntent().getExtras();
		topic = (Topic) b.getParcelable("topic");
		position = b.getInt("questionPosition", 0);
		topicPosition = b.getInt("topicPosition", 0);
		initialize();
		setValueId();
	}

	private void initialize() {
		question = (TextView) findViewById(R.id.tvquestion);
		choiceA = (RadioButton) findViewById(R.id.rchoice0);
		choiceB = (RadioButton) findViewById(R.id.rchoice1);
		choiceC = (RadioButton) findViewById(R.id.rchoice2);
		choiceD = (RadioButton) findViewById(R.id.rchoice3);
		mcGroup = (RadioGroup) findViewById(R.id.rgmultichoice);
		mcGroup.setOnCheckedChangeListener(this);
		submit = (Button) findViewById(R.id.bsubmit);
		submit.setOnClickListener(this);
	}

	// Assigns new Values
	private void setValueId() {
		mcGroup.clearCheck();
		// Sets up the string names to be fetches
		question.setText(topic.getQuestion().get(position).getQuestion());
		answer = topic.getQuestion().get(position).getAnswer();
		explanation = topic.getQuestion().get(position).getExplanation();

		choiceA.setText(topic.getQuestion().get(position).getChoiceA());
		choiceB.setText(topic.getQuestion().get(position).getChoiceB());
		choiceC.setText(topic.getQuestion().get(position).getChoiceC());
		choiceD.setText(topic.getQuestion().get(position).getChoiceD());

	}

	// Changes user choice
	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch (arg1) {
		case R.id.rchoice0:
			selectedAnswer = 0;
			break;
		case R.id.rchoice1:
			selectedAnswer = 1;
			break;
		case R.id.rchoice2:
			selectedAnswer = 2;
			break;
		case R.id.rchoice3:
			selectedAnswer = 3;
			break;
		}

	}

	// Handles the selection
	@Override
	public void onClick(View arg0) {
		if (selectedAnswer == 4) {
			new AlertDialog.Builder(this)
					.setTitle("You did not make a selection")
					.setMessage("Please select an option.")
					.setCancelable(false).setNegativeButton("OK", null).show();

		} else if (selectedAnswer == Integer.parseInt(answer)) {
			new AlertDialog.Builder(this)
					.setTitle("CORRECT")
					.setMessage("Your selection was correct!")
					.setCancelable(false)
					.setNegativeButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface x, int y) {
									position++;
									topic.incScore();
									if (position < topic.getQuestion().size()) {
										setValueId();
									} else {
										String scoreLine = topic.getQuestion()
												.get(0).getTopic()
												+ "@"
												+ topic.getScore()
												+ "@"
												+ topic.getQuestion().size();
										String mydate = java.text.DateFormat
												.getDateTimeInstance().format(
														Calendar.getInstance()
																.getTime());
										writeScore(scoreLine + "@" + mydate);
										startActivity(new Intent(QuestionPage.this, TopicPage.class));
									}
								}
							}).show();
		} else {
			new AlertDialog.Builder(this)
					.setTitle("Wrong")
					.setMessage(explanation)
					.setCancelable(false)
					.setNegativeButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface x, int y) {
									position++;
									if (position < topic.getQuestion().size()) {
										setValueId();
									} else {
										String scoreLine = topic.getQuestion()
												.get(0).getTopic()
												+ "@"
												+ topic.getScore()
												+ "@"
												+ topic.getQuestion().size();
										String mydate = java.text.DateFormat
												.getDateTimeInstance().format(
														Calendar.getInstance()
																.getTime());
										writeScore(scoreLine + "@" + mydate);
										startActivity(new Intent(QuestionPage.this, TopicPage.class));
									}
								}
							}).show();
		}
	}

	public void writeScore(String sBody) {

		try {
			File root = new File(Environment.getExternalStorageDirectory(),
					"Download");

			if (!root.exists()) {
				root.mkdirs();
			}

			File gpxfile = new File(root, "score.txt");

			BufferedWriter bW = new BufferedWriter(
					new FileWriter(gpxfile, true));
			bW.write(sBody);
			bW.newLine();
			bW.flush();
			bW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		Intent backToMA = new Intent(this, QuestionList.class);
		backToMA.putExtra("topic", topic);
		backToMA.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(backToMA);
	}
}