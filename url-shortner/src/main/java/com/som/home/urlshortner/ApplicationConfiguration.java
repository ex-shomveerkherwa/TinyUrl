package com.som.home.urlshortner;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.som.home")
public class ApplicationConfiguration {

	@Value("${baseUrl}")
	private String baseUrl;
	
	@Value("${mongodb.host}")
	private String mongoHost;
	
	@Value("${mongodb.port}")
	private int mongoPort;
	
	@Value("${mongodb.dbname}")
	private String mongoDbName;
	
	@Bean
	URL baseUrl() throws MalformedURLException {
		return new URL(baseUrl);
	}
	
	private MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongoHost, mongoPort), mongoDbName);
    }

    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
	
	@Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
