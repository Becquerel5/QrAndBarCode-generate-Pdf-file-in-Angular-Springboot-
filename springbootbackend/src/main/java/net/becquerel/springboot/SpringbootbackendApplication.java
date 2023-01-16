package net.becquerel.springboot;

import net.becquerel.springboot.controller.EmployeeController;
import net.becquerel.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class SpringbootbackendApplication implements CommandLineRunner {


	public static void main(String[] args) {
        new File(EmployeeController.uploadDirectory).mkdir();
        SpringApplication.run(SpringbootbackendApplication.class, args);
	}

	@Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
      /*  Employee employee = new Employee();
        employee.setFirstName("babay1");
        employee.setLastName("babyuuuu1");
       employee.setEmailId("just1@gmail.com");
       employee.setUrl("scqsc");
        employee.setUrl2("qscqsc");
        employeeRepository.save(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("babay22");
        employee2.setLastName("bayyyuu2");
        employee2.setEmailId("just22@gmail.com");
        employee2.setUrl("qscqsc");
        employee2.setUrl2("qscqsc");
        employeeRepository.save(employee2);*/
    }
}
