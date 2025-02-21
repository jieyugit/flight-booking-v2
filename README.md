## Background

A airline reservation system for database system course project. 

The backend of this project is built in Java.

The frontend of the project:
Linked: [Airline Reservation Frontend](https://github.com/JieYu-Johnny/AirlineTicketFrontEnd)

## Component of Project
- Java
- SpringBoot
- Redis
- MongoDB
- Mysql

## Update history

- 2023/3/3
  - create project and initialize Spring 
- 2023/3/4
  - add Spring Data MongoDB
  - test api for seat
- 2023/3/6
  - create api for user login and sign out
- 2025/2/17
  - Implemented user registration API with username and phone validation
  - Implemented user login API with username and password verification
  - Added JWT generation and validation, returning token after successful login
  - Fixed user data retrieval and storage issues, optimized service layer logic
- 2025/02/21
  feat: Implement partial seat selection functionality
  - Added MongoDB database integration for seat management
  - Implemented logic for storing seat data within the MongoDB collection
  - Updated service to handle seat availability and booking status
