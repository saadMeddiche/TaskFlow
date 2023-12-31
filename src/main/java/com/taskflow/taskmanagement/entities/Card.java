package com.taskflow.taskmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taskflow.taskmanagement.enums.CardType;
import com.taskflow.taskmanagement.enums.RangeType;
import com.taskflow.taskmanagement.costumValidations.EnumValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name of card cannot be null")
    @PositiveOrZero(message = "Number of utilisation cannot be negative")
    private Integer numberOfUtilisation;

    @NotNull(message = "The User of card cannot be null")
    @ManyToOne
    @JsonIgnoreProperties("cards")
    private User user;

    @NotNull(message = "Type of card cannot be null")
    @EnumValue(enumClass = CardType.class , message = "The Type Of Card Can Only Be 'Deletion' or 'Modification'")
    @Enumerated(value = EnumType.STRING)
    private CardType type;

    @NotNull(message = "Range Type of card cannot be null")
    @EnumValue(enumClass = RangeType.class , message = "The Range Type Of Card Can Only Be 'PerDay' or 'PerWeek' or 'PerMonth' or 'PerYear'")
    @Enumerated(value = EnumType.STRING)
    private RangeType rangeType;

}