package com.example.chemistry;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AboutPage extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

}
