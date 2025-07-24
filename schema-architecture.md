### Section 1: Architecture summary

This Spring Boot application integrates both MVC and RESTful controllers. Admin and Doctor dashboards are rendered using Thymeleaf templates, whereas other modules are served via REST APIs. The system connects to two databases: MySQL, which stores patient, doctor, appointment, and admin data using JPA entities, and MongoDB, which manages prescriptions through document models. All controller requests are funneled through a unified service layer that delegates tasks to the respective repositories. MySQL uses JPA entities while MongoDB uses document models.

### Section 2: Data and control flow

#### 1. User Interface Layer
The system accommodates multiple user types and interaction modes. Users interact with the application via:
- Thymeleaf-based web dashboards, such as AdminDashboard and DoctorDashboard, which serve server-rendered HTML views.
- REST API clients, including mobile apps and frontend modules like Appointments, PatientDashboard, and PatientRecord, which communicate with the backend via HTTP and receive JSON data.
- 
  This clear separation enables the application to support both interactive web experiences and scalable, API-driven integrations.

#### 2. Controller Layer
User actions—such as clicking buttons or submitting forms—trigger HTTP requests that are routed to the appropriate backend controllers based on the URL and HTTP method:
- Thymeleaf Controllers handle requests for server-rendered views and return .html templates populated with dynamic content.
- REST Controllers handle API requests, process inputs, call backend services, and return JSON responses.
  
  Controllers serve as the application's entry points, handling validation and orchestrating the request/response workflow.

#### 3. Service Layer
Controllers delegate logic to the Service Layer, which encapsulates the application's core functionality. It:
- Applies business rules and validations
- Coordinates complex workflows (e.g., verifying doctor availability before scheduling an appointment)
- Maintains a clear separation between the presentation and data layers

  This design improves modularity, testability, and scalability.

#### 4. Repository Layer
The Repository Layer handles database operations and includes:
- MySQL Repositories using Spring Data JPA to manage structured data like users, doctors, appointments, and admins
- MongoDB Repository using Spring Data MongoDB for document-based data like prescriptions

  Repositories abstract the database logic, providing a clean and consistent interface for data access.

#### 5. Database Access
Each repository interacts with a specific database engine:
- MySQL stores core entities with relational schemas, ideal for structured data and enforcing constraints.
- MongoDB stores flexible, schema-less documents like prescriptions, allowing rapid data model evolution.

  This dual-database approach leverages the strengths of both structured and unstructured storage systems.

#### 6. Model Binding
Retrieved data is converted into Java model classes:
- JPA Entities (annotated with @Entity) represent relational MySQL data
- Document Models (annotated with @Document) represent MongoDB documents

  These models provide a unified, object-oriented way to interact with data throughout the application.

#### 7. Application Models in Use
Models serve different purposes in the response layer:
- In MVC workflows, models are passed to Thymeleaf templates for dynamic HTML rendering.
- In REST workflows, models or DTOs are serialized to JSON and returned in API responses.

This concludes the request-response cycle, delivering either full web pages or structured data based on the client type.

