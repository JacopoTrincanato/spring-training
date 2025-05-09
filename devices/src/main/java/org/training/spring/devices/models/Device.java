package org.training.spring.devices.models;

import java.time.LocalDate;

import org.training.spring.devices.enums.StatusEnum;
import org.training.spring.devices.enums.TypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name field cannot be blank, empty or null")
    @Size(min = 1, max = 100, message = "name field cannot be shorter than 1 char and longer than 100 chars")
    private String name;

    @Enumerated(EnumType.STRING)
    private TypeEnum type = TypeEnum.OTHER;

    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.AVAILABLE;

    @PastOrPresent(message = "purchasing date field cannot be set in the future")
    private LocalDate purchasingDate;

    @NotBlank(message = "freshman field cannot be blank, empty or null")
    private String freshman;

}
