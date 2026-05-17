# College Management System API Documentation

## Base URL
http://localhost:8080

## Authentication
For protected APIs:

Authorization: Bearer <JWT_TOKEN>

---

# AUTH APIs

## 1. Login

### URL
```http
POST /api/auth/login
```

### Request
```json
{
  "username": "superadmin",
  "password": "admin123"
}
```

### Response
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "superadmin",
  "role": "SUPER_ADMIN",
  "passwordChanged": true
}
```

---

## 2. Change Password

### URL
```http
POST /api/auth/change-password
```

### Request
```json
{
  "oldPassword": "admin123",
  "newPassword": "newAdmin123"
}
```

### Response
```json
"Password changed successfully"
```

---

# USER APIs

## 3. Create User

### URL
```http
POST /api/users
```

### Request
```json
{
  "username": "ravi",
  "email": "ravi@gmail.com",
  "role": "STUDENT"
}
```

### Response
```json
{
  "id": 1,
  "username": "ravi",
  "email": "ravi@gmail.com",
  "role": "STUDENT",
  "isActive": true
}
```

---

## 4. Bulk Create Users

### URL
```http
POST /api/users/bulk
```

### Request
```json
{
  "users": [
    {
      "username": "ravi",
      "email": "ravi@gmail.com",
      "role": "STUDENT"
    },
    {
      "username": "suresh",
      "email": "suresh@gmail.com",
      "role": "FACULTY"
    }
  ]
}
```

### Response
```json
[
  {
    "id": 1,
    "username": "ravi",
    "email": "ravi@gmail.com",
    "role": "STUDENT",
    "isActive": true
  },
  {
    "id": 2,
    "username": "suresh",
    "email": "suresh@gmail.com",
    "role": "FACULTY",
    "isActive": true
  }
]
```

---

## 5. Update User

### URL
```http
PUT /api/users/{id}
```

Example:
```http
PUT /api/users/1
```

### Request
```json
{
  "username": "ravi kumar",
  "email": "ravi@gmail.com",
  "role": "FACULTY"
}
```

### Response
```json
{
  "id": 1,
  "username": "ravi kumar",
  "email": "ravi@gmail.com",
  "role": "FACULTY",
  "isActive": true
}
```

---

## 6. Delete User

### URL
```http
DELETE /api/users/{id}
```

### Response
```json
"User deleted successfully"
```

---

## 7. Get All Active Users

### URL
```http
GET /api/users
```

### Response
```json
[
  {
    "id": 1,
    "username": "ravi",
    "email": "ravi@gmail.com",
    "role": "STUDENT",
    "isActive": true
  }
]
```

---

## 8. Get Users with Pagination

### URL
```http
GET /api/users/pagination?page=0&size=5&search=ravi
```

### Response
```json
{
  "content": [
    {
      "id": 1,
      "username": "ravi",
      "email": "ravi@gmail.com",
      "role": "STUDENT",
      "isActive": true
    }
  ],
  "pageNumber": 0,
  "pageSize": 5,
  "totalElements": 10,
  "totalPages": 2,
  "last": false
}
```

---

# DEPARTMENT APIs

## 9. Create Department

### URL
```http
POST /api/departments
```

### Request
```json
{
  "name": "Computer Science",
  "description": "Computer Science Department",
  "code": "CSE"
}
```

### Response
```json
{
  "id": 1,
  "name": "Computer Science",
  "code": "CSE",
  "description": "Computer Science Department",
  "status": "ACTIVE"
}
```

---

## 10. Get All Departments

### URL
```http
GET /api/departments
```

### Response
```json
[
  {
    "id": 1,
    "name": "Computer Science",
    "code": "CSE",
    "description": "Computer Science Department",
    "status": "ACTIVE"
  }
]
```

---

## 11. Update Department

### URL
```http
PUT /api/departments/{id}
```

### Request
```json
{
  "name": "Information Technology",
  "description": "IT Department",
  "code": "IT",
  "status": "ACTIVE"
}
```

### Response
```json
{
  "id": 1,
  "name": "Information Technology",
  "code": "IT",
  "description": "IT Department",
  "status": "ACTIVE"
}
```

---

## 12. Delete Department

### URL
```http
DELETE /api/departments/{id}
```

### Response
```json
"Department deleted successfully"
```

---

# ACADEMIC BATCH APIs

## 13. Create Academic Batch

### URL
```http
POST /api/academicBatch
```

### Request
```json
{
  "name": "2025-2029",
  "startYear": 2025,
  "endYear": 2029
}
```

### Response
```json
{
  "id": 1,
  "name": "2025-2029",
  "startYear": 2025,
  "endYear": 2029,
  "status": "ACTIVE"
}
```

---

## 14. Get All Academic Batches

### URL
```http
GET /api/academicBatch
```

### Response
```json
[
  {
    "id": 1,
    "name": "2025-2029",
    "startYear": 2025,
    "endYear": 2029,
    "status": "ACTIVE"
  }
]
```

---

## 15. Update Academic Batch

### URL
```http
PUT /api/academicBatch/{id}
```

### Request
```json
{
  "name": "2025-2029",
  "startYear": 2025,
  "endYear": 2029,
  "status": "INACTIVE"
}
```

### Response
```json
{
  "id": 1,
  "name": "2025-2029",
  "startYear": 2025,
  "endYear": 2029,
  "status": "INACTIVE"
}
```

---

## 16. Delete Academic Batch

### URL
```http
DELETE /api/academicBatch/{id}
```

### Response
```json
"Academic batch deleted successfully"
```

---

# SEMESTER APIs

## 17. Create Semester

### URL
```http
POST /api/semesters
```

### Request
```json
{
  "semesterNumber": 1
}
```

### Response
```json
{
  "id": 1,
  "name": "Semester 1",
  "semesterNumber": 1,
  "status": "ACTIVE"
}
```

---

## 18. Get All Semesters

### URL
```http
GET /api/semesters
```

### Response
```json
[
  {
    "id": 1,
    "name": "Semester 1",
    "semesterNumber": 1,
    "status": "ACTIVE"
  }
]
```

---

## 19. Update Semester

### URL
```http
PUT /api/semesters/{id}
```

### Request
```json
{
  "semesterNumber": 2,
  "status": "ACTIVE"
}
```

### Response
```json
{
  "id": 1,
  "name": "Semester 2",
  "semesterNumber": 2,
  "status": "ACTIVE"
}
```

---

## 20. Delete Semester

### URL
```http
DELETE /api/semesters/{id}
```

### Response
```json
"Semester deleted successfully"
```

---

# SUBJECT APIs

## 21. Create Subject

### URL
```http
POST /api/subjects
```

### Request
```json
{
  "code": "CS101",
  "name": "Data Structures",
  "credits": 4,
  "type": "THEORY",
  "description": "Core CS subject"
}
```

### Response
```json
{
  "id": 1,
  "code": "CS101",
  "name": "Data Structures",
  "credits": 4,
  "type": "THEORY",
  "description": "Core CS subject",
  "status": "ACTIVE"
}
```

---

## 22. Get Subjects with Pagination

### URL
```http
GET /api/subjects/pagination?page=0&size=10&search=CS
```

### Response
```json
{
  "content": [
    {
      "id": 1,
      "code": "CS101",
      "name": "Data Structures",
      "credits": 4,
      "type": "THEORY",
      "description": "Core CS subject",
      "status": "ACTIVE"
    }
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 25,
  "totalPages": 3,
  "last": false
}
```

---

## 23. Update Subject

### URL
```http
PUT /api/subjects/{id}
```

### Request
```json
{
  "code": "CS102",
  "name": "Algorithms",
  "credits": 4,
  "type": "THEORY",
  "description": "Algorithm concepts",
  "status": "ACTIVE"
}
```

### Response
```json
{
  "id": 1,
  "code": "CS102",
  "name": "Algorithms",
  "credits": 4,
  "type": "THEORY",
  "description": "Algorithm concepts",
  "status": "ACTIVE"
}
```

---

## 24. Delete Subject

### URL
```http
DELETE /api/subjects/{id}
```

### Response
```json
"Deleted Successfully"
```

---