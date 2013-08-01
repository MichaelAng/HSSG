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

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionList extends Activity implements OnClickListener {
	// CONSTANTS
	String FONT = "starjout.ttf";
	String TITLEPAGENAME = "Choose Your Question or...";

	Topic topic;
	String[] questions;
	int topicPosition;
	List<String> name = new ArrayList<String>();
	Typeface type;
	Button takeItTop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_name_activity);
		// type = Typeface.createFromAsset(getAssets(), FONT);

		setTitleBar();
		takeItTop = (Button) findViewById(R.id.bTakeItTop);
		takeItTop.setOnClickListener(this);
		setButtonFont();
		addTopicToList();
		populateListView();
		registerClickCallback();
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
	
	private void setButtonFont() {// Sets the Font for the buttons
		type = Typeface.createFromAsset(getAssets(), FONT);
		takeItTop.setTypeface(type);
	}// Ends setButtonFont method

	private void addTopicToList() {
		// Gets the extra data from previous activity
		Bundle b = getIntent().getExtras();
		topic = (Topic) b.getParcelable("topic");
		topicPosition = b.getInt("topicPosition", 0);
		for (int i = 0; i < topic.getQuestion().size(); i++)
			name.add(topic.getQuestion().get(i).getQuestionTitle());
	}// Ends addTopicToListMethod

	private void populateListView() {
		ArrayAdapter<String> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.questionListView);
		list.setAdapter(adapter);
	}// Ends populateListView

	private void registerClickCallback() {// Sets up listeners for item clicked
		ListView list = (ListView) findViewById(R.id.questionListView);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {

				Intent intentToOpenQuestionPage = new Intent(QuestionList.this,
						QuestionPage.class);
				intentToOpenQuestionPage.putExtra("topic", topic);
				intentToOpenQuestionPage.putExtra("topicPosition",
						topicPosition);
				intentToOpenQuestionPage.putExtra("questionPosition", position);
				startActivity(intentToOpenQuestionPage);
			}
		});// Ends onItemClick method
	}// Ends registerClickCallback method

	@Override
	public void onBackPressed() {// Restatcks the return function
		Intent backToMA = new Intent(this, TopicPage.class);
		backToMA.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(backToMA);
	}// Ends onBackPressed method

	// Custom List Adapter
	private class MyListAdapter extends ArrayAdapter<String> {
		Typeface font;

		public MyListAdapter() {
			super(QuestionList.this, R.layout.item_view_topic, name);
			font = Typeface.createFromAsset(getAssets(), "starjout.ttf");
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Makes sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(
						R.layout.item_view_topic, parent, false);
			}
			// Find the car to work with
			String currentString = name.get(position);

			// Topic
			TextView topicText = (TextView) itemView
					.findViewById(R.id.item_topic_name);
			topicText.setTypeface(font);
			topicText.setText(currentString);

			return itemView;
		}//Ends getView method
	}//Ends MyListAdapter Class

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bTakeItTop:
			Intent intentToOpenQuestionPage = new Intent(QuestionList.this,
					QuestionPage.class);
			intentToOpenQuestionPage.putExtra("topic", topic);
			intentToOpenQuestionPage.putExtra("topicPosition",
					topicPosition);
			intentToOpenQuestionPage.putExtra("questionPosition", 0);
			intentToOpenQuestionPage.putExtra("TakeItTop", true);
			startActivity(intentToOpenQuestionPage);
			break;
		}
	}
}//Ends QuestionList Class