package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public String getAccessToken(@RegisteredOAuth2AuthorizedClient("m2m") OAuth2AuthorizedClient authorizedClient) {
		return authorizedClient != null ? authorizedClient.getAccessToken().getTokenValue() : "null";
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String getAccessToken() {
		return "Hello World";
	}


}
