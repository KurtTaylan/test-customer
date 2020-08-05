# CUSTOMER API

1. Instructions
2. Architecture
3. Model
4. API Documentation
5. Concerns


## 1.Instructions

### Build JAR

```
    mvn clean install
```

### Build Docker Image

```
    mvn spring-boot:build-image
```

### Test

Unit Test Line Coverage: %74
```
    Unit Test                :  mvn test
    Unit + Integration Tests :  mvn verify
```

### Run

```
    mvn spring-boot:run
```

## 2.Tech Stack

Used Libraries and Frameworks:
- Spring Boot: 2.3.2
- Spring Web: 2.3.2
- Lombok: 1.18.12
- Mapstruct: 1.31.1
- H2: 1.4.200
- Spring Doc, Open-API: 1.2.32

**Note: Spring Application (Async)Events are used for start point of event-driven system.** 

## 3.Model

**Relation: Customer, 0..1 -> N Address**


*Customer- 0..1*

```
- firstName: String
- lastName: String
- phoneNumber: String
- email: String
```

*Address- N*
```
- type: Enum(Integer)
- city: String
- country: String
- line: String
- customerId: long
```



## 4.API Documentation

Please, Test the API from SpringDoc Panel.

You can check REST Document from following URL;

```
    http://localhost:8080/api/documentation.html
```

## 5.Concerns

1. You cannot retrieve too much record at the same time, even it is not on the spec, basic pagination structure introduced. page: 1, count: 20 default settings.

2. Validation rules remained basic level for sake of demo project

3. Unit Tests created specifically for domain services

4. Integration Tests only created for REST level, Persistence layer Integration tests are not implemented.

   