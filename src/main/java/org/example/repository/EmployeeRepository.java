package org.example.repository;

import org.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Find by email
    Employee findByEmail(String email);
    
    // Find by department
    List<Employee> findByDepartment(String department);
    
    // Find by position
    List<Employee> findByPosition(String position);
    
    // Find by status
    List<Employee> findByStatus(org.example.entity.EmployeeStatus status);
    
    // Find by first name or last name containing (case insensitive)
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);
    
    // Find by salary range
    List<Employee> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary);
    
    // Custom search query for keyword search
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.department) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.position) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Employee> searchEmployees(@Param("keyword") String keyword);
    
    // Find employees by department and salary range
    @Query("SELECT e FROM Employee e WHERE e.department = :department AND e.salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findByDepartmentAndSalaryRange(
            @Param("department") String department,
            @Param("minSalary") BigDecimal minSalary,
            @Param("maxSalary") BigDecimal maxSalary);
    
    // Count employees by department
    long countByDepartment(String department);
    
    // Check if email exists
    boolean existsByEmail(String email);
} 