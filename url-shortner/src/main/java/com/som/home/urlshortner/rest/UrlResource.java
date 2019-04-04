package com.som.home.urlshortner.rest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.som.home.urlshortner.dao.RegisteredUrlDao;
import com.som.home.urlshortner.service.ShorteningService;

@Path("/short")
public class UrlResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UrlResource.class);
	
	@Autowired
	ShorteningService shorteningService;
	
	
	
	@GET
	public String resolve(String id) throws URISyntaxException {
		LOGGER.info("UrlResource resolve");
		shorteningService.resolve();
		//return Response.seeOther(new URI("defaultURIresponse")).build();
		return "som";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String create(@FormParam("url") String OriginalUrl) {
		LOGGER.info("UrlResource create OriginalUrl "+OriginalUrl);
		try {
			return shorteningService.shortenUrl(OriginalUrl).toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException();
		}
	}
}
