package com.wherlock.myFirstGame.resources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class ResourceManager {
	
	public View view;
	
	public ResourceManager(View view) {
		this.view = view;
	}

	public Bitmap getBitmap(int bitmapId) {
		return BitmapFactory.decodeResource(view.getResources(), bitmapId);
	}
}
