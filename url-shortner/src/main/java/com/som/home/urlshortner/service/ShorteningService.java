package com.som.home.urlshortner.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.som.home.urlshortner.dao.RegisteredUrlDao;
import com.som.home.urlshortner.generator.UrlIdentifierGenerator;
import com.som.home.urlshortner.model.RegisteredUrl;

@Service
public class ShorteningService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShorteningService.class);
	
	@Autowired
	protected URL baseUrl;
	
	@Autowired
	private UrlIdentifierGenerator urlIdentifierGenerator;
	
	@Autowired
	RegisteredUrlDao registeredUrlDao;
	
	public void resolve() {
		LOGGER.info("ShorteningService resolve");
	}
	

	//we will check if its present first and if not , we create a new entry 
	public URL shortenUrl(String url) throws MalformedURLException {
		try{
			LOGGER.info("ShorteningService shortenUrl");
			final URL urlToShorten = new URL(url);
				//check in db and return the already shortened one , build a url with shortened one 
			return retreiveOrCreateRegisteredUrl(urlToShorten);
		} catch(NullPointerException e) {
		
		}
		return baseUrl;
		}

	public URL retreiveOrCreateRegisteredUrl(URL urlToShorten) throws MalformedURLException {
		LOGGER.info("ShorteningService retreiveOrCreateRegisteredUrl");
		try {
			//Add logic to fetch it from db or throw an exception if not found
			//since we do not have db , we will throw a null pointer so that catch block generates a new registered url
			throw new NullPointerException();
			
		}catch(NullPointerException e) {
			LOGGER.info("ShorteningService new RegisteredUrl");
			final RegisteredUrl registeredUrl = new RegisteredUrl(urlIdentifierGenerator.generate(), urlToShorten);
			//TODO save the newly created url to the db and return 
			registeredUrlDao.createRegisteredUrl(registeredUrl);
			LOGGER.info("ShorteningService new RegisteredUrl "+registeredUrl.getId());
			return buildCompleteShortenedUrl(registeredUrl);
		}
	}
	
	//TODO add the id of registeredUrl , rather than the whole object
	private URL buildCompleteShortenedUrl(RegisteredUrl registeredUrl) throws MalformedURLException {
		LOGGER.info("ShorteningService buildCompleteShortenedUrl");
		URL url = new URL(baseUrl.getProtocol(),baseUrl.getHost(),baseUrl.getPort(),"/"+registeredUrl.getId());
		LOGGER.info("ShorteningService buildCompleteShortenedUrl URL "+url.toString());
		return url;
	}
}
