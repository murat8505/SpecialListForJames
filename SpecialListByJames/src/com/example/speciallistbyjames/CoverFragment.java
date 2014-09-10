package com.example.speciallistbyjames;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.example.speciallistbyjames.data.CoverResult;
import com.example.speciallistbyjames.manager.ApiParamsUtil;
import com.example.speciallistbyjames.manager.CoverController;
import com.example.speciallistbyjames.manager.VolleyBitmapCache;
import com.example.speciallistbyjames.view.SpecialListAdapter;
import com.google.gson.Gson;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

public class CoverFragment extends Fragment {

	CoverResult mResult;
	ListView mListView;
	SpecialListAdapter mSpecialListAdapter;
	boolean isLoading = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(
				R.layout.fragment_specail_list_by_james, container, false);

		mListView = (ListView) rootView.findViewById(R.id.list);

		mListView.setAdapter(new SpecialListAdapter(getActivity(), null));

		mListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {

			}

			@Override
			public void onScroll(AbsListView arg0, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount == totalItemCount
						&& totalItemCount > 0) {
					loadNext();
				}
			}
		});
		return rootView;
	}

	public void refresh() {
		CoverController.refreshData(mFirstLoadListener, mErrorListener);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		isLoading = true;
		CoverController.loadFirstBatchData(mFirstLoadListener, mErrorListener);
	}

	protected void onLoading() {
		isLoading = true;
	}

	protected void onLoaded() {
		isLoading = false;
	}

	protected void loadNext() {
		if (!isLoading) {
			isLoading = true;
			CoverController.loadNextBatchData(mResult.getData().getInfo()
					.getNextUrl(), mNextLoadListener, mErrorListener);
		}
	}

	Listener<String> mFirstLoadListener = new Listener<String>() {

		@Override
		public void onResponse(String response) {

			Gson mGson = new Gson();
			mResult = mGson.fromJson(response, CoverResult.class);
			mSpecialListAdapter = new SpecialListAdapter(getActivity(), mResult);
			mListView.setAdapter(mSpecialListAdapter);
			onLoaded();
		}
	};

	Listener<String> mNextLoadListener = new Listener<String>() {

		@Override
		public void onResponse(String response) {

			Gson mGson = new Gson();
			CoverResult mNextResult = mGson.fromJson(response,
					CoverResult.class);
			mResult.fillNextBachData(mNextResult.getData());
			mSpecialListAdapter.notifyDataSetChanged();
			onLoaded();
		}
	};

	ErrorListener mErrorListener = new ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {

			Toast.makeText(getActivity(), error.getMessage() + "",
					Toast.LENGTH_SHORT).show();
			onLoaded();
		}
	};
}
