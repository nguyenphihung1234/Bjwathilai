package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Employee;
import org.example.entity.EmployeeStatus;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    // Display all employees
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", employeeService.getAllDepartments());
        model.addAttribute("positions", employeeService.getAllPositions());
        model.addAttribute("activePage", "employees");
        return "employees/list";
    }
    
    // Show employee creation form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("statuses", EmployeeStatus.values());
        model.addAttribute("activePage", "add-employee");
        return "employees/form";
    }
    
    // Create new employee
    @PostMapping
    public String createEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                BindingResult result, Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", EmployeeStatus.values());
            model.addAttribute("activePage", "add-employee");
            return "employees/form";
        }
        
        try {
            employeeService.createEmployee(employee);
            redirectAttributes.addFlashAttribute("success", "Employee created successfully!");
            return "redirect:/employees";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("statuses", EmployeeStatus.values());
            model.addAttribute("activePage", "add-employee");
            return "employees/form";
        }
    }
    
    // Show employee details
    @GetMapping("/{id}")
    public String showEmployee(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("activePage", "employees");
            return "employees/details";
        } else {
            return "redirect:/employees";
        }
    }
    
    // Show employee edit form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("statuses", EmployeeStatus.values());
            model.addAttribute("activePage", "employees");
            return "employees/form";
        } else {
            return "redirect:/employees";
        }
    }
    
    // Update employee
    @PostMapping("/{id}")
    public String updateEmployee(@PathVariable Long id,
                                @Valid @ModelAttribute("employee") Employee employee,
                                BindingResult result, Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", EmployeeStatus.values());
            model.addAttribute("activePage", "employees");
            return "employees/form";
        }
        
        try {
            employeeService.updateEmployee(id, employee);
            redirectAttributes.addFlashAttribute("success", "Employee updated successfully!");
            return "redirect:/employees";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("statuses", EmployeeStatus.values());
            model.addAttribute("activePage", "employees");
            return "employees/form";
        }
    }
    
    // Delete employee
    @PostMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployee(id);
            redirectAttributes.addFlashAttribute("success", "Employee deleted successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/employees";
    }
    
    // Search employees
    @GetMapping("/search")
    public String searchEmployees(@RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) String department,
                                 @RequestParam(required = false) String position,
                                 @RequestParam(required = false) String status,
                                 @RequestParam(required = false) BigDecimal minSalary,
                                 @RequestParam(required = false) BigDecimal maxSalary,
                                 Model model) {
        List<Employee> employees;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            employees = employeeService.searchEmployees(keyword);
        } else if (department != null && !department.trim().isEmpty()) {
            if (minSalary != null && maxSalary != null) {
                employees = employeeService.getEmployeesByDepartmentAndSalaryRange(department, minSalary, maxSalary);
            } else {
                employees = employeeService.getEmployeesByDepartment(department);
            }
        } else if (position != null && !position.trim().isEmpty()) {
            employees = employeeService.getEmployeesByPosition(position);
        } else if (status != null && !status.trim().isEmpty()) {
            employees = employeeService.getEmployeesByStatus(EmployeeStatus.valueOf(status));
        } else if (minSalary != null && maxSalary != null) {
            employees = employeeService.getEmployeesBySalaryRange(minSalary, maxSalary);
        } else {
            employees = employeeService.getAllEmployees();
        }
        
        model.addAttribute("employees", employees);
        model.addAttribute("departments", employeeService.getAllDepartments());
        model.addAttribute("positions", employeeService.getAllPositions());
        model.addAttribute("statuses", EmployeeStatus.values());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedDepartment", department);
        model.addAttribute("selectedPosition", position);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("minSalary", minSalary);
        model.addAttribute("maxSalary", maxSalary);
        model.addAttribute("activePage", "employees");
        
        return "employees/list";
    }
    
    // REST API endpoints for AJAX calls
    @GetMapping("/api/search")
    @ResponseBody
    public List<Employee> searchEmployeesApi(@RequestParam(required = false) String keyword) {
        return employeeService.searchEmployees(keyword);
    }
    
    @GetMapping("/api/departments")
    @ResponseBody
    public List<String> getDepartmentsApi() {
        return employeeService.getAllDepartments();
    }
    
    @GetMapping("/api/positions")
    @ResponseBody
    public List<String> getPositionsApi() {
        return employeeService.getAllPositions();
    }
} 