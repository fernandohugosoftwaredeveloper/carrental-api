package com.bee.carrental.api.service;

import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.presenter.CarDTO;
import com.bee.carrental.api.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    public Car createCar(CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        return carRepository.save(car);
    }

}



