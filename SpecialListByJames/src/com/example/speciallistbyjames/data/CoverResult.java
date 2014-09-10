package com.example.speciallistbyjames.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.speciallistbyjames.Contants;

public class CoverResult {

	private String stat;
	private String msg;
	private Data data;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void fillNextBachData(Data data) {
		this.data.fillNextBactchData(data);
	}

	public ArrayList<Article> getArticles() {
		if (data != null) {
			ArrayList<Article> mArticles = data.getArticles();
			if (mArticles != null) {
				return mArticles;
			}
		}
		return new ArrayList<Article>();
	}

	public static final String SAMPLE_URL = "http://iphone.myzaker.com/zaker/cover_app.php";

	public static final HashMap<String, String> getParams() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(Contants.APPID_KEY, Contants.APPID);
		params.put(Contants.VERSION_KEY, Contants.VERSION);
		return params;
	}

}
