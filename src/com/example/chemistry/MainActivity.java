package com.example.chemistry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	Button multi, shortans, points, regents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		multi = (Button) findViewById(R.id.multi_choice);
		shortans = (Button) findViewById(R.id.short_answers);
		points = (Button) findViewById(R.id.points);
		regents = (Button) findViewById(R.id.full_regents);

		multi.setOnClickListener(this);
		shortans.setOnClickListener(this);
		points.setOnClickListener(this);
		regents.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.multi_choice:
			startActivity(new Intent("com.example.chemistry.Topics"));
			break;
		case R.id.short_answers:
		//	startActivity(new Intent("com.example.chemistry.ShortAnswers"));
			break;
		case R.id.points:
		//	startActivity(new Intent("com.example.chemistry.Points"));
			break;
		case R.id.full_regents:
		//	startActivity(new Intent("com.example.chemistry.FullRegents"));
			break;
		}
	}
}
