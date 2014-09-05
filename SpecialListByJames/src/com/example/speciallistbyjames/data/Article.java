package com.example.speciallistbyjames.data;

import java.util.ArrayList;

public class Article {

	private String auther_name;
	private ArrayList<Media> media;

	public String getAuther_name() {
		return auther_name;
	}

	public void setAuther_name(String auther_name) {
		this.auther_name = auther_name;
	}

	public ArrayList<Media> getMedia() {
		return media;
	}

	public void setMedia(ArrayList<Media> media) {
		this.media = media;
	}

}
