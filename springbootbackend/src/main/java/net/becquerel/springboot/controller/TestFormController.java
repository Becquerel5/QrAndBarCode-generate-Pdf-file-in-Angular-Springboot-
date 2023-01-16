package net.becquerel.springboot.controller;


import net.becquerel.springboot.model.Employee;
import net.becquerel.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/form/")
public class TestFormController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("saveform1")
    public Employee createEmployee( @RequestBody Employee employee) throws IOException {
        employee.setEmailId(employee.getEmailId());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setUrl("nullform1");
       // employee.setLogo(null);
        return  employeeRepository.save(employee);
        // return "save successful";

    }

    @PostMapping("saveform2")
    public Employee createEmployee2( @RequestBody Employee employee) throws IOException {
        employee.setEmailId(employee.getEmailId());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setUrl("nullform2");
       // employee.setLogo(null);
        return  employeeRepository.save(employee);
        // return "save successful";

    }
}
