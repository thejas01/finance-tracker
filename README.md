# Documentation

🧭 API  Gateway
Port: 8080
Purpose: Routes all requests to appropriate microservices.
No direct APIs, just routing via configured application.yml (e.g., /users/** → User Service)

🧬 Eureka Server
Port: 8761

URL: <http://localhost:8761>

Purpose: Service registry UI – used internally for service discovery.

👤 User Service
Base Path: /users
Port: 8081

Method Endpoint Description
POST /users Create a new user
GET /users Get all users
GET users/{id} Get user by ID

💳 Transaction Service
Base Path: /transactions
Port: 8082

Method Endpoint Description
POST/transactions Create a transaction
GET /transactions Get all transactions
GET /transactions/user/{userId} Get transactions by user
GET /transactions/user/{userId}/month/{month} Get monthly report by user

📊 Report Service
Base Path: /reports
Port: 8083

Method Endpoint Description
GET /reports/{userId}/monthly/{month} Get monthly summary report (category-wise total)

💰 Budget Service
Base Path: /budgets
Port: 8087

Method Endpoint Description
POST /budgets Create budget for a user
GET /budgets/user/{userId} Get all budgets of a user
GET /budgets/status/{userId} Get difference between spent and limit
GET /budgets/alerts/{userId} Get overspending alert

📦 Inter-Service (Feign APIs)
These are not public APIs, but used internally:

budget-service ↔ transaction-service

To get all transactions for budget comparison

⚙️ Example Full URLs (via API Gateway)

Purpose Full URL
Get user by ID <http://localhost:8080/users/1>
Get all users <http://localhost:8080/users>
Add transaction  <http://localhost:8080/transactions>
Get monthly report <http://localhost:8080/reports/1/monthly/4>
Get budgets <http://localhost:8080/budgets/user/1>
Get alerts <http://localhost:8080/budgets/alerts/1>
