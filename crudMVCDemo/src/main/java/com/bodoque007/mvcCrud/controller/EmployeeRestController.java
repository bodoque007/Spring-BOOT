package com.bodoque007.mvcCrud.controller;

import com.bodoque007.mvcCrud.entity.Employee;
import com.bodoque007.mvcCrud.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employee")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "/employee/list-employees";
    }
    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "/employee/addEmployee";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employee/list";
    }
    @GetMapping("/updateEmployee")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
        Employee employee = employeeService.findById(id);
        // This is so thymeleaf pre-populates the form with the employee's to be updated data.
        model.addAttribute("employee", employee);
        return "/employee/addEmployee";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.deleteById(id);
        return "redirect:/employee/list";
    }
}
