package com.somity.MVCRestAPI.controllers;

import com.somity.MVCRestAPI.dto.EmpDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
public class EmpControllers {

    @GetMapping(path = "/{id}")
    public EmpDTO getEmpById(@PathVariable(name = "id") Long empId){
        return new EmpDTO(empId,"amit","amit@gmail.com",25, LocalDate.of(2023,12,12),false);
    }
    @GetMapping
    public String getAllEmp(@RequestParam(required = false) String name,
                            @RequestParam(required = false) int age){
       return "hello" +" " +name+" "+age;
    }

    @PostMapping
    public EmpDTO createEmp(@RequestBody EmpDTO inputEmp){
        inputEmp.setId(10023L);
        return  inputEmp;
    }



}
