# Movies - RESTful API Exercise

This is an exercise using Java and Spring Boot

## Prerequisites

  * [JDK (Java SE Development Kit) 8](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html) 
  * [Eclipse IDE](https://www.eclipse.org/downloads/)
  * [Maven](https://maven.apache.org/) (in order to run through console)
  * [Postman](https://www.postman.com/) (in order to access and test the endpoints)


## Quick Start

## 1. Clone the project from github:

```shell
$ git clone https://github.com/gbparada28/movies.git
```

## 2. Run the application:

### 2.1. Through Eclipse IDE:<brbr>

#### 2.1.1. Open Eclipse IDE

#### 2.1.3. Click <b>File > Import</b>

#### 2.1.4. Type Maven in the search box under <b>Select an import wizard:</b>

#### 2.1.5. Select <b>Existing Maven Projects</b> and then click <b>Next</b>

#### 2.1.6. Click <b>Browse</b> and select the folder that is the root of the Maven project (contains the pom.xml file)

#### 2.1.7. Click <b>Finish</b>

#### 2.1.8. Find `src/main/java/com/gabriel/movies/MoviesApplication.java`
#### 2.1.9. Right click and then: `Run as > Java Application` <br><br>

### 2.2. Through console:

#### 2.2.1. Enter the project folder and run maven 
```shell
$ cd movies
$ mvn spring-boot:run
```

## 3. Using the API:

### 3.1. The API will run on `http://localhost:8080` by default

### 3.2. Upload a movies list file to `http://localhost:8080/movies/import/` via `POST` method (body should be form-data with a "file" parameter). Use <b>Postman</b> for this or your prefered API tester.

### 3.3. You can find an example file in `src/main/resources/files`

### 3.4. Perform a `GET` request to `http://localhost:8080/producers/getIntervals` in order to obtain the producers with the minimum and the maximum intervals between awards.<br><br>

## 4. Running integration tests:

### 4.1. Through Eclipse IDE:

#### 4.1.1. Find `src/test/java/com/gabriel/movies/MoviesApplicationTests.java` 
#### 4.1.2. Right click and then: `Run as > JUnit Test`<br><br>

### 4.2. Through console:
### 4.2.1. Run:
```shell
$ mvn test
```







