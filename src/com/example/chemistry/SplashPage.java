package com.example.chemistry;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

public class SplashPage extends Activity {
	public MediaPlayer ourSong;

	@Override
	protected void onCreate(Bundle Splash) {
		super.onCreate(Splash);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_activity);
		ourSong = MediaPlayer.create(this, R.raw.preview);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		
		if (music == true)
			ourSong.start();
		
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
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