package com.example.speciallistbyjames.manager;

import com.android.volley.Cache;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.speciallistbyjames.data.CoverResult;

public class CoverController {

	public static void loadNextBatchData(String url,
			Listener<String> mListener, ErrorListener mErrorListener) {
		StringRequest mStringRequest = getRequestByUrl(url, mListener, mErrorListener);
		VolleyBitmapCache.mQueue.add(mStringRequest);
	}

	public static void loadFirstBatchData(Listener<String> mListener,
			ErrorListener mErrorListener) {
		loadNextBatchData(CoverResult.SAMPLE_URL, mListener, mErrorListener);
	}

	public static void cleanCache(Runnable callback) {
		Cache mCache = VolleyBitmapCache.mQueue.getCache();
		ClearCacheRequest mCacheRequest = new ClearCacheRequest(mCache,
				callback);
		VolleyBitmapCache.mQueue.add(mCacheRequest);
	}

	public static void refreshData(Listener<String> mListener,
			ErrorListener mErrorListener) {
		StringRequest mStringRequest = getRequestByUrl(CoverResult.SAMPLE_URL, mListener, mErrorListener);
		mStringRequest.setForceRefresh(true);
		VolleyBitmapCache.mQueue.add(mStringRequest);
	}

	private static StringRequest getRequestByUrl(String url,
			Listener<String> mListener, ErrorListener mErrorListener) {
		StringRequest mStringRequest = new StringRequest(
				ApiParamsUtil.appendParams(url, CoverResult.getParams()),
				mListener, mErrorListener);
		return mStringRequest;
	}
}
