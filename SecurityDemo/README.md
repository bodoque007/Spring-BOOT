# SecurityDemo

![Java Version](https://img.shields.io/badge/Java-17-informational?style=flat&logo=java)
![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.2.2-brightgreen?style=flat&logo=spring)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-SpringSecurity6-blue?style=flat&logo=thymeleaf)
![MySQL Connector](https://img.shields.io/badge/MySQL%20Connector-runtime-yellow?style=flat&logo=mysql)

Demo security project practice using Spring Boot and Thymeleaf.

## Description

This project serves as a demonstration of security features in a Spring Boot application. Key highlights include:

- **Authentication and Authorization:** Utilizes Spring Security to implement secure authentication and authorization mechanisms.
- **Thymeleaf Templating:** Integrates Thymeleaf for dynamic and expressive HTML templates, enhancing the user interface.
- **MySQL Database:** Persists data using MySQL, providing a robust and scalable database solution.

### Features

- **Login and Logout:** Implements basic login and logout functionality.
- **Role-Based Access Control:** Restricts access to URLs based on the user's assigned roles.
- **JDBC Authentication:** Utilizes custom tables and column names for JDBC authentication.

### Database Setup

The project includes an SQL script (`sql-scripts/spring-security-setup.sql`) that automates the setup of a MySQL database. The script creates tables for members and roles, essential for authentication. Default usernames and roles have been pre-defined for convenience:

- **Usernames:** eren, mary, linus
- **Passwords:** Each username serves as its own password (e.g., username=eren, password=eren).
- **Roles:**
    - eren: ROLE_EMPLOYEE
    - mary: ROLE_EMPLOYEE, ROLE_MANAGER
    - linus: ROLE_EMPLOYEE, ROLE_MANAGER, ROLE_ADMIN

## Prerequisites

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://dev.mysql.com/downloads/)

## Dependencies

- [Spring Boot Starter Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
- [Spring Boot Starter Thymeleaf](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf)
- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Thymeleaf Extras SpringSecurity6](https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity6)
- [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [MySQL Connector](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)
- [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring Security Test](https://mvnrepository.com/artifact/org.springframework.security/spring-security-test)

## Build and Run

1. Clone this repository.
   ```bash
   git clone https://github.com/yourusername/SecurityDemo.git
2. Run as any other spring boot app using maven.
   ```bash
   mvn spring-boot:run
