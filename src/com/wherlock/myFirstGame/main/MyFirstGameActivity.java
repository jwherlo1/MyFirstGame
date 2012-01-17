package com.wherlock.myFirstGame.main;

import com.wherlock.myFirstGame.model.GameWorld;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MyFirstGameActivity extends Activity {
	
	private static final String TAG = MyFirstGameActivity.class.getSimpleName();
	
	private MainGameView gameView;
	private GameWorld gameWorld;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        gameWorld = new GameWorld();
        
        gameView = new MainGameView(this, gameWorld);
        setContentView(gameView);
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
	public void onConfigurationChanged(Configuration newConfig) {
		// Ignore screen orientation changes
		super.onConfigurationChanged(newConfig);
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