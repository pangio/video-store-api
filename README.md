# Video Rental API

# Basic Overview
## Entities & Operations
### Associate:
- Rent a Video

- Return a Video

- Pending rentals to be returned 

- Profile (bonus points and history)

### Video:
- all CRUD operations

### FilmType:
- List & FindById

### PriceType:
- List & FindById



# Stack
*  Java 8
*  JPA, Spring Data
*  Spring Boot, Spring MVC
*  jUnit, Mockito
*  Gradle
*  H2 in-memory DB

# Prerequisites
*  jdk 1.8
*  gradle 2.2.1
*  any REST Client (like XHR Poster)
*  OPTIONAL: GGTS / Eclipse (or your favorite IDE)

# Run the example

From IDE: 
```
Import the project into the IDE. 
Build it with Gradle. 
Execute the class Application.java.
```

From Terminal / Command line:

* clone the repo
```
https://github.com/pangio/video-store-api.git
```
* Build
```
./gradlew build
```
* Run
```
./gradlew bootRun
```
* use the REST Client to hit the endpoints





# Endpoints / Http Methods

# Associates

#### Rent a Video

POST: ```http://localhost:8080/associates/{associateId}/rent```

JSON body example:
```
{
	"videoId": 3,
	"days": 2
}
```

**Response**

200 OK - if the rental was successful

JSON sample response:
```
{
	"fees": 80
}
```

NOTE: if ```fees``` are ```0```  means no rental has been created because video is out of stock

**Errors**

404 Not Found - for an invalid associate id

404 Not Found - for an invalid video id



#### Return a Video to the Store

GET: ```http://localhost:8080/associates/{associateId}/return/{videoId}```

**Response**
200 OK - if the rental was returned successfully

JSON sample response:
```
{
	"extraFees": 80
}
```

NOTE: if ```extraFees``` are ```0``` means no Extra Fees should be charged to the customer.

**Errors**

404 Not Found - for an invalid associate id

404 Not Found - for an invalid video id



#### List of Rentals Pending to return to the Store

GET: ```http://localhost:8080/associates/{associateId}/pending```

**Response**

200 OK - list of pending rentals

JSON sample response:
```
[
	{
		"id": 3,
		"dateRented": 1439740760734,
		"video": {
			"id": 4,
			"name": "Harry Potter",
			"totalStock": 6,
			"availableStock": 5,
			"filmType": {
				"id": 2,
				"description": "Regular Film",
				"bonusPoints": 1,
				"priceType": {
					"id": 1,
					"description": "Basic",
					"value": 30
				}
			},
			"enabled": true
		},
		"days": 3,
		"returned": false
	}
]
```

**Errors**

404 Not Found - for an invalid associate id



#### Profile of the Associate

GET: ```http://localhost:8080/associates/{associateId}```

**Response**

200 OK - associate's  profile

JSON sample response:
```
{
	"id": 1,
	"name": "Karen",
	"bonusPoints": 5,
	"rentals": [
		{
			"id": 1,
			"dateRented": 1439740760408,
			"video": {
				"id": 5,
				"name": "San Andreas",
				"totalStock": 7,
				"availableStock": 0,
				"filmType": {
					"id": 3,
					"description": "New Release",
					"bonusPoints": 2,
					"priceType": {
						"id": 2,
						"description": "Premium",
						"value": 40
					}
				},
				"enabled": true
			},
			"days": 1,
			"returned": false
		}
	]
}
```

**Errors**

404 Not Found - for an invalid associate id



# Videos
#### Creates a new Video

POST: ```http://localhost:8080/videos```

JSON body example:
```
{
	"name": "Terminator",
	"totalStock": 8,
	"availableStock": 8,
	"filmType": "/filmTypes/{filmTypeId}"	
}
```

#### Updates a new Video

PUT: ```http://localhost:8080/videos/{videoId}```

JSON body example:
```
{
	"name": "Terminator",
	"totalStock": 8,
	"availableStock": 8,
	"filmType": "/filmTypes/{filmTypeId}"	
}
```

#### Returns the list of all Videos

GET: ```http://localhost:8080/videos```

#### Finds a Video by id

GET: ```http://localhost:8080/videos/{videoId}```

#### Deletes a Video 

DELETE: ```http://localhost:8080/videos/{videoId}```

NOTE: Performs a virtual delete. Video is set as unavailable for further Rentals



# PriceTypes
#### Returns the list of all PriceType

GET: ```http://localhost:8080/prices```

#### Finds a PriceType by id

GET: ```http://localhost:8080/prices/{priceTypeId}```




# FilmTypes
#### Returns the list of all FilmType

GET: ```http://localhost:8080/types```

#### Finds a FilmType by id

GET: ```http://localhost:8080/types/{filmTypeId}```



# Tests
* The Test Suite [AllTest.java](src/test/java/com/pangio/rental/api/AllTests.java) contains some tests (more should be added)



### Notes & Assumptions
* Associate: Customer of the Rental Store.
* One Associate can not rent two (or more) copies of same video at the same time.
* Videos are not deleted from DB. They are set as unavailable for further Rentals.
* Initial data was created in [Application.java](src/main/java/com/pangio/rental/api/Application.java)
* Content Type should be set to ```application/json``` .
* More tests should be added
* Security should be added

