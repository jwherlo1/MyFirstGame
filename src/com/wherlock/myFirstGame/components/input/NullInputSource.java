package com.wherlock.myFirstGame.components.input;

import android.view.MotionEvent;

import com.wherlock.myFirstGame.main.TouchEventListener;

public class NullInputSource implements InputSource, TouchEventListener {
	
	@Override
	public void onTouchEvent(MotionEvent event) {
		// Do nothing
	}

	@Override
	public MotionEvent getNextMove() {
		// Do nothing
		return null;
	}

}
