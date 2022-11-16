# Security and Database with Spring Boot

This is a sample application that shows how to use Spring Security and Spring Data JPA to secure a web application.

## Technologies Used

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![Azure](https://img.shields.io/badge/azure-%230072C6.svg?style=for-the-badge&logo=microsoftazure&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![H2](https://img.shields.io/badge/h2-%2300ADD8.svg?style=for-the-badge&logo=h2&logoColor=white)


## Features

- User registration
- User login
- User logout

## Getting Started

- Clone the repository

```bash
git clone
```

## Navigate to the webapp URL

```bash
https://security-with-database.azurewebsites.net/
```

## Usage

### Register a new user

#### Desktop View
![Register](https://github.com/leonardoapd/Lec91_CustomFormLogin/blob/e447251b36ca2038257ee6059a808a039d34b038/src/main/resources/static/screenshots/signup-desktop.png?raw=true)

#### Mobile View
<img src="https://github.com/leonardoapd/Lec91_CustomFormLogin/blob/e447251b36ca2038257ee6059a808a039d34b038/src/main/resources/static/screenshots/signup-mobile.png?raw=true" width="200" height="400" alt="Register">

### Login

#### Desktop View
![Login](https://github.com/leonardoapd/Lec91_CustomFormLogin/blob/e447251b36ca2038257ee6059a808a039d34b038/src/main/resources/static/screenshots/login-desktop.png?raw=true)

#### Mobile View
<img src="https://github.com/leonardoapd/Lec91_CustomFormLogin/blob/e447251b36ca2038257ee6059a808a039d34b038/src/main/resources/static/screenshots/login-mobile.png?raw=true" width="200" height="400" alt="Login">

### Home

#### Desktop View
![Home](https://github.com/leonardoapd/Lec91_CustomFormLogin/blob/e447251b36ca2038257ee6059a808a039d34b038/src/main/resources/static/screenshots/index-desktop.png?raw=true)

#### Mobile View
<img src="https://github.com/leonardoapd/Lec91_CustomFormLogin/blob/e447251b36ca2038257ee6059a808a039d34b038/src/main/resources/static/screenshots/index-mobile.png?raw=true" width="200" height="400" alt="Home">

### Permission Denied

#### Desktop View
![Permission Denied](https://github.com/leonardoapd/Lec91_CustomFormLogin/blob/e447251b36ca2038257ee6059a808a039d34b038/src/main/resources/static/screenshots/permission-denied-desktop.png?raw=true)

#### Mobile View
<img src="https://github.com/leonardoapd/Lec91_CustomFormLogin/blob/e447251b36ca2038257ee6059a808a039d34b038/src/main/resources/static/screenshots/permission-denied-mobile.png?raw=true" width="200" height="400" alt="Permission Denied">



## Running the application

To run the application with maven, you can run the following command:

    mvn spring-boot:run

## Running the application with Docker

To run the application with Docker, you need to have Docker installed, and the dockerfile in the root of the project.

The sample of the dockerfile is:


    FROM eclipse-temurin:17-alpine
    RUN addgroup -S spring && adduser -S spring -G spring
    USER spring:spring
    ARG JAR_FILE=target/*.jar
    COPY ${JAR_FILE} myjar.jar
    EXPOSE 3000
    ENTRYPOINT ["java","-jar","/myjar.jar"]

With the dockerfile ready, it is time to build the jar file with maven:

    mvn install

Then, you can build the docker image with the following command:

    docker build -t spring-security-jpa .
    docker run -p 8080:3000 spring-security-jpa


## Pushing the image to Azure Container Registry

To push the image to Azure Container Registry, you need to have the Azure CLI installed and logged in.

First, you need to create a resource group:

    az group create --name myResourceGroup --location westeurope

Then, you need to create the Azure Container Registry:

    az acr create --resource-group myResourceGroup --name myRegistry --sku Basic

To push the image to the registry, you need to tag it with the registry name:
    
    docker tag spring-security-jpa myRegistry.azurecr.io/spring-security-jpa
    
Then, you need to log in to the registry:
    
    az acr login --name myRegistry
    
Finally, you can push the image to the registry:
    
    docker push myRegistry.azurecr.io/spring-security-jpa

