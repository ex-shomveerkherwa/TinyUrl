package com.som.home.urlshortner.model;

import java.net.URL;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RegisteredUrl {

	@Override
	public String toString() {
		return "RegisteredUrl [id=" + id + ", url=" + url + "]";
	}

	private final String id;
	private final URL url;

	public String getId() {
		return id;
	}

	public URL getUrl() {
		return url;
	}

	public RegisteredUrl(String id, URL url) {
		this.id = id;
		this.url = url;
	}

}
