package com.som.home.urlshortner.dao;

import java.net.URL;

import com.som.home.urlshortner.model.RegisteredUrl;

public interface RegisteredUrlDao {

	RegisteredUrl getRegisteredUrlById(String id);
	
	void createRegisteredUrl(RegisteredUrl registeredUrl);
	
	RegisteredUrl findRegisteredUrl(URL url);
}
