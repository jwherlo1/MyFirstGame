package com.wherlock.myFirstGame.main;

import java.util.ArrayList;
import java.util.List;

import com.wherlock.myFirstGame.model.input.TouchEventListener;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	
	private static final String TAG = GameView.class.getSimpleName();
	
	private GameLoop gameThread;
	
	private List<TouchEventListener> touchEventListeners = new ArrayList<TouchEventListener>();
	
	public GameView(Context context) {
		super(context);
		
		listenToSurfaceEvents();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// Ignore
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameThread = new GameLoop(this, getHolder());
		gameThread.setRunning(true);
		gameThread.start();		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		
		Log.d(TAG, "Killing game thread...");
		gameThread.setRunning(false);
		
		while (retry) {
			try {
				gameThread.join();
				retry = false;
			} catch (InterruptedException e) {
				Log.d(TAG, "Interrupted trying to kill game thread!");
			}
		}
		
		Log.d(TAG, "Game thread dead");
	}

	public void addTouchEventListener(TouchEventListener listener) {
		touchEventListeners.add(listener);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		checkForQuit(event);
		
		for (TouchEventListener listener : touchEventListeners) {
			listener.onTouchEvent(event);
		}		
		
		return true;
	}

	private void listenToSurfaceEvents() {
		getHolder().addCallback(this);
		setFocusable(true);
	}

	private void checkForQuit(MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {		
			
			if ((event.getY() / this.getHeight()) * 100 < 30) {
				gameThread.setRunning(false);
				((Activity)getContext()).finish();
			}
			
			Log.d(TAG, "CoordX = " + event.getX() + ", CoordY = " + event.getY());
		}
	}

}
