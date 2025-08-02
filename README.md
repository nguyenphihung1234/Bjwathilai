# Salary Management System

A comprehensive employee salary management system built with Spring Boot, JPA Hibernate, and Thymeleaf.

## 🚀 Features

### Core Functionality
- **Create Employee**: Add new employees with complete information
- **Read Employee**: View employee details and list all employees
- **Update Employee**: Edit existing employee information
- **Delete Employee**: Remove employees from the system
- **Search Employee**: Advanced search functionality with multiple filters

### Advanced Features
- **Dashboard**: Overview with statistics and department distribution
- **Advanced Search**: Search by keyword, department, position, status, and salary range
- **Employee Status Management**: Track employee status (Active, Inactive, Terminated, On Leave)
- **Responsive Design**: Modern UI with Bootstrap 5 and Font Awesome icons
- **Form Validation**: Client-side and server-side validation
- **Database Console**: H2 database console for data management

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.2.0
- **Database**: H2 Database (in-memory)
- **ORM**: JPA Hibernate
- **Frontend**: Thymeleaf, Bootstrap 5, jQuery
- **Validation**: Bean Validation (Jakarta)
- **Build Tool**: Maven

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Any modern web browser

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd Bjwathilai
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

### 4. Access the Application
- **Main Application**: http://localhost:8080
- **H2 Database Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:employeedb`
  - Username: `sa`
  - Password: `password`

## 📱 Application Structure

### Pages
1. **Dashboard** (`/`) - Overview with statistics
2. **Employee List** (`/employees`) - View all employees with search
3. **Add Employee** (`/employees/new`) - Create new employee
4. **Employee Details** (`/employees/{id}`) - View employee information
5. **Edit Employee** (`/employees/{id}/edit`) - Update employee information

### API Endpoints
- `GET /employees` - List all employees
- `POST /employees` - Create new employee
- `GET /employees/{id}` - Get employee by ID
- `POST /employees/{id}` - Update employee
- `POST /employees/{id}/delete` - Delete employee
- `GET /employees/search` - Search employees
- `GET /employees/api/search` - AJAX search endpoint
- `GET /employees/api/departments` - Get all departments
- `GET /employees/api/positions` - Get all positions

## 🎯 Sample Data

The application comes pre-loaded with 5 sample employees:
- John Doe (IT - Software Engineer)
- Jane Smith (HR - HR Manager)
- Mike Johnson (Finance - Accountant)
- Sarah Wilson (Marketing - Marketing Specialist)
- David Brown (IT - System Administrator)

## 🔍 Search Features

### Keyword Search
Search across multiple fields:
- First Name
- Last Name
- Email
- Department
- Position

### Filter Options
- **Department**: Filter by specific department
- **Position**: Filter by job position
- **Status**: Filter by employee status
- **Salary Range**: Filter by minimum and maximum salary

## 🎨 UI Features

### Modern Design
- Responsive Bootstrap 5 layout
- Beautiful gradient sidebar
- Card-based content organization
- Font Awesome icons throughout

### Interactive Elements
- Confirmation modals for delete operations
- Auto-formatting for phone numbers and salary
- Real-time form validation
- Flash messages for user feedback

### Data Visualization
- Employee count statistics
- Department distribution table
- Employment duration calculations
- Salary breakdown (annual/monthly)

## 🛡️ Security & Validation

### Input Validation
- Required field validation
- Email format validation
- Phone number format validation
- Salary range validation
- Date validation

### Data Integrity
- Unique email constraint
- Proper error handling
- Transaction management
- Data validation at service layer

## 📊 Database Schema

### Employee Table
```sql
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    department VARCHAR(100) NOT NULL,
    position VARCHAR(100) NOT NULL,
    hire_date DATE NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE'
);
```

## 🔧 Configuration

### Application Properties
- **Database**: H2 in-memory database
- **JPA**: Hibernate with auto DDL
- **Server**: Port 8080
- **Thymeleaf**: Template caching disabled for development

### Customization
You can modify the following in `application.properties`:
- Database configuration
- Server port
- Logging levels
- Thymeleaf settings

## 🚀 Deployment

### Development
```bash
mvn spring-boot:run
```

### Production
```bash
mvn clean package
java -jar target/Bjwathilai-1.0-SNAPSHOT.jar
```

## 📝 API Documentation

### Employee Object Structure
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@company.com",
  "phoneNumber": "123-456-7890",
  "department": "IT",
  "position": "Software Engineer",
  "hireDate": "2020-01-15",
  "salary": 75000.00,
  "status": "ACTIVE"
}
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions:
- Check the documentation
- Review the code comments
- Open an issue on GitHub

## 🎉 Demo

The application is ready for immediate demo with:
- Pre-loaded sample data
- Complete CRUD functionality
- Advanced search capabilities
- Modern, responsive UI
- Database console access

Access the application at http://localhost:8080 after starting the server. 