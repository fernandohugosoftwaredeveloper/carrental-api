package com.bee.carrental.api.presenter;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@ToString
public class CarUpdateDTO {
    @NotNull
    private Long id;

    @NotNull
    @Schema(required = true, description = "Brand of the car")
    private String brand;

    @NotNull
    @Schema(required = true, description = "Model of the car")
    private String model;

    @NotNull
    @Schema(required = true, description = "Availability of the car")
    private Boolean available;
}