package com.wherlock.myFirstGame.model.ai;

import android.view.MotionEvent;

import com.wherlock.myFirstGame.model.GameObject;

public class LaserAI implements AI {

	private GameObject laser;

	public LaserAI(GameObject laser) {
		this.laser = laser;
	}

	@Override
	public void update(MotionEvent event) {
		if (laser.isVisible() == true) {

			laser.setY(laser.getY() - laser.getHeight() / 2);

			if (laser.getY() <= 0) {
				laser.setVisible(false);
			}
		}
	}

}
