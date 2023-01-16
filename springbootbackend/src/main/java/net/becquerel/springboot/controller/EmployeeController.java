package net.becquerel.springboot.controller;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.becquerel.springboot.Exportdoc.PdfDoc;
import net.becquerel.springboot.exception.ResourceNotFoundException;
import net.becquerel.springboot.message.Response;
import net.becquerel.springboot.model.Employee;
import net.becquerel.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/becquerel/employees/")
public class EmployeeController {


    private EmployeeRepository employeeRepository;

    @Value("spring.mail.username")
    private String sender;


    @Value("${spring.mail.username}")
    private String sendermail;

    //private PdfDoc pdfDoc;


    public static  String uploadDirectory =System.getProperty("user.dir")+"/fileuploads/";

    public EmployeeController(EmployeeRepository employeeRepository, PdfDoc pdfDoc) {
        this.employeeRepository = employeeRepository;
       // this.pdfDoc = pdfDoc;
    }

    @PostMapping("save")
    public ResponseEntity<Response> createEmployee2(
            @RequestParam("file") MultipartFile file,
            @RequestParam("file2") MultipartFile file2,
            @RequestParam("file3") MultipartFile file3,
            @RequestParam String user
        ) throws IOException, JsonParseException, JsonMappingException {
        String imagepath=uploadDirectory+file2.getOriginalFilename();
        String imagepath3=uploadDirectory+file3.getOriginalFilename();
        file2.transferTo(new File(imagepath));
        file3.transferTo(new File(imagepath3));
        //return (ResponseEntity<Response>) ResponseEntity.status(HttpStatus.OK);

        Employee employee = new ObjectMapper().readValue(user,Employee.class);
        //employee.setLogo(file.getBytes());
        employee.setUrl(imagepath);
        employee.setUrl2(imagepath3);
        employee.setFileName(file.getOriginalFilename());
        Employee dbEmployee = employeeRepository.save(employee);

        if (dbEmployee != null){
            return new ResponseEntity<Response>(new Response("User saved succesfully"), HttpStatus.OK);
        }else{
            return new ResponseEntity<Response>(new Response("user is not saved"), HttpStatus.BAD_REQUEST);
        }








    }



    @GetMapping
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }


    @GetMapping(value = "/download/employeepdf")
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
        List<Employee> list = (List<Employee>) employeeRepository.findAll();
        ByteArrayInputStream bis = PdfDoc.employeePDFreport(list);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline; file = employee.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF).
                body(new InputStreamResource(bis)
        );
    }




    //posy restapi
    @PostMapping("save1")
    public Employee createEmployee(
            @RequestBody Employee employee,
            @RequestParam("file") MultipartFile file
            //@RequestParam("file2") MultipartFile file2
            //@RequestParam("firstName") String  firstName,
            //@RequestParam("lastName") String lastName,
            //@RequestParam("emailId") String emailId
            ) throws IOException {
        String imagepath=uploadDirectory+file.getOriginalFilename();
        file.transferTo(new File(imagepath));
        /*String imagepath2=uploadDirectory+file2.getOriginalFilename();
        try {
            file.transferTo(new File(imagepath));
            file2.transferTo(new File(imagepath2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


       // Employee employee = new Employee();
        employee.setEmailId(employee.getEmailId());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setUrl(imagepath);
       // employee.setUrl2(imagepath2);
        return  employeeRepository.save(employee);
       // return "save successful";

    }
    //get eployee by ID
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return  ResponseEntity.ok(employee);
    }

    //update rest api
    @PutMapping("{id}")
    public  ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updateEmployee =employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id:" +id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(employeeDetails);
        return  ResponseEntity.ok(updateEmployee);
    }

    //delete rest apr
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee doesnot exist"));
        employeeRepository.delete(employee);

        return  new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

/*
    @PostMapping("/sendmail")
    public void sendMail(@RequestParam("email") String email){

        validateInput(to, subject, text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendermail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p>For security reason, you're required to use the following "
                        + "One Time Password to login:</p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        javaMailSender.send(message);

    }

    public void sendSimpleMessage(
            String to,
            String subject,
            String text
    ) throws ResourceNotFoundException {
        validateInput(to, subject, text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendermail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "<p>Hello, </p>"
                        + "<p>For security reason, you're required to use the following "
                        + "One Time Password to login:</p>"
                        + "<p><b>" + text + "</b></p>"
                        + "<br>"
        );
        javaMailSender.send(message);
    }

 */




}
