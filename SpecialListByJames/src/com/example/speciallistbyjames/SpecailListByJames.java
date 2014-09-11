package com.example.speciallistbyjames;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.speciallistbyjames.manager.CoverController;
import com.example.speciallistbyjames.manager.VolleyBitmapCache;
import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.jfeinstein.jazzyviewpager.JazzyViewPager.TransitionEffect;
import com.jfeinstein.jazzyviewpager.PagerSlidingTabStrip;

public class SpecailListByJames extends FragmentActivity {
  
	private JazzyViewPager mJazzy;
	private PagerSlidingTabStrip mPagerSlidingTabStrip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		VolleyBitmapCache.mQueue = Volley
				.newRequestQueue(getApplicationContext());

		setContentView(R.layout.activity_specail_list_by_james);

		setupJazziness(TransitionEffect.Standard);
	}

	private void setupJazziness(TransitionEffect effect) {
		mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.indicate);
		mPagerSlidingTabStrip.setShouldExpand(true);
		mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		mJazzy.setOffscreenPageLimit(3);
		mJazzy.setTransitionEffect(effect);
		mJazzy.setAdapter(new DemoAdapter(getSupportFragmentManager()));
		mJazzy.setPageMargin(30);

		mPagerSlidingTabStrip.setViewPager(mJazzy);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.specail_list_by_james, menu);
		return true;
	}

	private class DemoAdapter extends FragmentPagerAdapter {

		public DemoAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment mFragment = new CoverFragment();
			return mFragment;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			if (object != null) {
				return ((Fragment) object).getView() == view;
			} else {
				return false;
			}
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			Object obj = super.instantiateItem(container, position);
			mJazzy.setObjectForPosition(obj, position);
			return obj;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Tab " + position;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		final int id = item.getItemId();
		switch (id) {
		case R.id.action_refresh:
			
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
