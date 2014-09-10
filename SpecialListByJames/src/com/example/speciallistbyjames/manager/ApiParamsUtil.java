package com.example.speciallistbyjames.manager;

import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;

public class ApiParamsUtil {

	public static String appendParams(String url, HashMap<String, String> params) {

		StringBuilder mStringBuilder = new StringBuilder();
		mStringBuilder.append(url);

		if (!url.contains("?")) {
			mStringBuilder.append("?");
		}
		else if(url.lastIndexOf("&") != url.length() - 1)
		{
			mStringBuilder.append("&");
		}

		if (params.size() > 0) {
			for (Map.Entry<String, String> entry : params.entrySet()) {

				String key = entry.getKey();
				String value = entry.getValue();
				if (!TextUtils.isEmpty(value)) {

					mStringBuilder.append(key);
					mStringBuilder.append("=");
					mStringBuilder.append(value);
					mStringBuilder.append("&");
				}
			}
			mStringBuilder.deleteCharAt(mStringBuilder.length() - 1);
		}
		return mStringBuilder.toString();
	}

}
