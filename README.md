﻿# Product-Management-System

This Product Management System is built using Spring Boot and integrates with MongoDB for storing reviews. The system allows managing products, categories, reviews, and orders with various features like authentication, role-based access control, and pagination. Below is an overview of the system including the ERD and the endpoints we created.

### Technologies Used

- Java JDK 21
- Maven
- Spring Boot
- Spring Data JPA
- postgresql
- Spring Data MongoDB
- Database (MongoDB, Postgres)

### ERD (Entity-Relationship Diagram)

![Screenshot (7)](https://github.com/user-attachments/assets/c0068c17-8d21-4ea7-a400-400adec01162)

### Endpoints

### Product Endpoints

- #### Get All Products
  - GET `/products`
  - Description: Retrieve a paginated list of products.
  - Query Params: page, size, sortBy, sortDir
  
- #### Get Product by ID
  - GET `/products/{id}`
  - Description: Retrieve a specific product by its ID.
  
- #### Create Product
  - POST` /products`
  - Description: Create a new product.
  - Body: `{ "name": "Product Name", "description": "Product Description", "price": 100.0, "category": {id: "categoryId"}, "quantity": 10 }`

- #### Update Product
  - PUT` /products/{id}`
  - Description: Update an existing product by its ID.
  - Body: { "name": "Updated Name", "description": "Updated Description", "price": 150.0, "categoryId": "CategoryId", "quantity": 5 }

- #### Delete Product
  - DELETE `/products/{id}`
  - Description: Delete a product by its ID.

### Category Endpoints

- #### Get All Categories
  - GET `/categories`
  - Description: Retrieve all categories.
  
- #### Create Category
  - POST `/categories`
  - Description: Create a new category.
  - Body: { "name": "Category Name", "parentCategoryId": "ParentCategoryId" }
- #### Update Category
  - PUT `/categories/{id}`
  - Description: Update an existing category by its ID.
  - Body: { "name": "Updated Category Name", "parentCategoryId": "ParentCategoryId" }
  
- #### Delete Category
  - DELETE `/categories/{id}`
  - Description: Delete a category by its ID.

### Order Endpoints

- #### Get All Orders
  - GET `/orders`
  - Response: List of orders (admin only)

- #### Get Order by ID
  - GET `/orders/{id}`
  - Path Param: id
  - Response: Order details
  
- ####  Add Order
  - POST `/orders`
  - Request Body: Order object
  - Response: Created order (buyer only)

### Review Endpoints

- #### Get Reviews by Product ID
  - GET `/reviews/product/{productId}`
  - Path Param: productId
  - Response: List of reviews for a product

- #### Get Reviews by User ID
  - GET` /reviews/user/{userId}`
  - Path Param: userId
  - Response: List of reviews by a user

- #### Add Review
  - POST `/reviews`
  - Request Body: Review object
  - Response: Created review (authenticated users only)

### Authentication and Authorization
- #### Login
  - POST `/auth/login`
  - Request Body: { "username": "user", "password": "pass" }
  - Response: Session with user role

- #### Logout
  - POST `/auth/logout`
  - Response: Session invalidation

### Role-Based Access

- Admin: Can manage all resources (products, orders, reviews)
- Seller: Can manage their own products.
- Buyer: Can make orders and write reviews.


### Demo Loom Link:  https://www.loom.com/share/f8be4a0eae1640298cedba60a393135e?sid=cae7d3f7-8a52-44a0-800d-dd8877666c70
https://www.loom.com/share/be2afbb0c24140db90c55d7b7cc6099e?sid=c31f54f9-9212-4f47-bf46-76c074110b70
