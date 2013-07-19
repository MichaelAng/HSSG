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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ScorePage extends Activity implements OnClickListener {
	private List<Score> score = new ArrayList<Score>();
	private double point;
	Button clearScore;

	// For my File code
	File file = null;
	File path = null;
	BufferedReader reader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.points_activity);

		clearScore = (Button) findViewById(R.id.bClearScore);
		clearScore.setOnClickListener(this);

		openFile();
		populateListView();
	}

	private void openFile() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			path.mkdirs();
			file = new File(path, "score.txt");
			try {
				reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] RowData = line.split("@");

					score.add(new Score(RowData[0], Integer
							.parseInt(RowData[1]),
							Integer.parseInt(RowData[2]), RowData[3]));
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

	private void populateListView() {
		ArrayAdapter<Score> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.scoreListView);
		list.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bClearScore:
			writeScore("Topic@1@1@Date");

			finish();
			startActivity(getIntent());
			break;
		}

	}

	public void writeScore(String sBody) {

		try {
			File root = new File(Environment.getExternalStorageDirectory(),
					"Download");

			if (!root.exists()) {
				root.mkdirs();
			}

			File gpxfile = new File(root, "score.txt");

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

	private class MyListAdapter extends ArrayAdapter<Score> {
		public MyListAdapter() {
			super(ScorePage.this, R.layout.item_view, score);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Makes sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.item_view,
						parent, false);
			}
			// Find the car to work with
			Score currentScore = score.get(position);

			// Topic
			TextView topicText = (TextView) itemView
					.findViewById(R.id.item_topic);
			topicText.setText(currentScore.getTopic());

			// Date
			TextView dateText = (TextView) itemView
					.findViewById(R.id.item_date);
			dateText.setText(currentScore.getDateTime());

			// Score
			TextView scoreText = (TextView) itemView
					.findViewById(R.id.item_score);

			point = ((double) currentScore.getAmountCorrect() / (double) currentScore
					.getTotalQuestionAnswered()) * 100;
			scoreText.setText(String.format("%.1f", point) + "%");

			return itemView;
		}
	}

}