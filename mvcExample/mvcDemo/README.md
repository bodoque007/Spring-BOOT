# Spring Boot Form Validation Project

This is a basic Spring Boot project that demonstrates form validation using Thymeleaf.

## Dependencies

- **Spring Boot Starter Thymeleaf**: For building the web interface.
- **Spring Boot Starter Web**: For basic web-related functionalities.
- **Spring Boot DevTools**: For automatic application restarts during development.
- **Spring Boot Starter Validation**: For form validation support.
- **Spring Boot Starter Test**: For testing the application.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed.
- [Maven](https://maven.apache.org/) build tool.

### Running the Application
1. Navigate to the project directory
2. (OPTIONAL) Configure your MySQL database details in the application (By default, "root" user with password "root" with database on localhost:3306).
3Build and run the application:

    ```bash
    mvn spring-boot:run
    ```
4. Open your web browser and go to [http://localhost:8080](http://localhost:8080) to access the form. The validations check that the customer's favorite number is between 0 and 100, the last name is not null and that the couponCode begins with "bodoque".

## Features

- **Form Validation**: Demonstrates basic form validation using Spring Boot.
- **Thymeleaf Templates**: Utilizes Thymeleaf for server-side rendering of HTML templates.
- **DevTools Integration**: Enables automatic restarts during development.
