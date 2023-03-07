package com.bee.carrental.api.service;

import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.presenter.CarDTO;
import com.bee.carrental.api.presenter.CarResponseDTO;
import com.bee.carrental.api.presenter.CarUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

    public CarResponseDTO createCar(CarDTO carDTO);

    public CarResponseDTO updateCar(CarUpdateDTO carDTO);

    public void deleteCar(Long id);

    public Page<CarResponseDTO> findAvailableCars(String modelName, String brandName, Pageable pageable);
}
