package com.learn.searchApi.exceptionHandler;

public class RestClientException extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;

	public RestClientException() {
		super();
	}

	public RestClientException(final String message) {
		super(message);
	}

}
