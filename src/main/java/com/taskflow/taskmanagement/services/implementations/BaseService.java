package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.DoNotExistException;
import com.taskflow.taskmanagement.repositories.BaseRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
class BaseService<T,ID> {

    private BaseRepository<T,ID> repository;
    private  Class<?> entityClass;

    public BaseService(BaseRepository<T,ID> baseRepository , Class<?> entityClass) {
        this.entityClass = entityClass;
        this.repository = baseRepository;
    }

    public void deleteEntityById(ID id){
        Optional.of(repository.findById(id)).filter(Optional::isPresent).orElseThrow(() -> new DoNotExistException("No "+ entityClass.getSimpleName()+" has been found with id: " + id));
        repository.deleteById(id);
    }
}
