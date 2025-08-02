package org.example;

import org.example.entity.Employee;
import org.example.entity.EmployeeStatus;
import org.example.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @Bean
    public CommandLineRunner initData(EmployeeService employeeService) {
        return args -> {
            // Initialize with sample data
            if (employeeService.getAllEmployees().isEmpty()) {
                // Create sample employees
                Employee emp1 = new Employee("John", "Doe", "john.doe@company.com", "1234567890",
                        "IT", "Software Engineer", LocalDate.of(2020, 1, 15), new BigDecimal("75000.00"));
                emp1.setStatus(EmployeeStatus.ACTIVE);
                
                Employee emp2 = new Employee("Jane", "Smith", "jane.smith@company.com", "0987654321",
                        "HR", "HR Manager", LocalDate.of(2019, 3, 20), new BigDecimal("65000.00"));
                emp2.setStatus(EmployeeStatus.ACTIVE);
                
                Employee emp3 = new Employee("Mike", "Johnson", "mike.johnson@company.com", "1122334455",
                        "Finance", "Accountant", LocalDate.of(2021, 6, 10), new BigDecimal("55000.00"));
                emp3.setStatus(EmployeeStatus.ACTIVE);
                
                Employee emp4 = new Employee("Sarah", "Wilson", "sarah.wilson@company.com", "5566778899",
                        "Marketing", "Marketing Specialist", LocalDate.of(2022, 2, 28), new BigDecimal("60000.00"));
                emp4.setStatus(EmployeeStatus.ACTIVE);
                
                Employee emp5 = new Employee("David", "Brown", "david.brown@company.com", "9988776655",
                        "IT", "System Administrator", LocalDate.of(2018, 11, 5), new BigDecimal("70000.00"));
                emp5.setStatus(EmployeeStatus.ACTIVE);
                
                // Save employees
                employeeService.createEmployee(emp1);
                employeeService.createEmployee(emp2);
                employeeService.createEmployee(emp3);
                employeeService.createEmployee(emp4);
                employeeService.createEmployee(emp5);
                
                System.out.println("Sample data initialized successfully!");
            }
        };
    }
}