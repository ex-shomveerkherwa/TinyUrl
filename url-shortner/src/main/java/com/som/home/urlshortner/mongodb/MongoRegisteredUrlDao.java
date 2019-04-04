package com.som.home.urlshortner.mongodb;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.som.home.urlshortner.dao.RegisteredUrlDao;
import com.som.home.urlshortner.model.RegisteredUrl;

@Repository
public class MongoRegisteredUrlDao implements RegisteredUrlDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(MongoRegisteredUrlDao.class);
	
	@Autowired
	private MongoOperations mongoOps;
	
	
	@Override
	public RegisteredUrl getRegisteredUrlById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRegisteredUrl(RegisteredUrl registeredUrl) {
		LOGGER.info("MongoRegisteredUrlDao createRegisteredUrl "+registeredUrl);
		mongoOps.save(registeredUrl);
	}

	@Override
	public RegisteredUrl findRegisteredUrl(URL url) {
		// TODO Auto-generated method stub
		return null;
	}

}
