package com.learn.searchApi.serviceTest;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.learn.searchApi.Service.SearchService;
import com.learn.searchApi.Service.SearchServiceImpl;
import com.learn.searchApi.client.SearchClient;

public class SearchServiceTest {
	
	@InjectMocks
	private SearchService  searchService = new SearchServiceImpl();
	
	@Mock
	SearchClient mockSearchClient;
	
	@Test
	public void searchProjectsTest() throws Exception {
		String mockCity = "test";
		String mockUnit = "mockUnit";
		double mockRadius = 0.0;
		Mockito.when(mockSearchClient.getUsersByLocation(Mockito.anyString())).thenReturn(new ResponseEntity<String>(HttpStatus.OK));
		searchService.searchUsers(mockCity, mockUnit , mockRadius );
	}
	


}
