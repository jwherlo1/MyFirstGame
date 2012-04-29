package com.wherlock.myFirstGame.model.ai;

import android.view.MotionEvent;

import com.wherlock.myFirstGame.model.GameObject;
import com.wherlock.myFirstGame.model.World;

public class SpaceShipAI implements AI {

	private GameObject playerSpaceShip;
	private World world;
	
	public SpaceShipAI(GameObject playerSpaceShip, World world) {
		this.playerSpaceShip = playerSpaceShip;
		this.world = world;
	}
	
	@Override
	public void update(MotionEvent event) {
		
		if (event == null) {
			return;
		}
		
		int x = (int) event.getX();
		int y = (int) event.getY();

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			handleMoveAction(x);
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			handleUpAction(x);	
		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {
			handleDownAction(x, y);
		}
	}

	private void handleDownAction(int x, int y) {
		if (x >= playerSpaceShip.getX() - bitmapHalfWidth()
				&& x <= playerSpaceShip.getX() + bitmapHalfWidth()) {
			if (y >= playerSpaceShip.getY() - (bitmapHalfHeight())
					&& y <= playerSpaceShip.getY() + (bitmapHalfHeight())) {
				playerSpaceShip.setTouched(true);
			} else {
				playerSpaceShip.setTouched(false);
			}
		} else {
			playerSpaceShip.setTouched(false);
		}
	}

	private void handleMoveAction(int x) {
		if (playerSpaceShip.isTouched()) {
	
			if (x - bitmapHalfWidth() <= 0) {
				x = bitmapHalfWidth();
			} else if (x + bitmapHalfWidth() >= world.getWorldWidth()) {
				x = world.getWorldWidth() - bitmapHalfWidth();
			}
			
			playerSpaceShip.setX(x);
		}
	}

	private void handleUpAction(int x) {
		if (playerSpaceShip.isTouched()) {
			playerSpaceShip.setTouched(false);
		}
		
		if (x > playerSpaceShip.getX() - bitmapHalfWidth()
				&& x < playerSpaceShip.getX() + bitmapHalfWidth()) {
			fireLaser();
		}
	}

	private void fireLaser() {
		for (GameObject laser : world.getLasers()) {
			if (laser.isVisible() == false) {
				laser.setX(playerSpaceShip.getX());
				laser.setY(playerSpaceShip.getY() - playerSpaceShip.getHeight());
				laser.setVisible(true);
				break;
			}
		}
	}

	private int bitmapHalfWidth() {
		return playerSpaceShip.getWidth() / 2;
	}

	private int bitmapHalfHeight() {
		return playerSpaceShip.getHeight() / 2;
	}
}
