package com.wherlock.myFirstGame.components;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameObject {

	private Bitmap bitmap;
	
	private Speed speed;
	
	private int x;	
	private int y;
	
	private boolean touched;
	private boolean visible;

	private InputSource inputSource;
	
	public GameObject(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		speed = new Speed();
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
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
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
	}
	
	public Speed getSpeed() {
		return speed;
	}

	public void update() {
		if (!touched) {
			x += speed.getXv() * speed.getxDirection();
			y += speed.getYv() * speed.getyDirection();
		}
	}

	public void setInputSource(InputSource inputSource) {
		this.inputSource = inputSource;
	}
	
	
}
