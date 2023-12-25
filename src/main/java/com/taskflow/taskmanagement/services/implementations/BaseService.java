package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.DoNotExistException;
import com.taskflow.taskmanagement.repositories.BaseRepository;

import java.util.Optional;


public class BaseService<O> {

    private final BaseRepository<O> repository;
    private final Class<?> entityClass;

    public BaseService(BaseRepository<O> baseRepository , Class<?> entityClass) {
        this.entityClass = entityClass;
        this.repository = baseRepository;
    }

    public O getEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DoNotExistException("No "+ entityClass.getSimpleName()+" has been found with id: " + id));
    }

    public void deleteEntityById(Long id){
        Optional.of(repository.findById(id)).filter(Optional::isPresent).orElseThrow(() -> new DoNotExistException("No "+ entityClass.getSimpleName()+" has been found with id: " + id));
        repository.deleteById(id);
    }
}
