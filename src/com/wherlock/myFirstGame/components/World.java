package com.wherlock.myFirstGame.components;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class World {
	
	private static final int MAX_ALIENS = 24;
	private static final int MAX_LASERS = 5;
	
	private int worldHeight;
	private int worldWidth;
	private GameObject playerSpaceShip;
	private List<GameObject> lasers = new ArrayList<GameObject>(MAX_LASERS);
	private List<GameObject> aliens = new ArrayList<GameObject>(MAX_ALIENS);
	
	
	public void setWorldBoundary(int width, int height) {
		worldWidth = width;
		worldHeight = height;
	}
	
	public void setSpaceShipSprite(Bitmap spaceShipBitmap) {
		playerSpaceShip = new GameObject(spaceShipBitmap, 250, 600);
		playerSpaceShip.setVisible(true);
	}
	
	public void updateGameState() {
		
//		for (Sprite laser : lasers) {
//			if (laser.isVisible() == true) {
//				
//				laser.setY(laser.getY() - laser.getBitmap().getHeight() / 2);
//				
//				if (laser.getY() >= 0) {
//					laser.setVisible(false);
//				}
//			}
//		}
		
//	    if (spaceShipSprite.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
//	            && spaceShipSprite.getX() + spaceShipSprite.getBitmap().getWidth() / 2 >= worldWidth) {
//	    	
//	        spaceShipSprite.getSpeed().toggleXDirection();
//	    }
//	    
//	    if (spaceShipSprite.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
//	            && spaceShipSprite.getX() - spaceShipSprite.getBitmap().getWidth() / 2 <= 0) {
//	        spaceShipSprite.getSpeed().toggleXDirection();
//	    }
//	    
//	    if (spaceShipSprite.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
//	            && spaceShipSprite.getY() + spaceShipSprite.getBitmap().getHeight() / 2 >= worldHeight) {
//	        spaceShipSprite.getSpeed().toggleYDirection();
//	    }
//	    
//	    if (spaceShipSprite.getSpeed().getyDirection() == Speed.DIRECTION_UP
//	            && spaceShipSprite.getY() - spaceShipSprite.getBitmap().getHeight() / 2 <= 0) {
//	        spaceShipSprite.getSpeed().toggleYDirection();
//	    }
	    
	    //spaceShipSprite.update();
	}
	
	public void downPressed(int x, int y) {
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
	
	public void upPressed(int x, int y) {
		if (playerSpaceShip.isTouched()) {
			playerSpaceShip.setTouched(false);
		}
		
		for (GameObject laser : lasers) {
			if (laser.isVisible() == false) {
				laser.setX(playerSpaceShip.getX());
				laser.setY(playerSpaceShip.getY() - playerSpaceShip.getBitmap().getHeight());
				laser.setVisible(true);
				break;
			}
		}
	}
	
	public void movePressed(int x, int y) {
		if (playerSpaceShip.isTouched()) {

			if (x - playerSpaceShip.getBitmap().getWidth() / 2 <= 0) {
				x += playerSpaceShip.getBitmap().getWidth() / 2;
			} else if (x + playerSpaceShip.getBitmap().getWidth() / 2 >= worldWidth) {
				x -= playerSpaceShip.getBitmap().getWidth() / 2;
			}

			playerSpaceShip.setX(x);
		}
	}

	public void setLasersSprites(Bitmap laserBitmap) {
		for (int i=0; i<MAX_LASERS; i++) {
			
			GameObject laser = new GameObject(laserBitmap, 0, 0);
			laser.setVisible(false);
			lasers.add(laser);
		}
	}

	public void setAliensSprites(Bitmap alienBitmap) {
		
		int xSpacing = 50;
		int ySpacing = 75;
		int currentX = 0;
		int currentY = 50;
		
		for (int i=1; i<=MAX_ALIENS; i++) {
			
			currentX += xSpacing;
			
			GameObject alien = new GameObject(alienBitmap, currentX, currentY);
			alien.setVisible(true);
			aliens.add(alien);
			
			if (i % 8 == 0) {
				currentX = 0;
				currentY += ySpacing;
			}
		}
	}

	public void render(Canvas canvas) {
		playerSpaceShip.draw(canvas);
		
		for (int i=0; i<MAX_ALIENS; i++) {
			aliens.get(i).draw(canvas);
		}
		
		for (GameObject laser : lasers) {
			if (laser.isVisible() == true) {
				laser.draw(canvas);
			}
		}
	}

	public void setPlayerInput(HumanInputSource playerInput) {
		playerSpaceShip.setInputSource(playerInput);
	}	
}
