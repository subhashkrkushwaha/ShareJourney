<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Journey Sharing App - README</title>
</head>
<body style="font-family: Arial, sans-serif; line-height: 1.6;">
    
    <h1>🚀 Student Journey Sharing App</h1>
    <p><em>A platform where students create accounts, share their professional journey, explore others' journeys, and grow together.</em></p>

    <hr>

    <h2>📘 Overview</h2>
    <p>The <strong>Student Journey Sharing App</strong> is a mobile + backend platform designed for students to share their professional paths, achievements, and experiences. It allows users to create accounts, post their journeys, and view others’ posts for inspiration.</p>
    <p>This project includes:</p>
    <ul>
        <li>📱 <strong>Android Mobile App</strong> built using Java + Retrofit + XML UI</li>
        <li>🖥️ <strong>Spring Boot Backend</strong> with REST APIs</li>
        <li>🛢️ <strong>MySQL Database</strong> using Spring Data JPA</li>
        <li>🔐 <strong>JWT Authentication</strong> with Spring Security</li>
    </ul>

    <hr>

    <h2>🛠️ Tech Stack</h2>

    <h3>📱 Frontend (Android App)</h3>
    <ul>
        <li>Android Studio</li>
        <li>Java</li>
        <li>Retrofit (API communication)</li>
        <li>XML (UI design)</li>
    </ul>

    <h3>🖥️ Backend</h3>
    <ul>
        <li>Spring Boot</li>
        <li>Spring MVC</li>
        <li>Spring JPA</li>
        <li>Spring Security</li>
        <li>JWT Authentication</li>
        <li>RESTful API</li>
    </ul>

    <h3>🛢️ Database</h3>
    <ul>
        <li>MySQL</li>
    </ul>

    <hr>

    <h2>✨ Features</h2>

    <h3>👤 User Module</h3>
    <ul>
        <li>User Registration</li>
        <li>User Login</li>
        <li>Secure JWT-based Session Authentication</li>
        <li>Profile Management</li>
    </ul>

    <h3>📝 Journey Module</h3>
    <ul>
        <li>Create and share your professional journey</li>
        <li>View journeys shared by other students</li>
        <li>Read career paths, experiences, and stories</li>
        <li>Search or filter journeys (optional)</li>
    </ul>

    <h3>🔐 Security</h3>
    <ul>
        <li>JWT Token-based authentication</li>
        <li>Password hashing</li>
        <li>Secured API endpoints</li>
        <li>Validation for safe inputs</li>
    </ul>

    <hr>

    <h2>📱 Android App Functionality</h2>
    <ul>
        <li>User can create an account</li>
        <li>Login using email and password</li>
        <li>Post new journey details</li>
        <li>View journey list from other students</li>
        <li>Retrofit used for backend communication</li>
        <li>Interactive, responsive UI using XML</li>
        <li>Handles loading and error states</li>
    </ul>

    <hr>

    <h2>🖥️ Backend Workflow (Spring Boot)</h2>
    <ul>
        <li>Handles REST API requests from Android app</li>
        <li>Manages authentication using JWT + Spring Security</li>
        <li>Stores user & journey data in MySQL</li>
        <li>Uses Spring Data JPA for database operations</li>
        <li>Serves JSON responses</li>
    </ul>

    <hr>

    <h2>📁 Project Structure</h2>

    <h3>Backend (Spring Boot)</h3>
    <pre>
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── entity/
 ├── security/
 ├── dto/
 └── config/
    </pre>

    <h3>Android App</h3>
    <pre>
app/
 ├── java/
 │    ├── activities/
 │    ├── adapters/
 │    ├── models/
 │    ├── network/
 │    └── utils/
 ├── res/
 │    ├── layout/
 │    ├── drawable/
 │    └── values/
 └── AndroidManifest.xml
    </pre>

    <hr>

    <h2>🧪 API Endpoints (Examples)</h2>

    <h3>Authentication</h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>Method</th>
            <th>Endpoint</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>POST</td>
            <td>/api/auth/register</td>
            <td>Register new user</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>/api/auth/login</td>
            <td>User login + JWT token</td>
        </tr>
    </table>

    <h3>Journey</h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>Method</th>
            <th>Endpoint</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>POST</td>
            <td>/api/journey</td>
            <td>Create new journey</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/api/journey</td>
            <td>Get all journeys</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/api/journey/{id}</td>
            <td>Get single journey</td>
        </tr>
    </table>

    <hr>

    <h2>🚀 How to Run the Project</h2>

    <h3>Backend</h3>
    <pre>
git clone &lt;repo-url&gt;
cd backend
mvn install
mvn spring-boot:run
    </pre>

    <h3>Database Setup</h3>
    <pre>
CREATE DATABASE student_journey;
    </pre>
    <p>Update <strong>application.properties</strong> with your MySQL credentials.</p>

    <h3>Android App</h3>
    <ol>
        <li>Open Android Studio</li>
        <li>Import the project</li>
        <li>Update Retrofit base URL</li>
        <li>Run on emulator or device</li>
    </ol>

    <hr>

    <h2>🔐 JWT Authentication Flow</h2>
    <ol>
        <li>User logs in → Backend validates credentials</li>
        <li>Backend generates JWT token</li>
        <li>Android stores token in SharedPreferences</li>
        <li>API calls include <code>Authorization: Bearer &lt;token&gt;</code></li>
        <li>Backend validates token on each request</li>
    </ol>

    <hr>

    <h2>🙌 Contributions</h2>
    <p>Contributions, issues, and feature requests are welcome!</p>

    <hr>

    <h2>📄 License</h2>
    <p>This project is licensed under the MIT License.</p>

</body>
</html>
