package com.wherlock.myFirstGame.components.ai;

import com.wherlock.myFirstGame.components.GameObject;

import android.view.MotionEvent;

public class LaserAI implements AI {

	private GameObject laser;

	public LaserAI(GameObject laser) {
		this.laser = laser;
	}

	@Override
	public void update(MotionEvent event) {
		if (laser.isVisible() == true) {

			laser.setY(laser.getY() - laser.getBitmap().getHeight() / 2);

			if (laser.getY() >= 0) {
				laser.setVisible(false);
			}
		}
	}

}
