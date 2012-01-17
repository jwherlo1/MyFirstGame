package com.wherlock.myFirstGame.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.wherlock.myFirstGame.R;


public class GameWorld {
	
	private static final int MAX_ALIENS = 24;
	private static final int MAX_LASERS = 5;
	
	private int worldHeight;
	private int worldWidth;
	private Sprite spaceShipSprite;
	private List<Sprite> lasers = new ArrayList<Sprite>(MAX_LASERS);
	private List<Sprite> aliens = new ArrayList<Sprite>(MAX_ALIENS);
	
	
	public void setWorldBoundary(int width, int height) {
		worldWidth = width;
		worldHeight = height;
	}
	
	public void setSpaceShipSprite(Bitmap spaceShipBitmap) {
		spaceShipSprite = new Sprite(spaceShipBitmap, 250, 600);
		spaceShipSprite.setVisible(true);
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
		if (x >= spaceShipSprite.getX() - (spaceShipSprite.getBitmap().getWidth() / 2) && x <= spaceShipSprite.getX() + (spaceShipSprite.getBitmap().getWidth() / 2)) {
			if (y >= spaceShipSprite.getY() - (spaceShipSprite.getBitmap().getHeight() / 2) && y <= spaceShipSprite.getY() + (spaceShipSprite.getBitmap().getHeight() / 2)) {
				spaceShipSprite.setTouched(true);
			} else {
				spaceShipSprite.setTouched(false);
			}
		} else {
			spaceShipSprite.setTouched(false);
		}
	}
	
	public void upPressed(int x, int y) {
		if (spaceShipSprite.isTouched()) {
			spaceShipSprite.setTouched(false);
		}
		
		for (Sprite laser : lasers) {
			if (laser.isVisible() == false) {
				laser.setX(spaceShipSprite.getX());
				laser.setY(spaceShipSprite.getY() - spaceShipSprite.getBitmap().getHeight());
				laser.setVisible(true);
				break;
			}
		}
	}
	
	public void movePressed(int x, int y) {
		if (spaceShipSprite.isTouched()) {

			if (x - spaceShipSprite.getBitmap().getWidth() / 2 <= 0) {
				x += spaceShipSprite.getBitmap().getWidth() / 2;
			} else if (x + spaceShipSprite.getBitmap().getWidth() / 2 >= worldWidth) {
				x -= spaceShipSprite.getBitmap().getWidth() / 2;
			}

			spaceShipSprite.setX(x);
		}
	}

	public void setLasersSprites(Bitmap laserBitmap) {
		for (int i=0; i<MAX_LASERS; i++) {
			
			Sprite laser = new Sprite(laserBitmap, 0, 0);
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
			
			Sprite alien = new Sprite(alienBitmap, currentX, currentY);
			alien.setVisible(true);
			aliens.add(alien);
			
			if (i % 8 == 0) {
				currentX = 0;
				currentY += ySpacing;
			}
		}
	}

	public void render(Canvas canvas) {
		spaceShipSprite.draw(canvas);
		
		for (int i=0; i<MAX_ALIENS; i++) {
			aliens.get(i).draw(canvas);
		}
		
		for (Sprite laser : lasers) {
			if (laser.isVisible() == true) {
				laser.draw(canvas);
			}
		}
	}	
}
