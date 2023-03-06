package com.bee.carrental.api.controller;

import com.bee.carrental.api.presenter.CarDTO;
import com.bee.carrental.api.presenter.CarResponseDTO;
import com.bee.carrental.api.presenter.CarUpdateDTO;
import com.bee.carrental.api.service.CarService;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cars")
@Tag(name = "Car Rental")
@CrossOrigin(origins = "*")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarResponseDTO> createCar(@RequestBody CarDTO carDTO) {
        System.out.println(carDTO.toString());
        CarResponseDTO createdCarDTO = carService.createCar(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarDTO);
    }

    @PutMapping
    public ResponseEntity<CarResponseDTO> updateCar(@Valid @RequestBody CarUpdateDTO carDTO) {
        CarResponseDTO updatedCarDTO = carService.updateCar(carDTO);
        return ResponseEntity.ok(updatedCarDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<Page<CarResponseDTO>> findAvailableCars(
            @RequestParam(required = false) String modelName,
            @RequestParam(required = false) String brandName,
            @PageableDefault(sort = "id") Pageable pageable) {
        Page<CarResponseDTO> availableCars = carService.findAvailableCars(modelName, brandName, pageable);
        return ResponseEntity.ok(availableCars);
    }

}

