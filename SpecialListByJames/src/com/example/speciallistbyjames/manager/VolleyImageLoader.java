package com.example.speciallistbyjames.manager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

public class VolleyImageLoader extends ImageLoader {

	ImageCache mImageCache;
	
	public VolleyImageLoader(RequestQueue queue, ImageCache imageCache) {
		super(queue, imageCache); 
		this.mImageCache = imageCache;
	}

	public ImageCache getImageCache() {
		return mImageCache;
	}
	  
}
