package com.example.demo.controller;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.service.GreetingService;
import com.example.demo.service.NameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author Ryan Baxter
 */
@RestController
public class GatewayController {

	private static final Logger LOG = Logger.getLogger(GatewayController.class.getName());

	private NameService nameService;
	private GreetingService greetingService;

	public GatewayController(NameService nameService, GreetingService greetingService) {
		this.nameService = nameService;
		this.greetingService = greetingService;
	}

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		String locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request).toLanguageTag();
		String greeting =  new StringBuilder().append(greetingService.getGreeting(locale)).
				append(" ").append(nameService.getName()).toString();
		LOG.info("Greeting: " + greeting);
		LOG.info("Locale: " + locale);
		return greeting;
	}
}
