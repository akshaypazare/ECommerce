package com.ecommerceproject.ProductName.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

<<<<<<< HEAD
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String ResourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fieldValue));
        ResourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return ResourceName;
=======
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public String getResourceName() {
        return resourceName;
    }

    public long getFieldValue() {
        return fieldValue;
>>>>>>> bb0188cfe4e60440cfbb89a2a05a85dc07a7c3f2
    }

    public String getFieldName() {
        return fieldName;
    }

<<<<<<< HEAD
    public long getFieldValue() {
        return fieldValue;
    }
=======
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue){
        super(String.format("%s not found with %s: '%s'" ,resourceName,fieldName,fieldValue));
        this.resourceName= resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


>>>>>>> bb0188cfe4e60440cfbb89a2a05a85dc07a7c3f2
}
