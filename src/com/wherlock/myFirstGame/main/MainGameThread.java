package com.wherlock.myFirstGame.main;

import com.wherlock.myFirstGame.model.GameWorld;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainGameThread extends Thread {
	
	private static final String TAG = MainGameThread.class.getSimpleName();
	
	private final static int MAX_FPS = 50;
	private final static int MAX_FRAME_SKIPS = 5;
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;
	
	private MainGameView mainGameView;
	private SurfaceHolder surfaceHolder;
	private GameWorld gameWorld;
	
	private long statPeriod = 0;
	private int totalFramesSkipped;

	private boolean running;
	
	public MainGameThread(GameWorld gameWorld, MainGameView mainGameView, SurfaceHolder surfaceHolder) {
		this.gameWorld = gameWorld;
		this.mainGameView = mainGameView;
		this.surfaceHolder = surfaceHolder;
	}

	@Override
	public void run() {
		Log.d(TAG, "Started main game thread");
		
		Canvas canvas;
		
		long beginTime;
		long timeDiff;
		int sleepTime;
		int framesSkipped;
		
		while (running) {
			canvas = null;

			try {
				canvas = surfaceHolder.lockCanvas();				
				synchronized (surfaceHolder) {
					
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;
					
					gameWorld.updateGameState();
					mainGameView.renderGameImage(canvas);
					
					timeDiff = System.currentTimeMillis() - beginTime;
					sleepTime = (int)(FRAME_PERIOD - timeDiff);

					if (sleepTime > 0) {
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							Log.d(TAG, "Sleep interrupted!");
						}
					}
					
					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {					
						gameWorld.updateGameState();
						
						sleepTime += FRAME_PERIOD;
						framesSkipped++;
					}
					
					updateStats(framesSkipped);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
		
		Log.d(TAG, "Stopped main game thread");
	}
	
	private void updateStats(int framesSkipped) {

		totalFramesSkipped += framesSkipped;
		
		if (System.currentTimeMillis() - statPeriod >= 1000) {
	
			Log.d(TAG, "Average FPS = " + (int)(MAX_FPS - totalFramesSkipped));
			
			totalFramesSkipped = 0;
			statPeriod = System.currentTimeMillis();
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
}
