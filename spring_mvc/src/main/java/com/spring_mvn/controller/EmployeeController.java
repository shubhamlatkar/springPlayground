package com.spring_mvn.controller;

import com.spring_mvn.dto.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee(2, "testEmp", 150.5),
            new Employee(3, "testEmp3", 155.5)));

    @RequestMapping("/employee")
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee");
        modelAndView.addObject("id", 1);
        modelAndView.addObject("name", "test");
        modelAndView.addObject("salary", 150);
        modelAndView.addObject("testEmp", new Employee(2, "testEmp", 150.5));
        modelAndView.addObject("employeeList", employees);
        return modelAndView;
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee) {
        employees.add(employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee");
        modelAndView.addObject("employeeList", employees);
        return modelAndView;
    }
}
