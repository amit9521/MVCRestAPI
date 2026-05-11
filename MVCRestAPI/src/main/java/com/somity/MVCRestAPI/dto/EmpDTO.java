package com.somity.MVCRestAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpDTO {


   private Long id;
   private String name;
   private String email;
   private Integer age;
   private LocalDate dateOfJoining;
   @JsonProperty("isActive")
   private boolean isActive;
}
