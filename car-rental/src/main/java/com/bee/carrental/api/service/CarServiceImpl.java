package com.bee.carrental.api.service;

import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.entity.Model;
import com.bee.carrental.api.exception.*;
import com.bee.carrental.api.presenter.CarDTO;
import com.bee.carrental.api.presenter.CarResponseDTO;
import com.bee.carrental.api.presenter.CarUpdateDTO;
import com.bee.carrental.api.repository.CarRepository;
import com.bee.carrental.api.repository.ModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelRepository modelRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    public CarResponseDTO createCar(CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        if (Objects.nonNull(car.getId()) && carRepository.existsById(car.getId())) {
            throw new CarAlreadyExistsException("Car with ID " + car.getId() + " already exists");
        }
        Optional<Model> model = modelRepository.findByNameAndBrandName(car.getModel().getName(), car.getModel().getBrand().getName());
        car.setModel(model.orElseThrow(() -> new InvalidCarDataException("Brand or Model are inconsistent.")));

        try {
            Car createdCar = carRepository.save(car);
            return modelMapper.map(createdCar, CarResponseDTO.class);
        } catch (DataAccessException ex) {
            throw new CarCreationException("Failed to create car", ex);
        }
    }

    public CarResponseDTO updateCar(CarUpdateDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);

        if (!carRepository.existsById(car.getId())) {
            throw new CarNotFoundException("Car with ID " + car.getId() + " not found");
        }

        Optional<Model> model = modelRepository.findByNameAndBrandName(car.getModel().getName(), car.getModel().getBrand().getName());
        car.setModel(model.orElseThrow(() -> new InvalidCarDataException("Brand or Model are inconsistent.")));

        try {
            Car updatedCar = carRepository.save(car);
            return modelMapper.map(updatedCar, CarResponseDTO.class);
        } catch (DataAccessException ex) {
            throw new CarCreationException("Failed to update car", ex);
        }
    }

    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException("Car with ID " + id + " not found");
        }
        carRepository.deleteById(id);
    }

    public Page<CarResponseDTO> findAvailableCars(String modelName, String brandName, Pageable pageable) {

        Page<Car> cars = carRepository.findAvailableCarsByModelOrBrandName(modelName, brandName, pageable);

        if (cars.isEmpty()) {
            throw new CarNotFoundException("No available cars found for the provided filters.");
        }
        return cars.map(car -> modelMapper.map(car, CarResponseDTO.class));
    }
}



