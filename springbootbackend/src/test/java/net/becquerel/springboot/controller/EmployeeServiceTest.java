package net.becquerel.springboot.controller;


import net.becquerel.springboot.exception.ResourceNotFoundException;
import net.becquerel.springboot.model.Employee;
import net.becquerel.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;



    @Test
    void saveEmployee(){
       //given
        Employee employee = new Employee();
        employee.setFirstName("DTFB");
        employee.setLastName("DTFB2");
        employee.setEmailId("email@gmail.com");
      //when
     when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
     Employee saveEmployee = employeeRepository.save(employee);

        //then
        assertThat(saveEmployee.getFirstName()).isEqualTo("DTFB");

    }


    @Test
    void saveEmployee2(){
        //GIVEN
            Employee employee1 = new Employee();
            employee1.setFirstName("test2");
            employee1.setLastName("lat2");
        //WHEN
            when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);
            Employee saveEmployee = employeeRepository.save(employee1);
        //THEN
            assertThat(saveEmployee.getLastName()).isEqualTo("lat2");
        //AND
            throw new ResourceNotFoundException("sUCCESS");
    }

    @Test
    void findEmployeeById(){
        //Given
            Employee employee = new Employee();
            employee.setId(1L);
            employee.setFirstName("firstn");
            employee.setLastName("setla");
        //when
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee saveemp = employeeRepository.save(employee);



        //THen
        assertThat(employee.getId()).isEqualTo(1L);
    }


    @Test
    void findEmployeeById2(){
        //Given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("firstn");
        employee.setLastName("setla");
        //when
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee saveemp = employeeRepository.save(employee);
        Optional<Employee> findId= employeeRepository.findById(saveemp.getId());

        //Optional<Employee> ofResult = Optional.of(employee);
        //when(employeeRepository.findById((Long) any())).thenReturn(ofResult);


        //THen
       assertThat(findId).isEqualTo(1L);
    }


}
