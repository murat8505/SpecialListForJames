package com.example.speciallistbyjames.data;

import java.util.ArrayList;

public class Result {

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
	
	public ArrayList<Article> getArticles()
	{
		if(data != null)
		{
			ArrayList<Article> mArticles = data.getArticles();
			if(mArticles != null)
			{
				return mArticles;
			}
		}
		return new ArrayList<Article>();
	}

}
