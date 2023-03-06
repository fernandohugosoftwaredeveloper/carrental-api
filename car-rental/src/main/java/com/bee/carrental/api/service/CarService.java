package com.bee.carrental.api.service;

import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.presenter.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

    public CarDTO createCar(CarDTO carDTO);

    public CarDTO updateCar(CarDTO carDTO);

    public void deleteCar(Long id);

    public Page<CarDTO> findAvailableCars(String modelName, String brandName, Pageable pageable);
}
