package com.filmstar.api.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class EntityValidator {
	
	public static ResponseEntity<Map<String, List<String>>> validate(BindingResult bindingResult){
        List<String> errors = bindingResult.getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<Map<String, List<String>>>(Map.of("errors", errors), HttpStatus.BAD_REQUEST);
	}

}
