package com.company.exam.error.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

     private String uuid;
     private Integer code;
     private String status;
     private String message;
     private LocalDateTime timestamp;

}
