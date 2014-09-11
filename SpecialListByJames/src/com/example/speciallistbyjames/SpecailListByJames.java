package com.example.speciallistbyjames;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.speciallistbyjames.R;
import com.android.volley.toolbox.Volley;
import com.example.speciallistbyjames.manager.CoverController;
import com.example.speciallistbyjames.manager.VolleyBitmapCache;

public class SpecailListByJames extends FragmentActivity {

	CoverFragment mCoverFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		VolleyBitmapCache.mQueue = Volley
				.newRequestQueue(getApplicationContext());

		setContentView(R.layout.activity_specail_list_by_james);
		if (savedInstanceState == null) {
			mCoverFragment = new CoverFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, mCoverFragment).commit();
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
		final int id = item.getItemId();
		switch (id) {
		case R.id.action_refresh:

			if (mCoverFragment != null) {
				mCoverFragment.refresh();
			}

			return true;

		case R.id.action_clean:

			final ProgressDialog mProgressDialog = new ProgressDialog(
					SpecailListByJames.this);
			mProgressDialog.setCancelable(false);
			mProgressDialog.setMessage("Cleaning cache ...");
			mProgressDialog.show();

			CoverController.cleanCache(new Runnable() {

				@Override
				public void run() {

					mProgressDialog.dismiss();
					Toast.makeText(SpecailListByJames.this, "Clean Finished",
							Toast.LENGTH_SHORT).show();

				}
			});
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
