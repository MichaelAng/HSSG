/************************************************************************
 *   Copyright 2013 Derek Li, Michael Ang
 *
 *   This file is part of The High School Study Guide (HSSG).
 *
 *   HSSG is free software created by Seidenberg Creative Laboratory 
 *   for non-commercial use.
 *   
 *   Github account: https://github.com/MichaelAng/HSSG
 ************************************************************************/

package com.example.chemistry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TopicPage extends Activity {
	// CONSTANTS
	String FONT = "starjout.ttf";
	String TITLEPAGENAME = "Select a Topic Below";
	private String fileName;

	// Prepares a list of Topics each containing a list of Questions
	List<Topic> topic = new ArrayList<Topic>();
	List<Question> question = new ArrayList<Question>();
	List<String> name = new ArrayList<String>();

	// For my File code
	File file = null;
	File path = null;
	File defaultFile = null;
	String state;
	StringBuilder text;
	BufferedReader reader;
	Typeface type;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topic_activity);

		setTitleBar();
		try {
			openAndProcessFile();
			breakUpAssignList();
			addTopicToList();
			populateListView();
			registerClickCallback();
		} catch (Exception e) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Corrupt File. use Default.txt or Download a correct file.",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}
	}




	public void writeFile(String sBody) {

		try {
			File root = new File(Environment.getExternalStorageDirectory(),
					"Download");

			if (!root.exists()) {
				root.mkdirs();
			}

			File gpxfile = new File(root, "Default.txt");

			BufferedWriter bW = new BufferedWriter(new FileWriter(gpxfile,
					false));
			bW.write(sBody);
			bW.newLine();
			bW.flush();
			bW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setTitleBar() {// Sets the Title Bar Name and Font
		SpannableString s = new SpannableString(TITLEPAGENAME);
		s.setSpan(
				new TypefaceSpan(this, MainPage.FONT.substring(0,
						MainPage.FONT.lastIndexOf('.'))), 0, s.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		// Update the action bar title with the TypefaceSpan instance
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(s);
	}// Ends setTitleBar method

	private void openAndProcessFile() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			if (!path.exists()) {
				path.mkdirs();
			}
			defaultFile = new File(path, "Default.txt");
			
			if (!defaultFile.exists() || defaultFile.length() == 0)
				writeFile(getResources().getString(R.string.defaultText));	
			
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
			fileName= preferences.getString("fileName", Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/Default.txt");	
			
			file = new File(fileName); 

			try {
				reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] RowData = line.split("@");
					question.add(new Question(RowData[0], RowData[1],
							RowData[2], RowData[3], RowData[4], RowData[5],
							RowData[6], RowData[7], RowData[8]));
				}// Ends while loop
			} catch (IOException ex) {
				Log.i("FAILED TO OPEN", "Failed to open the file");
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					Log.i("FAILED TO CLOSE", "Failed to close the file");
				}// Ends try/catch for finally
			}// Ends try/catch for the main
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Unable to access SD Card. Please turn on SD Card.",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.show();
		}// Ends if/else statement
	}// Ends openAndProcessFile method

	private void breakUpAssignList() {
		String questionTopic;
		int pointer = 0;
		for (int i = 0; i < question.size(); i++) {
			questionTopic = question.get(i).getTopic();
			if (i + 1 < question.size()) {
				if (questionTopic.equals(question.get(i + 1).getTopic()) != true) {
					topic.add(new Topic(question.subList(pointer, i + 1)));
					pointer = i + 1;
				}// ends if statement
			} else {
				topic.add(new Topic(question.subList(pointer, i + 1)));
			}// ends if/else statement
		}// ends for loop
	}// Ends breakUpAssignList method

	private void addTopicToList() {
		for (int i = 0; i < topic.size(); i++)
			name.add(topic.get(i).getQuestion().get(0).getTopic());
	}// Ends addTopicToListMethod

	private void populateListView() {
		ArrayAdapter<String> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.topicListView);
		list.setAdapter(adapter);
	}// Ends populateListView

	private void registerClickCallback() {// Sets up listeners for item clicked
		ListView list = (ListView) findViewById(R.id.topicListView);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {

				Intent intent = new Intent(TopicPage.this, QuestionList.class);
				intent.putExtra("topic", topic.get(position));
				intent.putExtra("topicPosition", position);
				startActivity(intent);
			}
		});// Ends onItemClick method
	}// Ends registerClickCallback method

	@Override
	public void onBackPressed() {// Restatcks the return function
		Intent backToMA = new Intent(this, MainPage.class);
		backToMA.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(backToMA);
	}// Ends onBackPressed method
	
	// Custom List Adapter
	private class MyListAdapter extends ArrayAdapter<String> {
		Typeface font;

		public MyListAdapter() {
			super(TopicPage.this, R.layout.item_view_topic, name);
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
		}// Ends getView method
	}// Ends MyListAdapter Class
}// Ends TopicPage Class
