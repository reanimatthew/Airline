package com.example.airline.exceptions;

import javax.persistence.EntityNotFoundException;

public class EntityNotFoundByIdException extends EntityNotFoundException {
    public EntityNotFoundByIdException(Number id, String entityName) {
        super(entityName + " with id = " + id + " not found.");
    }
}
