package com.bee.carrental.api.controller;

import com.bee.carrental.api.presenter.CarDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

public class CarController {

    public ResponseEntity<List<CarDTO>> listAvailableCars(@Parameter(in = ParameterIn.QUERY, description = "Filter by model", schema = @Schema()) @Valid @RequestParam(value = "model", required = false) String model, @Parameter(in = ParameterIn.QUERY, description = "Filter by brand", schema = @Schema()) @Valid @RequestParam(value = "brand", required = false) String brand, @Min(1) @Parameter(in = ParameterIn.QUERY, description = "The page number", schema = @Schema(allowableValues = {"1"}, minimum = "1"
    )) @Valid @RequestParam(value = "page", required = false) Integer page, @Min(1) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "The page size", schema = @Schema(allowableValues = {"1", "50"}, minimum = "1", maximum = "50"
    )) @Valid @RequestParam(value = "size", required = false) Integer size) {


        return new ResponseEntity<List<CarDTO>>(HttpStatus.NOT_IMPLEMENTED);
    }
}
