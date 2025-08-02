package org.example.controller;

import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/")
    public String home(Model model) {
        // Add dashboard statistics
        model.addAttribute("totalEmployees", employeeService.getAllEmployees().size());
        model.addAttribute("departments", employeeService.getAllDepartments());
        model.addAttribute("positions", employeeService.getAllPositions());
        model.addAttribute("activePage", "dashboard");
        
        // Get department counts
        model.addAttribute("departmentCounts", employeeService.getAllDepartments().stream()
                .collect(java.util.stream.Collectors.toMap(
                        dept -> dept,
                        dept -> employeeService.getEmployeeCountByDepartment(dept)
                )));
        
        return "home";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return home(model);
    }
} 