# REST API

![Java Version](https://img.shields.io/badge/Java-17-informational?style=flat&logo=java)
![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.2.2-brightgreen?style=flat&logo=spring)
![MySQL Connector](https://img.shields.io/badge/MySQL%20Connector-runtime-yellow?style=flat&logo=mysql)
![OpenCSV](https://img.shields.io/badge/OpenCSV-5.7.1-green?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAfklEQVR42mP8//8/w0AUgBmLCJxAxqsQCTMQAAAABJRU5ErkJggg==)
![Lombok](https://img.shields.io/badge/Lombok-1.18.30-blue?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAmklEQVR42mP8//8/w0AUgBmLCJxAxqsQCTMQAAAABJRU5ErkJggg==)
![JUnit](https://img.shields.io/badge/JUnit-5-orange?style=flat&logo=junit)
![Mockito](https://img.shields.io/badge/Mockito-3.12.4-red?style=flat&logo=mockito)
![Redis](https://img.shields.io/badge/Redis-6.2.5-red?style=flat&logo=redis)
[![springdoc-openapi](https://img.shields.io/badge/springdoc--openapi-1.6.4-green?style=flat&logo=spring)](https://springdoc.org/)

A simple CRUD (Create, Read, Update, Delete) API project for managing employee records. 
![App documentation](https://github.com/bodoque007/Spring-BOOT/assets/63447579/367e3db8-9001-4cba-93f0-f9daf26e43ba)

## Description

This project provides a RESTful API for basic CRUD operations on an employee database. It leverages the following technologies:

- [Spring Boot](https://spring.io/projects/spring-boot) for building robust and scalable applications.
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) for easy data access with Hibernate.
- [MySQL](https://www.mysql.com/) for data storage and retrieval.
- [OpenCSV](https://github.com/opencsv/opencsv) for CSV parsing.
- [Project Lombok](https://projectlombok.org/) for reducing boilerplate code in Java.
- [JUnit](https://junit.org/) for unit testing.
- [Mockito](https://site.mockito.org/) for mocking objects in tests.
- [Redis](https://redis.io/) for caching and improving requests performance.
- [OpenApi](https://springdoc.org/) for documentation generation.
## How to Run

1. Ensure you have Java 17, Maven and Redis (for caching) installed.
2. Clone this repository.
3. Configure your MySQL database details in the application.properties file.
    - By default, the database is set to localhost:3036 with root as both the username and password. The database should be created using the sql script inside the "sql-scripts" folder.
4. Go to the repository's location
5. Run Redis server
   - Open a terminal and run the following command to start the Redis server. You can change the port Redis will use in application.properties. By default, it's 6484.
     ```bash
     redis-server --port 6484
     ```
     Or add the following to the application.properties file to disable cache.
   - ```bash
     spring.cache.type=none
     ```
6. Run the application using Maven:
   ```bash
   mvn spring-boot:run
7. Get your token by making a POST request to http://localhost:8080/token with with basic authentication using the username "eren" and password "password". You can use tools like cURL or Postman.

## Note on security
The JWT implementation in this project is for educational purposes only. The user "eren" is an in-memory user, and the public and private keys are exposed for demonstration purposes. In a production environment, secure practices should be followed for user authentication and key management.
## Usage
**This is important!**
*While the following documentation is still right, you can better read (and interact) with the swagger documentation for the API at http://localhost:8080/swagger-ui/index.html*

The API adheres to REST principles, making it intuitive to interact with. For example, to retrieve information about an employee with ID 1, simply make a GET request to:

```bash
GET localhost:8080/employee/1
```
For updating or deleting an employee with a specific ID (employee_id), use the corresponding request to:
```bash
PUT or DELETE localhost:8080/employees/{employee_id}
```
When updating, remember to include the employee details in JSON format, specifying the firstName, lastName, and email properties.

To interact with the API, you can use external tools like Postman for POST, PUT, and DELETE requests. Alternatively, for a more user-friendly experience, consider using the version available in crudMVCDemo. It's an MVC CRUD web app that provides a graphical UI for adding, deleting, or updating employees, yet this version is more developed.

If you want to customize the number of employees displayed or control the page size when retrieving all employees (GET URL /employees), you can use query parameters. For example:
```bash
GET http://localhost:8080/employees?size=3
```
By default, the page size is set to 10 and cannot exceed 100. Feel free to adjust the query parameters according to your preferences.
