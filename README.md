# search-api


#Problem statement :- 
Build an API which calls the API at https://bpdts-test-app.herokuapp.com/,
and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London. 

#Solution :-  

To get the list of all user based on the london we will consume api https://bpdts-test-app.herokuapp.com/city/{city}/users  (For Example https://bpdts-test-app.herokuapp.com/city/London/users) 
we will get a response somewhat like that -  
[{
"id": 1,
"first_name": "Mohit",
"last_name": "Yadav",
"email": "test@testing.com",
"ip_address": "113.71.242.187",
"latitude": -6.5115909,
"longitude": 105.652983
}]

I using Lat & lon to findout all users within 50 miles of London. 
For this I am using Haversine formula ,ie. Distance, d = 3963.0 * arccos[(sin(lat1) * sin(lat2)) + cos(lat1) * cos(lat2) * cos(long2 – long1)]
to calulate distance in miles from a given lat & lon.

I using Google api to get approx. Lat & lon of given city i.e London
Goodle-API = "https://maps.googleapis.com/maps/api/geocode/json?address={city}&key={API_KEY};



To run the application

	$mvn spring-boot:run

To run the application on IDE(IntelliJ/Eclipse)
Import the project as a maven project and edit the run maven configuration.
	goal : spring-boot:run

JDK required 1.8 or above.

Endpoints Details:-

GET http://localhost:8080/search-api/users?city=London&unit=miles&radius=50


