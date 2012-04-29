package com.wherlock.myFirstGame.model.input;

import android.view.MotionEvent;


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
