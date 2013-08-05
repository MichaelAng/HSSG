package com.example.chemistry;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TutorialPage extends Activity implements OnClickListener {
	String FONT = "starjout.ttf";
	Button bOK;
	TextView tvText;
	Typeface type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tutorial_activity);	
		
		bOK = (Button) findViewById(R.id.bOK);
		bOK.setOnClickListener(this);
		tvText = (TextView) findViewById(R.id.tvText);
		tvText.setMovementMethod(new ScrollingMovementMethod());
		setButtonFont();
	}

	private void setButtonFont() {// Sets the Font for the buttons
		type = Typeface.createFromAsset(getAssets(), FONT);
		bOK.setTypeface(type);
		tvText.setTypeface(type);
	}// Ends setButtonFont method
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOK:
			startActivity(new Intent(this, MainPage.class));
			break;
		}
	}
}
 