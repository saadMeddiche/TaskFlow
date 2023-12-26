package com.taskflow.taskmanagement.entities;

import com.taskflow.taskmanagement.costumValidations.EnumValue;
import com.taskflow.taskmanagement.enums.DemandStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DemandReplacement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task CurrentTask;

    @ManyToOne
    private Task newTask;

    private String description;

    private LocalDate dateDemand;

    @ManyToOne
    private User demandedBy;

    @EnumValue(enumClass = DemandStatus.class , message = "The Status Of Demand Can Only Be 'PENDING' or 'ACCEPTED' or 'REFUSED'")
    @Enumerated(value = EnumType.STRING)
    private DemandStatus status;
}
