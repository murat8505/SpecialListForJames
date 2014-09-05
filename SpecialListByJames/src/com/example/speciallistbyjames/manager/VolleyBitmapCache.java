package com.example.speciallistbyjames.manager;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache; 

public class VolleyBitmapCache implements ImageCache {

	final String TAG = "VolleyBitmapCache";
	private LruCache<String, Bitmap> mCache;

	public VolleyBitmapCache() {
		int maxSize = (int) Runtime.getRuntime().maxMemory() / 3;
		mCache = new LruCache<String, Bitmap>(maxSize) {
			@SuppressLint("NewApi")
			@Override
			protected int sizeOf(String key, Bitmap value) {
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
					return value.getByteCount();
				} else {
					return value.getRowBytes() * value.getHeight();
				}
			}

		};

	}

	@Override
	public Bitmap getBitmap(String url) {
		
		Log.e("VolleyBitmapCache", "getBitmap url : " + url);
		final Bitmap bitmap = mCache.get(url);

		if (bitmap != null) {
			return bitmap;
		}

		return null;
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		
		Log.e("VolleyBitmapCache", "putBitmap url : " + url);
		if (bitmap != null) {
			mCache.put(url, bitmap);
		}
	}

	public static VolleyImageLoader mImageLoader;
	public static RequestQueue mQueue; 

	public static VolleyImageLoader getImageLoader() {

		if (mImageLoader == null) {
		 
			mImageLoader = new VolleyImageLoader(mQueue,
					new VolleyBitmapCache()); 
		} 
		return mImageLoader;
	}

}
