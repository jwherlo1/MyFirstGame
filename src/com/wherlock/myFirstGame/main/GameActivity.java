package com.wherlock.myFirstGame.main;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class GameActivity extends Activity {
	
	private static final String TAG = GameActivity.class.getSimpleName();
	
	private GameView gameView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        gameView = new GameView(this);
        setContentView(gameView);
    }

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// Ignore screen orientation changes
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "Paused...");
		super.onPause();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "Resumed");
		super.onResume();
	}
	
}