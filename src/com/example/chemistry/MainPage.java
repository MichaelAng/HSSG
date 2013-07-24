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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainPage extends Activity implements View.OnClickListener {

	Button btnMultipleChoice, btnScore;
	
	Typeface type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		type = Typeface.createFromAsset(getAssets(),"starjout.ttf"); 
		   
		
		btnMultipleChoice = (Button) findViewById(R.id.btnMultipleChoice);
		btnScore = (Button) findViewById(R.id.btnScore);
		
		btnScore.setTypeface(type);
		btnMultipleChoice.setTypeface(type);
		
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
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.main, menu);
		return true;

	}

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
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}
}
