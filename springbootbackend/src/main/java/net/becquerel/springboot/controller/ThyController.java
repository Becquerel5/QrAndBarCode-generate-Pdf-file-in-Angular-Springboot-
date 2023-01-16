package net.becquerel.springboot.controller;


import net.becquerel.springboot.Exportdoc.QrAndBarCode;
import net.becquerel.springboot.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Controller
public class ThyController {

    private EmployeeRepository employeeRepository;
    private QrAndBarCode qrandBarCode;

    public ThyController(EmployeeRepository employeeRepository, QrAndBarCode qrandBarCode) {
        this.employeeRepository = employeeRepository;
        this.qrandBarCode = qrandBarCode;
    }


    /*@GetMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.put("employees",employeeRepository.findAll());
        return "index";

    }*/

    @GetMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.put("employees",employeeRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/qrcode/{id}",method = RequestMethod.GET)
    public void qrcode(
        @PathVariable("id") String id,
        HttpServletResponse response) throws Exception
    {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(QrAndBarCode.getQRCodeImage(id,200,200));
        outputStream.flush();
        outputStream.close();
    }


    @RequestMapping(value = "/barcode/{id}",method = RequestMethod.GET)
    public void barcode(
            @PathVariable("id") String id,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(QrAndBarCode.getBarCodeImage(id,200,200));
        outputStream.flush();
        outputStream.close();
    }















































}
