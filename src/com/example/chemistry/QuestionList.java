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
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class QuestionList extends ListActivity {
	Topic topic;
	String[] questions;
	int topicPosition;
	List<String> name;
	Typeface type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Sets the Title Bar Font
		SpannableString s = new SpannableString("My Title");
		s.setSpan(new TypefaceSpan(this, "starjout"), 0, s.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		// Update the action bar title with the TypefaceSpan instance
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(s);
		
		// Gets the extra data from previous activity
		Bundle b = getIntent().getExtras();
		topic = (Topic) b.getParcelable("topic");
		topicPosition = b.getInt("topicPosition", 0);
		name = new ArrayList<String>();
		type = Typeface.createFromAsset(getAssets(), "starjout.ttf");

		for (int i = 0; i < topic.getQuestion().size(); i++) {
			name.add(topic.getQuestion().get(i).getQuestionTitle());
		}

		//what the fuck happende
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, name) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView textView = (TextView) view
						.findViewById(android.R.id.text1);
				textView.setTypeface(type);
				return view;
			}
		};
		/* SET THE ADAPTER TO LISTVIEW */
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent intentToOpenQuestionPage = new Intent(this, QuestionPage.class);
		intentToOpenQuestionPage.putExtra("topic", topic);
		intentToOpenQuestionPage.putExtra("topicPosition", topicPosition);
		intentToOpenQuestionPage.putExtra("questionPosition", position);
		startActivity(intentToOpenQuestionPage);
	}

	@Override
	public void onBackPressed() {
		Intent backToMA = new Intent(this, TopicPage.class);
		backToMA.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(backToMA);
	}

}