# Task Management Application

A **Task Management Application** built with Java and Spring Boot that facilitates collaboration and organization within teams or projects. The application provides features like user authentication, task management, team collaboration, and role-based access control.

---

## Features

- **User Management**
  - User registration and secure login with JWT.
  - View and update user profiles.
  - Role-based access control: Project Owner, Team Leader, Team Member.
  
- **Task Management**
  - Create, read, update, and delete tasks.
  - Assign tasks to users and set due dates.
  - Task filtering, sorting, and searching by status, title, or description.

- **Team Collaboration**
  - Create or join teams.
  - Assign roles and tasks within a team.
  - Add comments and attachments to tasks.

---

## Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Tokens)
- **Build Tool**: Gradle

---

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/task-management-app.git
   cd task-management-app

2. ## Set up MySQL database:

1. Create a database named `task_management`.
2. Update `application.yml` or `application.properties` with your MySQL credentials.

## Build and run the application:

```bash
./gradlew bootRun
```

## API Endpoints:

### User Endpoints:
- `POST /api/users/register` - Register a new user.
- `POST /api/users/login` - Login and retrieve JWT token.
- `GET /api/users/{id}` - Get user details by ID.
- `PUT /api/users/{id}` - Update user profile.
- `DELETE /api/users/{id}` - Delete a user.

### Task Endpoints:
- `POST /api/tasks` - Create a new task.
- `GET /api/tasks/{id}` - Get task by ID.
- `GET /api/tasks` - List all tasks (filter by status or search by title/description).
- `PUT /api/tasks/{id}` - Update a task.
- `DELETE /api/tasks/{id}` - Delete a task.

### Team Endpoints:
- `POST /api/teams` - Create a new team.
- `GET /api/teams/{id}` - Get team by ID.
- `PUT /api/teams/{id}` - Update team details.
- `DELETE /api/teams/{id}` - Delete a team.

## Configuration:

Edit the `src/main/resources/application.yml` file to configure your database connection:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/task_management
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## Future Enhancements:

- Real-time notifications and updates using WebSocket.
- Integration with external calendar services (e.g., Google Calendar).
- Role-based dashboard customization.
- Advanced reporting and analytics.

## Contributing:

1. Fork the repository.
2. Create a new branch:

```bash
git checkout -b feature/your-feature-name
```

3. Commit your changes:

```bash
git commit -m "Add your message"
```

4. Push to the branch:

```bash
git push origin feature/your-feature-name
```

5. Submit a pull request.
