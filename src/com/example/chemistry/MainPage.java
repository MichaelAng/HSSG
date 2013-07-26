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

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends Activity implements View.OnClickListener {

	Button btnMultipleChoice, btnScore;	
	Typeface type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  
		//Sets the Title Bar Font
	    SpannableString s = new SpannableString("T.J.H. Study Guide");
	    s.setSpan(new TypefaceSpan(this, "starjout"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	 
	    // Update the action bar title with the TypefaceSpan instance
	    ActionBar actionBar = getActionBar();
	    actionBar.setTitle(s);
		
	    
	    //Initalize Views
		btnMultipleChoice = (Button) findViewById(R.id.btnMultipleChoice);
		btnScore = (Button) findViewById(R.id.btnScore);
		
		//Sets the Font for the Buttons
		type = Typeface.createFromAsset(getAssets(),"starjout.ttf"); 
		btnScore.setTypeface(type);
		btnMultipleChoice.setTypeface(type);
		
		//Click Listeners
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
