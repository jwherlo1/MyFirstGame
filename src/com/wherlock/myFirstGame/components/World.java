package com.wherlock.myFirstGame.components;

import java.util.ArrayList;
import java.util.List;

import com.wherlock.myFirstGame.components.ai.AlienAI;
import com.wherlock.myFirstGame.components.ai.LaserAI;
import com.wherlock.myFirstGame.components.ai.SpaceShipAI;
import com.wherlock.myFirstGame.components.input.HumanInputSource;
import com.wherlock.myFirstGame.components.input.NullInputSource;

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

	public void createPlayerSpaceShip(Bitmap spaceShipBitmap, HumanInputSource playerInput) {
		playerSpaceShip = new GameObject(spaceShipBitmap, 250, 600);
		playerSpaceShip.setInputSource(playerInput);
		playerSpaceShip.setAI(new SpaceShipAI(playerSpaceShip, this));
		playerSpaceShip.setVisible(true);
	}

	public void createLasers(Bitmap laserBitmap) {
		for (int i = 0; i < MAX_LASERS; i++) {

			GameObject laser = new GameObject(laserBitmap, 0, 0);
			laser.setVisible(false);
			laser.setAI(new LaserAI(laser));
			laser.setInputSource(new NullInputSource());
			lasers.add(laser);
		}
	}

	public void createAliens(Bitmap alienBitmap) {

		int xSpacing = 50;
		int ySpacing = 75;
		int currentX = 0;
		int currentY = 50;

		for (int i = 1; i <= MAX_ALIENS; i++) {

			currentX += xSpacing;

			GameObject alien = new GameObject(alienBitmap, currentX, currentY);
			alien.setVisible(true);
			alien.setAI(new AlienAI());
			alien.setInputSource(new NullInputSource());
			aliens.add(alien);

			if (i % 8 == 0) {
				currentX = 0;
				currentY += ySpacing;
			}
		}
	}

	public List<GameObject> getLasers() {
		return lasers;
	}
	
	public List<GameObject> getAliens() {
		return aliens;
	}

	public int getWorldHeight() {
		return worldHeight;
	}

	public void setWorldHeight(int worldHeight) {
		this.worldHeight = worldHeight;
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	public void setWorldWidth(int worldWidth) {
		this.worldWidth = worldWidth;
	}

	public void updateGameState() {
	
		for (GameObject laser : lasers) {
			if (laser.isVisible() == true) {
				laser.update();
			}
		}
	
		playerSpaceShip.update();
	}

	public void render(Canvas canvas) {
		playerSpaceShip.draw(canvas);
	
		for (int i = 0; i < MAX_ALIENS; i++) {
			aliens.get(i).draw(canvas);
		}
	
		for (GameObject laser : lasers) {
			if (laser.isVisible() == true) {
				laser.draw(canvas);
			}
		}
	}
}
