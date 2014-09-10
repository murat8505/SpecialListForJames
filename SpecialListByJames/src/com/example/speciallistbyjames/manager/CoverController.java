package com.example.speciallistbyjames.manager;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.example.speciallistbyjames.data.CoverResult;

public class CoverController {

	public static void loadNextBatchData(String url,
			Listener<String> mListener, ErrorListener mErrorListener) {
		StringRequest mStringRequest = new StringRequest(
				ApiParamsUtil.appendParams(url, CoverResult.getParams()),
				mListener, mErrorListener);
		VolleyBitmapCache.mQueue.add(mStringRequest);
	}

	public static void loadFirstBatchData(Listener<String> mListener,
			ErrorListener mErrorListener) {
		loadNextBatchData(CoverResult.SAMPLE_URL, mListener, mErrorListener);
	}
}
