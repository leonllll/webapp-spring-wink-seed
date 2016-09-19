# Webapp-Spring-Wink-Seed -- the seed for a web application based on Spring & Wink
This project is a skeleton for a RESTful java web app based on [Spring Framework](http://projects.spring.io/spring-framework/), [Apache Wink](https://wink.apache.org/) and [Apache OpenJPA](http://openjpa.apache.org/). You can use it to quickly bootstrap your RESTful web services.

## Dependencies
* `Spring Framework` - including spring-context, spring-web, spring-orm, spring-test, etc. Spring-orm provides integration layers for JPA.
* `Apache Wink` - a simple yet solid framework for building RESTful Web services. Wink-spring-support is needed when integrating with Spring.
* `Apache OpenJPA` - compliant implementation of the JPA (Java Persistence 2.0 specification)
* `Sqlite` -  a high-reliability, embedded, zero-configuration, public-domain, SQL database engine
* `Aspectj` - a seamless aspect-oriented extension
* `Apache DBCP` - The DBCP (Database Connection Pool)  provides database connection pooling services.
* `Jackson` - a suite of data-processing tools for Java, including the flagship streaming JSON parser / generator library, matching data-binding library (POJOs to and from JSON) etc.

## Features
* RESTful services for basic CRUD operations
* Database transactions with JPA, using Sqlite as the sample database
* Error handling based on AOP

## Getting started
### Clone Webapp-Spring-Wink-Seed
```
git clone https://github.com/leonllll/webapp-spring-wink-seed.git
```
### Build the package
```
mvn clean package
```
### Run the application
deploy the webapp-spring-wink-seed.war into a Web Server, such as Tomcat.
Or you can use `tomcat7-maven-plugin` to build and deploy:
```
mvn tomcat7:redeploy
```
which needs some  [configurations](http://tomcat.apache.org/maven-plugin-2.0/tomcat7-maven-plugin/usage.html) in Tomcat and Maven

## Directory Layout
```
|____src
| |____main
| | |____java
| | | |____com
| | | | |____leonlu
| | | | | |____code
| | | | | | |____sample
| | | | | | | |____webapp
| | | | | | | | |____ws
| | | | | | | | | |____aspect
| | | | | | | | | | |____ExceptionAspect.java       --> an aspect handling all exceptions
| | | | | | | | | |____common
| | | | | | | | | | |____WebAppException.java       --> common runtime exception
| | | | | | | | | | |____WebAppResponse.java        --> common entity of Wink Response for RESTful APIs
| | | | | | | | | |____dao
| | | | | | | | | | |____GenericDao.java            --> interface of the generic abstract DAO for JPA
| | | | | | | | | | |____PeopleDao.java             --> interface of the DAO of People
| | | | | | | | | | |____impl
| | | | | | | | | | | |____GenericDaoImpl.java      --> an implementation of GenericDao
| | | | | | | | | | | |____PeopleDaoImpl.java       --> an implementation of PeopleDao
| | | | | | | | | |____domain
| | | | | | | | | | |____People.java                --> People info
| | | | | | | | | |____resource             
| | | | | | | | | | |____PeopleResource.java        --> RESTful APIs of People Management
| | | | | | | | | |____service
| | | | | | | | | | |____PeopleService.java         --> interface of People service
| | | | | | | | | | |____impl
| | | | | | | | | | | |____PeopleServiceImpl.java   --> an implementation of PeopleService
| | | | | | | | | |____util
| | | | | | | | | | |____JacksonUtil.java           --> a wrapper class for Jackson
| | |____resources
| | | |____config
| | | | |____database.properties                    --> database config
| | | |____log4j.properties                         --> log4j config
| | | |____message
| | | | |____error-message.properties               --> error message mapping for i18n  
| | | |____META-INF
| | | | |____persistence.xml                        --> JPA persistence config
| | | | |____spring
| | | | | |____applicationContext-jpa.xml           --> Spring config for JPA
| | | | | |____applicationContext-wink.xml          --> Spring config for Wink
| | |____webapp
| | | |____index.jsp                                --> A Welcome page
| | | |____WEB-INF
| | | | |____web.xml                                
| |____test
| | |____java
| | | |____com
| | | | |____leonlu
| | | | | |____code
| | | | | | |____sample
| | | | | | | |____webapp
| | | | | | | | |____ws
| | | | | | | | | |____resource
| | | | | | | | | | |____JpaTest.java
| | | | | | | | | | |____PeolpleServiceTest.java
```
## RESTful APIs
* get info of all people
`GET /api/people`
* create a new info of some people
`POST /api/people`
* get info of some people
`GET /api/people/{id}`
* update the info of some people
`PUT /api/people/{id}`
* delete a user
`DELETE /api/people/{id}`
* Request Parameters
    * JSON format: `{"name":"string","age":int}`
* Response Messages
    * Success
        * 200 OK
    * Failure
        * 400 Bad Request
        * 404 People Not Found
        * 500 Internal Server Error
