package com.example.speciallistbyjames.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.speciallistbyjames.R;
import com.example.speciallistbyjames.data.Article;
import com.example.speciallistbyjames.data.CoverResult;
import com.example.speciallistbyjames.manager.VolleyBitmapCache;

public class SpecialListAdapter extends BaseAdapter {

	LayoutInflater mLayoutInflater;
	CoverResult mResult = null;

	public SpecialListAdapter(Context context, CoverResult mResult) {
		mLayoutInflater = LayoutInflater.from(context);
		this.mResult = mResult;
	}

	@Override
	public int getCount() {

		if (mResult != null) {
			return mResult.getArticles().size() * 2;
		}

		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup arg2) {

		int type = getItemViewType(position);
		if (contentView == null) {
			contentView = mLayoutInflater
					.inflate(type == 0 ? R.layout.list_item1
							: R.layout.list_item2, null);

		}

		Article mArticle = mResult.getArticles().get(position / 2);
		if (type == 0) {
			String comment = mArticle.getMedia().get(0).getComment();
			((TextView) contentView.findViewById(R.id.text)).setText(comment);

		} else {
			String url = mArticle.getMedia().get(0).getUrl();
			((NetworkImageView) contentView.findViewById(R.id.icon))
					.setImageUrl(url, VolleyBitmapCache.getImageLoader());

		}

		return contentView;
	}

	@Override
	public int getItemViewType(int position) {
		int type = position % 2;
		return type;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

}
