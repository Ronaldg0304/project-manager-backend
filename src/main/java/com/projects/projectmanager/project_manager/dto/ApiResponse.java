package com.projects.projectmanager.project_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {

    private String message;
    private T data;
    private HttpStatus httpStatus;

}
