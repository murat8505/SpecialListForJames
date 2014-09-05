package com.example.speciallistbyjames;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.speciallistbyjames.data.Result;
import com.example.speciallistbyjames.manager.VolleyBitmapCache;
import com.example.speciallistbyjames.view.SpecialListAdapter;
import com.google.gson.Gson;

public class SpecailListByJames extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		VolleyBitmapCache.mQueue = Volley
				.newRequestQueue(getApplicationContext());

		setContentView(R.layout.activity_specail_list_by_james);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.specail_list_by_james, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_specail_list_by_james, container, false);
			final ListView mListView = (ListView) rootView
					.findViewById(R.id.list);
			mListView.setAdapter(new SpecialListAdapter(getActivity(), null));

			StringRequest mStringRequest = new StringRequest(
					Contants.SAMPLE_URL, new Listener<String>() {

						@Override
						public void onResponse(String response) {

							Gson mGson = new Gson();
							Result mResult = mGson.fromJson(response,
									Result.class);
							mListView.setAdapter(new SpecialListAdapter(
									getActivity(), mResult));
						}
					}, new ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub

						}
					});
			
			mListView.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
					 
				}
			});

			VolleyBitmapCache.mQueue.add(mStringRequest);

			return rootView;
		}
	}
}
