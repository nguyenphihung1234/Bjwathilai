package org.example.service;

import org.example.entity.Employee;
import org.example.entity.EmployeeStatus;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    // Create new employee
    public Employee createEmployee(Employee employee) {
        // Check if email already exists
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Employee with email " + employee.getEmail() + " already exists");
        }
        return employeeRepository.save(employee);
    }
    
    // Read all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    // Read employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    
    // Read employee by email
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    
    // Update employee
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        
        // Check if email is being changed and if it already exists
        if (!employee.getEmail().equals(employeeDetails.getEmail()) && 
            employeeRepository.existsByEmail(employeeDetails.getEmail())) {
            throw new RuntimeException("Employee with email " + employeeDetails.getEmail() + " already exists");
        }
        
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setPosition(employeeDetails.getPosition());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setSalary(employeeDetails.getSalary());
        employee.setStatus(employeeDetails.getStatus());
        
        return employeeRepository.save(employee);
    }
    
    // Delete employee
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
    
    // Search employees by keyword
    public List<Employee> searchEmployees(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllEmployees();
        }
        return employeeRepository.searchEmployees(keyword.trim());
    }
    
    // Get employees by department
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }
    
    // Get employees by position
    public List<Employee> getEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }
    
    // Get employees by status
    public List<Employee> getEmployeesByStatus(EmployeeStatus status) {
        return employeeRepository.findByStatus(status);
    }
    
    // Get employees by salary range
    public List<Employee> getEmployeesBySalaryRange(BigDecimal minSalary, BigDecimal maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }
    
    // Get employees by department and salary range
    public List<Employee> getEmployeesByDepartmentAndSalaryRange(String department, BigDecimal minSalary, BigDecimal maxSalary) {
        return employeeRepository.findByDepartmentAndSalaryRange(department, minSalary, maxSalary);
    }
    
    // Get employee count by department
    public long getEmployeeCountByDepartment(String department) {
        return employeeRepository.countByDepartment(department);
    }
    
    // Check if employee exists by email
    public boolean employeeExistsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }
    
    // Get all departments
    public List<String> getAllDepartments() {
        return employeeRepository.findAll().stream()
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .toList();
    }
    
    // Get all positions
    public List<String> getAllPositions() {
        return employeeRepository.findAll().stream()
                .map(Employee::getPosition)
                .distinct()
                .sorted()
                .toList();
    }
} 