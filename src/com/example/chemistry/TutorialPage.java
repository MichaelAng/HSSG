package com.example.chemistry;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TutorialPage extends Activity implements OnClickListener {
	String FONT = "starjout.ttf";
	String TITLEPAGENAME = "Tutorial";
	Button bOK;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial_activity);	
		setTitleBar();
		bOK = (Button) findViewById(R.id.bOK);
		bOK.setOnClickListener(this);
		
	}
	
	private void setTitleBar() {// Sets the Title Bar Name and Font
		SpannableString s = new SpannableString(TITLEPAGENAME);
		s.setSpan(
				new TypefaceSpan(this, FONT.substring(0, FONT.lastIndexOf('.'))),
				0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		// Update the action bar title with the TypefaceSpan instance
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(s);
	}// Ends setTitleBar method

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOK:
			startActivity(new Intent(this, MainPage.class));
			break;
		}
	}
}
 