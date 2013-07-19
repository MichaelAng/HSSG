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

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QuestionList extends ListActivity {
	Topic topic; 
	String[] questions;
	int topicPosition;	
	String[] q;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Gets the extra data from previous activity
		Bundle b = getIntent().getExtras();
		topic = (Topic) b.getParcelable("topic");
		topicPosition = b.getInt("topicPosition", 0);
		
		
		
		q = new String[topic.getQuestion().size()];
		for(int i = 0; i < topic.getQuestion().size();i++){
			q[i] = topic.getQuestion().get(i).getQuestionTitle();
			Log.i("getTopic"," " + topic.getQuestion().get(i).getTopic());
		}		
			
		//Creates a list adapter of questions
		setListAdapter(new ArrayAdapter<String>(QuestionList.this,
				android.R.layout.simple_list_item_1, q));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	
		Intent intentToOpenQuestionPage = new Intent(this,QuestionPage.class);
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