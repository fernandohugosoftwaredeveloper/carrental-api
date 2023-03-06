package com.bee.carrental.api.service;

import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.exception.CarAlreadyExistsException;
import com.bee.carrental.api.exception.CarNotFoundException;
import com.bee.carrental.api.exception.InvalidCarDataException;
import com.bee.carrental.api.presenter.CarDTO;
import com.bee.carrental.api.repository.CarRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    public CarDTO createCar(CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        if (Objects.nonNull(car.getId()) && carRepository.existsById(car.getId())) {
            throw new CarAlreadyExistsException("Car with ID " + car.getId() + " already exists");
        }
        if (StringUtils.isBlank(car.getModel().getBrand().getName()) || StringUtils.isBlank(car.getModel().getName())) {
            throw new InvalidCarDataException("Brand and model cannot be blank");
        }
        Car createdCar = carRepository.save(car);
        return modelMapper.map(createdCar, CarDTO.class);
    }

    public CarDTO updateCar(CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);

        if (Objects.nonNull(car.getId()) && carRepository.existsById(car.getId())) {
            throw new CarAlreadyExistsException("Car with ID " + car.getId() + " already exists");
        }
        Car createdCar  = carRepository.save(car);
        return modelMapper.map(createdCar, CarDTO.class);
    }

    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException("Car with ID " + id + " not found");
        }
        carRepository.deleteById(id);
    }

    public Page<CarDTO> findAvailableCars(String modelName, String brandName, Pageable pageable) {
        Page<Car> cars = carRepository.findAvailableCarsByModelOrBrandName(modelName, brandName, pageable);

        if (cars.isEmpty()) {
            throw new CarNotFoundException("No available cars found for the provided filters.");
        }
        return cars.map(car -> modelMapper.map(car, CarDTO.class));
    }
}



