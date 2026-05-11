package com.somity.MVCRestAPI.services;


import com.somity.MVCRestAPI.dto.EmpDTO;
import com.somity.MVCRestAPI.entity.EmpEntities;
import com.somity.MVCRestAPI.repository.EmpRepo;
import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpService {

    private final EmpRepo empRepo;
    private final ModelMapper modelMapper;

    public EmpService(EmpRepo empRepo, ModelMapper modelMapper) {
        this.empRepo = empRepo;
        this.modelMapper = modelMapper;
    }

    public Optional<EmpDTO> getEmpById(Long empId) {
      Optional<EmpEntities> empEntities= empRepo.findById(empId);
        return empEntities.map(empEntities1 -> modelMapper.map(empEntities1,EmpDTO.class));

    }

    public List<EmpDTO> getAllEmp() {
        List<EmpEntities> empEntities= empRepo.findAll();
        return  empEntities
                .stream()
                .map(employeeEntity->modelMapper.map(employeeEntity, EmpDTO.class))
                .collect(Collectors.toList());
    }

    public EmpDTO createEmp(EmpDTO inputEmp) {
        EmpEntities toSaveEmpEntity= modelMapper.map(inputEmp,EmpEntities.class);
        EmpEntities  savedEmpEntity= empRepo.save(toSaveEmpEntity);
        return modelMapper.map(savedEmpEntity, EmpDTO.class);
    }

    public EmpDTO updateById(Long empId, EmpDTO empDTO) {
        EmpEntities empEntities= modelMapper.map(empDTO, EmpEntities.class);
        empEntities.setId(empId);
        EmpEntities savedEmpEntities= empRepo.save(empEntities);
        return modelMapper.map(savedEmpEntities, EmpDTO.class);
    }

    public boolean deleteEmpById(Long empId) {
        boolean empExists= empRepo.existsById(empId);
        if(!empExists) return false;
        empRepo.deleteById(empId);
        return true;
    }

    public EmpDTO partialUpdateById(Map<String, Object> updates, Long employeeId) {
        boolean empExists= empRepo.existsById(employeeId);
        if(!empExists) return null;
        EmpEntities entities=empRepo.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated= ReflectionUtils.getRequiredField(EmpEntities.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,entities,value);
        });
        return modelMapper.map(empRepo.save(entities),EmpDTO.class);

    }
}
