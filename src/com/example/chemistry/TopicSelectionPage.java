package com.example.chemistry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TopicSelectionPage extends ListActivity{

	Button atom, colligative, acid, organic;
	Intent intent;
	
	//Topic topic;
	List<Topic> topic = new ArrayList<Topic>();
	List<Question> question = new ArrayList<Question>();
	
	// For my File code
	File file = null;
	File path = null;
	String state;
	StringBuilder text;
	BufferedReader reader;
	String[] t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		openFile();
		breakUpAssignList();
		
		t = new String[topic.size()];
		
		for(int i = 0; i < topic.size();i++){
			t[i] = topic.get(i).getQuestion().get(0).getTopic();
		}	
		setListAdapter(new ArrayAdapter<String>(TopicSelectionPage.this,
				android.R.layout.simple_list_item_1, t));

	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(this, QuestionList.class);
		intent.putExtra("topic", topic.get(position));
		startActivity(intent);
	}

	private void openFile() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			path.mkdirs();
			file = new File(path, "Default.txt");
			try {
				reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {

						String[] RowData = line.split("@");

						question.add(new Question(RowData[0],
								RowData[1],RowData[2],
								RowData[7],RowData[8],
								RowData[3],RowData[4],
								RowData[5],RowData[6]));					
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
		}
	}
	
	private void breakUpAssignList() {		
		String questionTopic;
		int pointer = 0;
		for(int i = 0;i < question.size(); i++){
			questionTopic = question.get(i).getTopic();
			if(i+1 < question.size()){
				if (questionTopic.equals(question.get(i+1).getTopic()) != true){
					Log.i("pointer",pointer + " "+ i);
					topic.add(new Topic(question.subList(pointer,i+1)));
					pointer = i+1;
				}
			}else{
				topic.add(new Topic(question.subList(pointer,i+1)));
			}
		}

	}

	// File Code
//	private void openFile() {
//		// Checks if file is present
//
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {
//			Log.i("Read", "able to read card");
//			path = Environment
//					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//			path.mkdirs();
//			file = new File(path, "Default.txt");
//			text = new StringBuilder();
//
//			try {
//				BufferedReader br = new BufferedReader(new FileReader(file));
//				String line;
//
//				while ((line = br.readLine()) != null) {
//					text.append(line);
//					text.append('\n');
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//				Log.i("FAILED TO OPEN", "Failed to open the file");
//			}
//			Log.i("text", text.toString());
//		}
//	}

	// Naive Button clicking
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.atoms:
	// MultipleChoiceClass atom = new MultipleChoiceClass("atoms",
	// getResources().getStringArray(R.array.atomTitleQuestion),
	// getResources().getStringArray(R.array.atomQuestion),
	// getResources().getStringArray(R.array.atomAnswer),
	// getResources().getStringArray(R.array.atomExplaination),
	// getResources().getStringArray(R.array.atomChoiceA),
	// getResources().getStringArray(R.array.atomChoiceB),
	// getResources().getStringArray(R.array.atomChoiceC),
	// getResources().getStringArray(R.array.atomChoiceD));
	//
	// // Sets up intent to pass MultipleChoiceClass to next Activity
	// intent = new Intent(this, QuestionList.class);
	// intent.putExtra("topic", atom);
	// startActivity(intent);
	// break;
	// case R.id.colligative:
	// MultipleChoiceClass colligative = new MultipleChoiceClass(
	// "colligative", getResources().getStringArray(
	// R.array.collTitleQuestion), getResources()
	// .getStringArray(R.array.collQuestion),
	// getResources().getStringArray(R.array.collAnswer),
	// getResources().getStringArray(R.array.collExplaination),
	// getResources().getStringArray(R.array.collChoiceA),
	// getResources().getStringArray(R.array.collChoiceB),
	// getResources().getStringArray(R.array.collChoiceC),
	// getResources().getStringArray(R.array.collChoiceD));
	//
	// // Sets up intent to pass MultipleChoiceClass to next Activity
	// intent = new Intent(this, QuestionList.class);
	// intent.putExtra("topic", colligative);
	// startActivity(intent);
	// break;
	// case R.id.acid:
	// MultipleChoiceClass acid = new MultipleChoiceClass("acid",
	// getResources().getStringArray(R.array.acidTitleQuestion),
	// getResources().getStringArray(R.array.acidQuestion),
	// getResources().getStringArray(R.array.acidAnswer),
	// getResources().getStringArray(R.array.acidExplaination),
	// getResources().getStringArray(R.array.acidChoiceA),
	// getResources().getStringArray(R.array.acidChoiceB),
	// getResources().getStringArray(R.array.acidChoiceC),
	// getResources().getStringArray(R.array.acidChoiceD));
	//
	// // Sets up intent to pass MultipleChoiceClass to next Activity
	// intent = new Intent(this, QuestionList.class);
	// intent.putExtra("topic", acid);
	// startActivity(intent);
	// break;
	// case R.id.organic:
	// MultipleChoiceClass organic = new MultipleChoiceClass("organic",
	// getResources().getStringArray(R.array.orgTitleQuestion),
	// getResources().getStringArray(R.array.orgQuestion),
	// getResources().getStringArray(R.array.orgAnswer),
	// getResources().getStringArray(R.array.orgExplaination),
	// getResources().getStringArray(R.array.orgChoiceA),
	// getResources().getStringArray(R.array.orgChoiceB),
	// getResources().getStringArray(R.array.orgChoiceC),
	// getResources().getStringArray(R.array.orgChoiceD));
	//
	// // Sets up intent to pass MultipleChoiceClass to next Activity
	// intent = new Intent(this, QuestionList.class);
	// intent.putExtra("topic", organic);
	// startActivity(intent);
	// break;
	// }
	// }
}
