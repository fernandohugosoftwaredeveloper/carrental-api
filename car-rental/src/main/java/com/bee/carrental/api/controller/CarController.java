package com.bee.carrental.api.controller;

import com.bee.carrental.api.presenter.CarDTO;
import com.bee.carrental.api.service.CarService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) {
        CarDTO createdCarDTO = carService.createCar(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @Valid @RequestBody CarDTO carDTO) {
        carDTO.setId(id);
        CarDTO updatedCarDTO = carService.updateCar(carDTO);
        return ResponseEntity.ok(updatedCarDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<Page<CarDTO>> findAvailableCars(
            @RequestParam(required = false) String modelName,
            @RequestParam(required = false) String brandName,
            @PageableDefault(sort = "id") Pageable pageable) {
        Page<CarDTO> availableCars = carService.findAvailableCars(modelName, brandName, pageable);
        return ResponseEntity.ok(availableCars);
    }

}

