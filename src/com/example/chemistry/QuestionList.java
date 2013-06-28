package com.example.chemistry;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QuestionList extends ListActivity {
	Topic topic; 
	String[] questions;
	
	String[] q;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Gets the extra data from previous activity
		Bundle b = getIntent().getExtras();
		topic = (Topic) b.getParcelable("topic");
		
		q = new String[topic.getQuestion().size()];
		for(int i = 0; i < topic.getQuestion().size();i++){
			q[i] = topic.getQuestion().get(i).getQuestionTitle();
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
		intentToOpenQuestionPage.putExtra("questionPosition", position);
		startActivity(intentToOpenQuestionPage);
	}

}