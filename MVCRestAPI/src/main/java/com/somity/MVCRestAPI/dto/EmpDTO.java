package com.somity.MVCRestAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDTO {

   private Long id;
   private String name;
   private String email;
   private int age;
   private LocalDate dateOfJoining;
   private boolean isActive;
}
