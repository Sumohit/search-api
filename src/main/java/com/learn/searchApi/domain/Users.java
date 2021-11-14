package com.learn.searchApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {

	Users() {}

	Users(int id, String firstName, String lastName, String email, String ip_address,
			String latitude, String longitude) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.ip_address =ip_address;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	@JsonProperty("id")
	private int id;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("ip_address")
	private String ip_address;

	@JsonProperty("latitude")
	private String latitude;

	@JsonProperty("longitude")
	private String longitude;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Users))
			return false;
		Users searchResponse = (Users) o;
		return Objects.equals(this.id, searchResponse.id) &&
				Objects.equals(this.firstName, searchResponse.firstName) &&
				Objects.equals(this.lastName, searchResponse.lastName) &&
				Objects.equals(this.email, searchResponse.email) &&
				Objects.equals(this.ip_address, searchResponse.ip_address) &&
				Objects.equals(this.latitude, searchResponse.latitude) &&
				Objects.equals(this.longitude, searchResponse.longitude);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.firstName, this.lastName,this.email, this.ip_address,this.latitude, this.longitude);
	}

	@Override
	public String toString() {
		return "{\"first_name\":\"" + firstName
				+ "\", \"last_name\":" + lastName
				+ "\", \"email\":\"" + email
				+ "\", \"ip_address\":\"" + ip_address
				+ "\", \"latitude\":\"" + latitude
				+ "\", \"longitude\":\"" + longitude
				+ "\", \"id\":" + id
				+ "}";
	}

}
