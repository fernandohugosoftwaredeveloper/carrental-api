package com.bee.carrental.api.presenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
public class CarDTO {
    @JsonIgnore
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

