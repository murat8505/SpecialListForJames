package com.example.speciallistbyjames.data;

import java.util.ArrayList;

public class Data {

	private ArrayList<Article> articles;
	private Info info;
	
	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	
	public void fillNextBactchData(Data mData)
	{
		this.info = mData.info;
		this.articles.addAll(mData.getArticles());
	}

}
