package com.wherlock.myFirstGame.components;

import com.wherlock.myFirstGame.components.ai.AI;
import com.wherlock.myFirstGame.components.input.InputSource;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class GameObject {

	private Bitmap bitmap;

	private int x;
	private int y;

	private boolean touched;
	private boolean visible;

	private InputSource inputSource;
	private AI ai;

	public GameObject(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setInputSource(InputSource inputSource) {
		this.inputSource = inputSource;
	}

	public void setAI(AI ai) {
		this.ai = ai;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2),
				y - (bitmap.getHeight() / 2), null);
	}

	public void update() {
		if (inputSource != null && ai != null) {
			
			MotionEvent event = inputSource.getNextMove();
			ai.update(event);
		}
	}
}
