/************************************************************************
 *   Copyright 2013 Derek Li, Michael Ang
 *
 *   This file is part of The High School Study Guide (HSSG).
 *
 *   HSSG is free software created by Seidenberg Creative Laboratory 
 *   for non-commercial use.
 *   
 *   Github account: https://github.com/MichaelAng/HSSG
 ************************************************************************/

package com.example.chemistry;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainPage extends Activity implements View.OnClickListener {
	//CONSTANTS
	static String FONT = "starjout.ttf";
	
	//Initialize Variables
	Button btnMultipleChoice, btnScore, btnFile;
	Typeface type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initialize();
		setButtonFont();
		setOnClickListener();
	}//Ends onCreate

	private void initialize() {// Initialize buttons
		btnMultipleChoice = (Button) findViewById(R.id.btnMultipleChoice);
		btnScore = (Button) findViewById(R.id.btnScore);
		btnFile = (Button) findViewById(R.id.btnFile);
	}// Ends initialize method

	private void setButtonFont() {// Sets the Font for the buttons
		type = Typeface.createFromAsset(getAssets(), FONT);
		btnScore.setTypeface(type);
		btnMultipleChoice.setTypeface(type);
		btnFile.setTypeface(type);
	}// Ends setButtonFont method

	private void setOnClickListener() {// Sets click Listeners for the buttons
		btnMultipleChoice.setOnClickListener(this);
		btnScore.setOnClickListener(this);
		btnFile.setOnClickListener(this);
	}// Ends setOnClickListener method

	public void onClick(View v) {// Listens for clicks
		switch (v.getId()) {
		case R.id.btnMultipleChoice:
			startActivity(new Intent(this, TopicPage.class));
			break;
		case R.id.btnScore:
			startActivity(new Intent(this, ScorePage.class));
			break;
		case R.id.btnFile:
			startActivity(new Intent(this, FileChooser.class));
			break;
		}// Ends switch cases
	}// Ends onClick method

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.main, menu);
		return true;
	}// ends onCreateOptionsMenu method

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.aboutUs:
			startActivity(new Intent(this, AboutPage.class));
			break;
		case R.id.preferences:
			startActivity(new Intent(this, Prefs.class));
			break;
		case R.id.tutorial:
			startActivity(new Intent(this, TutorialPage.class));
			break;
		}// Ends switch case
		return false;
	}// Ends onOptionsItemSelected method
}// Ends MainPage class
