package com.wherlock.myFirstGame.components.ai;

import com.wherlock.myFirstGame.components.GameObject;
import com.wherlock.myFirstGame.components.World;

import android.view.MotionEvent;

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
			handleUpAction();	
		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {
			handleDownAction(x, y);
		}
	}

	private void handleDownAction(int x, int y) {
		if (x >= playerSpaceShip.getX() - (playerSpaceShip.getBitmap().getWidth() / 2) && x <= playerSpaceShip.getX() + (playerSpaceShip.getBitmap().getWidth() / 2)) {
			if (y >= playerSpaceShip.getY() - (playerSpaceShip.getBitmap().getHeight() / 2) && y <= playerSpaceShip.getY() + (playerSpaceShip.getBitmap().getHeight() / 2)) {
				playerSpaceShip.setTouched(true);
			} else {
				playerSpaceShip.setTouched(false);
			}
		} else {
			playerSpaceShip.setTouched(false);
		}
	}

	private void handleUpAction() {
		if (playerSpaceShip.isTouched()) {
			playerSpaceShip.setTouched(false);
		}
		
		for (GameObject laser : world.getLasers()) {
			if (laser.isVisible() == false) {
				laser.setX(playerSpaceShip.getX());
				laser.setY(playerSpaceShip.getY() - playerSpaceShip.getBitmap().getHeight());
				laser.setVisible(true);
				break;
			}
		}
	}

	private void handleMoveAction(int x) {
		if (playerSpaceShip.isTouched()) {

			if (x - playerSpaceShip.getBitmap().getWidth() / 2 <= 0) {
				x = playerSpaceShip.getBitmap().getWidth() / 2;
			} else if (x + playerSpaceShip.getBitmap().getWidth() / 2 >= world.getWorldWidth()) {
				x = world.getWorldWidth() - playerSpaceShip.getBitmap().getWidth() / 2;
			}
			
			playerSpaceShip.setX(x);
		}
	}

}
