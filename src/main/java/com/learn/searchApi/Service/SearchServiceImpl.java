package com.learn.searchApi.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.searchApi.client.SearchClient;
import com.learn.searchApi.domain.Users;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchClient client;

	@Override
	public Optional<List<Users>> searchUsers(String city, String unit, double radius) throws JsonMappingException, JsonProcessingException, ParseException{

		ResponseEntity<String> response = client.getUsersByLocation(city);

		final ObjectMapper objectMapper = new ObjectMapper();
		List<Users> userList = transformSearchResponse(objectMapper.readValue(response.getBody().toString(), new TypeReference<List<Users>>(){}), getLatlonOfGivenCity(city), unit, radius);

		System.out.println(userList);
		return Optional.of(userList);
	}

	// This Method is used to get the lat and lon of a given city using google api.
	private Map<String,Object> getLatlonOfGivenCity(String city) throws JsonMappingException, JsonProcessingException, ParseException {

		ResponseEntity<String> getlatlonRes = client.getlatlon(city);


		// parsing the JSON response of google 
		JSONParser parser = new JSONParser();
		JSONObject json = null;

		json = (JSONObject) parser.parse(getlatlonRes.getBody().toString());

		JSONArray resultsArray = (JSONArray) json.get("results");
		Map<String,Object> results = new HashMap<String,Object>();
		if(!resultsArray.isEmpty()) {
			JSONObject obj = (JSONObject) resultsArray.get(0);
			JSONObject geometry = (JSONObject) obj.get("geometry");
			JSONObject location = (JSONObject) geometry.get("location");
			final ObjectMapper objectMapper = new ObjectMapper();

			results = objectMapper.readValue(location.toString(), new TypeReference<Map<String,Object>>(){});

		}
		return results;		
	}

	// This method will transform the search result into required output
	private List<Users>transformSearchResponse(List<Users> userList, Map<String,Object> geoLocation, String unit, double radius){
		List<Users> newUserList = new ArrayList<Users>();

		double givenCityLat =  0.0;
		double givenCityLon = 0.0;

		if(!geoLocation.isEmpty()) {
			givenCityLat = (double) geoLocation.get("lat");
			givenCityLon = (double) geoLocation.get("lng");
		}

		for(Users user : userList) {
			double distance = calculateDistance(givenCityLat, givenCityLon, Double.parseDouble(user.getLatitude()), Double.parseDouble(user.getLongitude()), unit);
			System.out.println("in miles:- " + distance);
			if(distance<=radius) {
				newUserList.add(user);
			}
		}
		return newUserList;
	}

	// This method will calculate the distance between given two lat & lon.
	// using Haversine formula - Distance, d = 3963.0 * arccos[(sin(lat1) * sin(lat2)) + cos(lat1) * cos(lat2) * cos(long2 – long1)]
	private double calculateDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double distance = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(lon1 - lon2));
		distance = Math.acos(distance);
		distance = Math.toDegrees(distance);		

		if (unit.equalsIgnoreCase("km") || unit.equalsIgnoreCase("kilometers")) {
			distance = distance * 1.609344;
		}

		if (unit.isEmpty()|| unit.equalsIgnoreCase("mi") || unit.equalsIgnoreCase("miles")) {
			distance = distance * 60 * 1.1515;;
		} 
		return distance;
	}

}
