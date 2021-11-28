package com.learn.searchApi.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SearchClient {

	private final String SEARCH_URL = "https://bpdts-test-app.herokuapp.com/city/";
	private final String GOOGLE_API_SEARCH_URL = "https://maps.googleapis.com/maps/api/geocode/json?";
	private final String API_KEY = "ADD_YOUR_GOOGLE_API_KEY_HERE";
	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	
	public ResponseEntity<String> getUsersByLocation (String location) throws RestClientException {
		headers.set("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(SEARCH_URL + location + "/users", HttpMethod.GET, entity, String.class);
		return response;
	}
	
	public ResponseEntity<String> getlatlon (String location) throws RestClientException { 
		headers.set("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(GOOGLE_API_SEARCH_URL + "address=" + location + "&key=" + API_KEY, HttpMethod.GET, entity, String.class);
		return response;
	}
	
	

}
