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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TopicPage extends ListActivity {
	
	//Prepares a list of Topics each containing a list of Questions
	List<Topic> topic = new ArrayList<Topic>();
	List<Question> question = new ArrayList<Question>();
	List<String> name;
	
	// For my File code
	private String FILENAME = "Default.txt";
	File file = null;
	File path = null;
	String state;
	StringBuilder text;
	BufferedReader reader;
	Typeface type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		name = new ArrayList<String>();
		type = Typeface.createFromAsset(getAssets(),"starjout.ttf"); 
		
		//Sets the Title Bar Font
	    SpannableString s = new SpannableString("My Title");
	    s.setSpan(new TypefaceSpan(this, "starjout"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	 
	    // Update the action bar title with the TypefaceSpan instance
	    ActionBar actionBar = getActionBar();
	    actionBar.setTitle(s);
		
		openAndProcessFile();
		breakUpAssignList();
		
		for (int i = 0; i < topic.size(); i++) {
			name.add(topic.get(i).getQuestion().get(0).getTopic());
		}
		 ArrayAdapter<String> adapter=new ArrayAdapter<String>(
		            this,android.R.layout.simple_list_item_1, name){

		        @Override
		        public View getView(int position, View convertView,
		                ViewGroup parent) {
		            View view =super.getView(position, convertView, parent);
		            
		            TextView textView=(TextView) view.findViewById(android.R.id.text1);

		    		textView.setTypeface(type);

		            return view;
		        }
		    };
		        /*SET THE ADAPTER TO LISTVIEW*/
		        setListAdapter(adapter);

	}

	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent intent = new Intent(this, QuestionList.class);
		intent.putExtra("topic", topic.get(position));
		intent.putExtra("topicPosition", position);
		startActivity(intent);
	}

	private void openAndProcessFile() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			path.mkdirs();
			file = new File(path, FILENAME);
			try {
				reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {

					String[] RowData = line.split("@");

					question.add(new Question(RowData[0], RowData[1],
							RowData[2], RowData[3], RowData[4], RowData[5],
							RowData[6], RowData[7], RowData[8]));
				}

			} catch (IOException ex) {
				Log.i("FAILED TO OPEN", "Failed to open the file");
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					Log.i("FAILED TO CLOSE", "Failed to close the file");
				}
			}
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Unable to access SD Card. Please turn on SD Card.",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
	}

	private void breakUpAssignList() {
		String questionTopic;
		int pointer = 0;
		for (int i = 0; i < question.size(); i++) {
			questionTopic = question.get(i).getTopic();
			if (i + 1 < question.size()) {
				if (questionTopic.equals(question.get(i + 1).getTopic()) != true) {
					topic.add(new Topic(question.subList(pointer, i + 1)));
					pointer = i + 1;
				}
			} else {
				topic.add(new Topic(question.subList(pointer, i + 1)));
			}
		}

	}

	@Override
	public void onBackPressed() {
		Intent backToMA = new Intent(this, MainPage.class);
		backToMA.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(backToMA);
	}
	
}
