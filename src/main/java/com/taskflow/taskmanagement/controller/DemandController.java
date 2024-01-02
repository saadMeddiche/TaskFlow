package com.taskflow.taskmanagement.controller;

import com.taskflow.taskmanagement.converters.DemandConverter;
import com.taskflow.taskmanagement.dtos.demand.request.DemandRequest;
import com.taskflow.taskmanagement.entities.DemandReplacement;
import com.taskflow.taskmanagement.services.DemandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demands")
@RequiredArgsConstructor
public class DemandController {

    private final DemandService demandService;

    private final DemandConverter demandConverter;

    @PostMapping
    public ResponseEntity<?> createDemand(@Valid @RequestBody DemandRequest demandRequest) {
        DemandReplacement demand = demandConverter.convertToEntity(demandRequest);
        DemandReplacement createdDemand = demandService.createDemand(demand);
        return new ResponseEntity<>(createdDemand , HttpStatus.OK);
    }



}
