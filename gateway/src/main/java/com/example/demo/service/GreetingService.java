package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.NestedServletException;

import java.util.logging.Logger;

/**
 * @author Ryan Baxter
 */
@Service
public class GreetingService {
	//private static final String URL = "http://localhost:9090";
	private static final String URL = "http://greeting";
	private static final Logger LOG = Logger.getLogger(GreetingService.class.getName());
	private RestTemplate rest;

	public GreetingService(RestTemplate rest) {
		this.rest = rest;
	}

	public String getGreeting() {
		return rest.getForObject(URL, String.class);
	}

	public String getGreeting(String locale) {
		String ret = "Default Greeting";
		try {
			ret = rest.getForObject(new StringBuilder().append(URL).append("/").append(locale).toString(), String.class);
		}
		catch (Exception e){
			LOG.info("Can not connect to the greeting service");
		}

		return ret;
	}
}
