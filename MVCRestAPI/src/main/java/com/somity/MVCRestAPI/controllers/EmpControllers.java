package com.somity.MVCRestAPI.controllers;

import com.somity.MVCRestAPI.dto.EmpDTO;
import com.somity.MVCRestAPI.entity.EmpEntities;
import com.somity.MVCRestAPI.repository.EmpRepo;
import com.somity.MVCRestAPI.services.EmpService;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmpControllers {

    private final EmpService empService;

    public EmpControllers(EmpService empService) {
        this.empService = empService;
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<EmpDTO>  getEmpById(@PathVariable(name = "id") Long empId){
       Optional <EmpDTO> empDTO= empService.getEmpById(empId);
        return empDTO
                .map(empDTO1 ->ResponseEntity.ok(empDTO1))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<List<EmpDTO>> getAllEmp(@RequestParam(required = false) String name,
                          @RequestParam(required = false) Integer age){
       return ResponseEntity.ok(empService.getAllEmp());
    }

    @PostMapping
    public ResponseEntity<EmpDTO> createEmp(@RequestBody EmpDTO inputEmp){
        return new ResponseEntity<>(empService.createEmp(inputEmp), HttpStatus.CREATED);
    }

    @PutMapping(path="/{empId}")
    public ResponseEntity<EmpDTO> updateById(@RequestBody EmpDTO empDTO, @PathVariable Long empId){
        return ResponseEntity.ok(empService.updateById(empId,empDTO));
    }

    @DeleteMapping(path="/{empId}")
    public ResponseEntity<Boolean>  deleteEmpById( @PathVariable Long empId){
     return ResponseEntity.ok(empService.deleteEmpById(empId));
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmpDTO> partialUpdateById(@RequestBody Map<String, Object> updates,
                                    @PathVariable Long employeeId){
        EmpDTO empDTO=empService.partialUpdateById(updates,employeeId );
        if(empDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empDTO);
    }





}
