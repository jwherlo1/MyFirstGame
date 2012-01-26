package com.wherlock.myFirstGame.components;

import com.wherlock.myFirstGame.main.TouchEventListener;

import android.view.MotionEvent;

public class HumanInputSource implements InputSource, TouchEventListener {
	
	private MotionEvent nextMove;
	private boolean moveValid = false;
	
	@Override
	public MotionEvent getNextMove() {
		if (moveValid) {
			moveValid = false;
			return nextMove;
		}
		
		return null;
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		moveValid = true;
		nextMove = event;
	}

}
