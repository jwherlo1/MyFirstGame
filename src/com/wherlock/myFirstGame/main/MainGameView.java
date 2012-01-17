package com.wherlock.myFirstGame.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.wherlock.myFirstGame.R;
import com.wherlock.myFirstGame.model.GameWorld;

public class MainGameView extends SurfaceView implements SurfaceHolder.Callback {
	
	private static final String TAG = MainGameView.class.getSimpleName();
	
	private MainGameThread gameThread;
	private GameWorld gameWorld;
	
	//private Sprite spaceShipSprite;

	public MainGameView(Context context, GameWorld gameWorld) {
		super(context);
		
		this.gameWorld = gameWorld;
		
		loadSprites();
		listenToSurfaceEvents();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameWorld.setWorldBoundary(getWidth(), getHeight());
		gameThread = new MainGameThread(gameWorld, this, getHolder());
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {		
			gameWorld.downPressed((int)event.getX(), (int)event.getY());
			checkForQuit(event.getY());
			
			Log.d(TAG, "CoordX = " + event.getX() + ", CoordY = " + event.getY());
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			gameWorld.movePressed((int)event.getX(), (int)event.getY());
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
			gameWorld.upPressed((int)event.getX(), (int)event.getY());
		}
		
		return true;
	}

	protected void renderGameImage(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		gameWorld.render(canvas);
	}

	private void loadSprites() {
		gameWorld.setSpaceShipSprite(BitmapFactory.decodeResource(getResources(), R.drawable.spaceship));
		gameWorld.setLasersSprites(BitmapFactory.decodeResource(getResources(), R.drawable.laser));
		gameWorld.setAliensSprites(BitmapFactory.decodeResource(getResources(), R.drawable.alien));
	}

	private void listenToSurfaceEvents() {
		getHolder().addCallback(this);
		setFocusable(true);
	}

	private void checkForQuit(float yPos) {
		if ((yPos / this.getHeight()) * 100 < 30) {
			gameThread.setRunning(false);
			((Activity)getContext()).finish();
		}
	}

}
