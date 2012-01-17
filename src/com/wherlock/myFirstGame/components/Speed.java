package com.wherlock.myFirstGame.components;

public class Speed {	

	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_LEFT  = -1;
	public static final int DIRECTION_UP    = -1;
	public static final int DIRECTION_DOWN  = 1;
	
	private float xv;
	private float yv;
	
	private int xDirection = DIRECTION_RIGHT;
	private int yDirection = DIRECTION_UP;
	
	public Speed () {
		xv = 2;
		yv = 2;
	}
	
	public Speed(float xv, float yv) {
		this.xv = xv;
		this.yv = yv;
	}

	public float getXv() {
		return xv;
	}

	public void setXv(float xv) {
		this.xv = xv;
	}

	public float getYv() {
		return yv;
	}

	public void setYv(float yv) {
		this.yv = yv;
	}

	public int getxDirection() {
		return xDirection;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	public void toggleXDirection() {
		xDirection = xDirection * -1;
	}
	
	public void toggleYDirection() {
		yDirection = yDirection * -1;
	}
}
