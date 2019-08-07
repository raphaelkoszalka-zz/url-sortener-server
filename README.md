# URL Shortener Microservice

This is a simple example of a URL shortener built with SpringBoot.

There's only one endpoint (or primitive) that will receive a POST or a GET
if it's a POST it will test if the URL is valid and if the url and it's expiration date
are not stored in the database.

## Endpoint 

### POST

```
{
	"originalUrl" : "https://www.globo.com",
	"expiresAt" : "1865165323239" // very important to be EPOCH MILLI (https://currentmillis.com/)
}
```

Will generate a response like:

```
{
    "originalUrl": "http://localhost:8080/v1/chsv8v",
    "expiresAt": 1865165323239
}
```

### GET

When you try to access http://localhost:8080/v1/{ID} the microservice will
test if the ID is still valid (you need to use epoch in milliseconds), if expiration date
is still valid the microservice will redirect you to it's original URL, if not it will return a 
simple 410 (GONE) HTTP Status Code.


## How to run

- Import the project into your IDE (it's preferable to be IntelliJ)
- Install Lombok Plugin for @Getter and @Setter annotation
- Enable annotation process at ```Editor > Compiler > Annotation Processors```
- bootRun the application (with Gradle)

When you bootRun for the first time the application will read db.changelog-master.yaml file @
resources/db folder and create the table and it's columns automatically, so you don't need to 
worry about SQL syntax for different databases, I chose PostgreSQL just because I am used to it, 
but it would work with MySQL or Oracle for example.

## Notes

If I had more time I would install and config Grafana to 'keep and eye' on the
server health. And also let it runnning at AWS, but my free tier is over :/

The idea is to let **utils** package running on a lambda server, so it would be up
only when requested, but since I ain't got no free tier account at AWS I just let it
be part of the microservice as a separated package.

To minimize at minimum collisions I am concatenating the URL plus Epoch timestamp
as the algorithm ID, and if is the same URL and timestamp it will just return the database
persisted shortened URL.

Since the microservice does not have user authentication it is stateless, or does not keep sessions,
but if auth would be need to keep it stateless I would use OAuth2 to avoid creating sessions and use 
JWT token instead.

## Doubts?
Just send me a message at rmkoszalka@gmail.com