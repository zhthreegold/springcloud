package com.example.demo.service;

import com.netflix.client.ClientException;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * @author Ryan Baxter
 */
@Service
@EnableFeignClients
public class NameService {
//	private static final String URL = "http://localhost:7070";
//	private RestTemplate rest;
//
//	public NameService(RestTemplate rest) {
//		this.rest = rest;
//	}
//
//	public String getName() {
//		return rest.getForObject(URL, String.class);
//	}
//
	private NameFeignClient nameFeignClient;
	private static final Logger LOG = Logger.getLogger(NameService.class.getName());

	public NameService(NameFeignClient nameFeignClient){
		this.nameFeignClient = nameFeignClient;
	}

	public String getName(){
		String ret = "Default Name";
		try{
			ret = nameFeignClient.getName();
		}
		catch(Exception e){
			LOG.info("Load balancer does not have available server for client: name");
		}
		return ret;
	}

	@FeignClient("name")
	static interface NameFeignClient{
		@RequestMapping("/")
		public String getName();
	}
}
