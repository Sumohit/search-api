package com.learn.searchApi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.searchApi.Service.SearchService;
import com.learn.searchApi.domain.Users;
import com.learn.searchApi.exceptionHandler.RestClientException;
import com.learn.searchApi.exceptionHandler.SearchExceptionHandler;

@RestController
@RequestMapping(
		value = "/search-api",
		produces = "application/json"
		)
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private SearchExceptionHandler exceptionHandler;


	@GetMapping("/users")
	public ResponseEntity<?>  getUser(@RequestParam(value = "city", required=true) String city, @RequestParam(value = "unit") String unit,
			@RequestParam(value = "radius") double radius){
		Optional<List<Users>> response = null;
		try {
			response = searchService.searchUsers(city, unit, radius);
		} catch (Exception ex) {

			return exceptionHandler.unprocessableEntity(new RestClientException(ex.getMessage()));
		}
		return response.isPresent()
				? ResponseEntity.ok(response)
						: ResponseEntity.notFound().build();
	}

}
