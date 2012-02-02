package com.wherlock.myFirstGame.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

import com.wherlock.myFirstGame.R;
import com.wherlock.myFirstGame.components.World;
import com.wherlock.myFirstGame.components.input.HumanInputSource;

public class Game extends Thread {
	
	private static final String TAG = Game.class.getSimpleName();
	
	private final static int MAX_FPS = 50;
	private final static int MAX_FRAME_SKIPS = 5;
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;
	
	private GameView mainGameView;
	private SurfaceHolder surfaceHolder;
	private ResourceManager resourceManager;
	private World gameWorld;
	
	private long statPeriod = 0;
	private int totalFramesSkipped;

	private boolean running;
	
	public Game(GameView mainGameView, SurfaceHolder surfaceHolder) {
		
		resourceManager = new ResourceManager(mainGameView);
		gameWorld = new World();
		this.mainGameView = mainGameView;
		this.surfaceHolder = surfaceHolder;
		
		initialiseGameWorld();
	}
	
	public void setRunning(boolean running) {
		this.running = running;
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
					
					drawWorld(canvas);
					
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

	private void drawWorld(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		gameWorld.render(canvas);
	}
	
	private void initialiseGameWorld() {
		gameWorld.setWorldBoundary(mainGameView.getWidth(), mainGameView.getHeight());
		
		HumanInputSource playerInput = new HumanInputSource();
		mainGameView.addTouchEventListener(playerInput);
		
		gameWorld.createPlayerSpaceShip(resourceManager.getBitmap(R.drawable.spaceship), playerInput);
		gameWorld.createLasers(resourceManager.getBitmap(R.drawable.laser));
		gameWorld.createAliens(resourceManager.getBitmap(R.drawable.alien));
	}

	private void updateStats(int framesSkipped) {

		totalFramesSkipped += framesSkipped;
		
		if (System.currentTimeMillis() - statPeriod >= 1000) {
	
			Log.d(TAG, "Average FPS = " + (int)(MAX_FPS - totalFramesSkipped));
			
			totalFramesSkipped = 0;
			statPeriod = System.currentTimeMillis();
		}
	}
	
}
