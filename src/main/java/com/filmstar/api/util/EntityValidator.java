package com.filmstar.api.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class EntityValidator {
	
	public static Map<String, List<String>> execute(BindingResult bindingResult){
        List<String> errors = bindingResult.getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());

        return Map.of("errors", errors);
	}

}
