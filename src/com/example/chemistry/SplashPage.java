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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;

public class SplashPage extends Activity {
	public MediaPlayer ourSong;
	boolean tutorial, music, shorterTime; 

	@Override
	protected void onCreate(Bundle Splash) {
		super.onCreate(Splash);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_activity);
		ourSong = MediaPlayer.create(this, R.raw.preview);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		music = getPrefs.getBoolean("music", true);
		tutorial = getPrefs.getBoolean("tutorial", true);
		shorterTime = getPrefs.getBoolean("short", false);
		
		if (music == true)
			ourSong.start();
		
		Thread timer = new Thread() {
			public void run() {
				try {
					if (shorterTime == true)
						sleep(1000);
					else
						sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (tutorial == true)
						startActivity(new Intent(SplashPage.this, TutorialPage.class));
					else
						startActivity(new Intent(SplashPage.this, MainPage.class));
				}
			}// End of run
		};// End of thread
		timer.start();
	}// End of onCreate

	@Override
	protected void onPause() {
		super.onPause();
		ourSong.release();
		finish();
	}

}// End of Class