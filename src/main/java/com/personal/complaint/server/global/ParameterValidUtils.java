package com.personal.complaint.server.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ParameterValidUtils {
    public static Map paramsValidResult(BindingResult bindingResult){
        Map<String, Object> errorMap = null;
        if(bindingResult.hasErrors()){
            errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return errorMap;
        }
        return null;
    }
}
