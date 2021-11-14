package com.learn.searchApi.Service;

import java.util.List;
import java.util.Optional;

import com.learn.searchApi.domain.Users;

public interface SearchService {
	public Optional<List<Users>> searchUsers (String city, String unit, double radius) throws Exception;

}
