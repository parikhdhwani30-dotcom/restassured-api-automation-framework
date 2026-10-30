# REST Assured API Automation Framework
**Author:** Dhwani Parikh  
**Technology Stack:** Java 11+, REST Assured 5, TestNG, Maven, Jackson (POJO), Parallel HTTP Execution

---

## 1. Overview
This repository contains an enterprise **REST API Automation Testing Framework** built with **REST Assured** and **TestNG**. It implements the API Client pattern with POJO request/response serialization via **Jackson Databind**, centralized Request/Response Specifications, and thread-safe **parallel test execution**.

Target API under test: [ReqRes REST API](https://reqres.in/)

---

## 2. Key Architectural Highlights
- **API Client Abstraction Pattern:** All HTTP requests (`GET`, `POST`, `PUT`, `DELETE`) are isolated inside `UserApiClient`, decoupling test assertions from endpoint communication.
- **Request & Response Specification Builders:** `SpecFactory` centralizes base URIs, JSON Content-Type headers, and reusable HTTP status code contracts.
- **Strongly Typed POJO Models:** `UserRequest` and `UserResponse` handle serialization and deserialization via Jackson, avoiding loose string parsing.
- **Parallel Test Execution:** Configured via `testng-api-parallel.xml` using `parallel="methods"` and `thread-count="4"` for lightning-fast API regression runs.
- **Complete CRUD Coverage:** Automated end-to-end tests covering creation (201), retrieval (200), updates (200), deletion (204), and error handling (404).

---

## 3. Project Structure
```text
restassured-api-automation-framework/
├── src/
│   ├── main/java/com/api/
│   │   ├── client/             # UserApiClient (API wrapper methods)
│   │   ├── models/             # Request & Response POJOs (UserRequest, UserResponse)
│   │   └── specs/              # SpecFactory (Request/Response specifications)
│   └── test/
│       ├── java/com/api/
│       │   └── tests/          # UserApiTests (CRUD & Negative test methods)
│       └── resources/
│           └── testng-api-parallel.xml # TestNG parallel test configuration
├── pom.xml                     # Maven project configuration
└── README.md
```

---

## 4. Execution Commands

### Run Parallel API Test Suite
```bash
mvn clean test
```
