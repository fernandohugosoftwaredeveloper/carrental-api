package com.bee.carrental.api.service.test;

import com.bee.carrental.api.exception.CarNotFoundException;
import com.bee.carrental.api.presenter.CarDTO;
import com.bee.carrental.api.presenter.CarResponseDTO;
import com.bee.carrental.api.presenter.CarUpdateDTO;
import com.bee.carrental.api.repository.CarRepository;
import com.bee.carrental.api.repository.ModelRepository;
import com.bee.carrental.api.service.CarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class CarServiceImplTest {


    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CarDTO carDTO;

    @Mock
    private CarResponseDTO carResponseDTO;

    @Mock
    private CarUpdateDTO carUpdateDTO;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private void createCarDTO(){
        this.carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setBrand("Ford");
        carDTO.setModel("Fusion");
        carDTO.setAvailable(true);
    }

    private void createCarResponseDTO(){
        this.carResponseDTO = new CarResponseDTO();
        carResponseDTO.setId(1L);
        carResponseDTO.setBrand("Ford");
        carResponseDTO.setModel("Fusion");
        carResponseDTO.setAvailable(true);
    }

    private void createCarUpdateDTO(){
        this.carUpdateDTO = new CarUpdateDTO();
        carUpdateDTO.setId(1L);
        carUpdateDTO.setBrand("Chevrolet");
        carUpdateDTO.setModel("Camaro");
        carUpdateDTO.setAvailable(false);

    }

    @Test
    @DisplayName("Test deleteCar when car exists")
    public void testDeleteCar_CarExists() {
        Long carId = 1L;
        Mockito.when(carRepository.existsById(carId)).thenReturn(true);

        carService.deleteCar(carId);
        Mockito.verify(carRepository, Mockito.times(1)).deleteById(carId);
    }

    @Test
    @DisplayName("Test deleteCar when car does not exist")
    public void testDeleteCar_CarDoesNotExist() {
        Long carId = 1L;
        Mockito.when(carRepository.existsById(carId)).thenReturn(false);

        Assertions.assertThrows(CarNotFoundException.class, () -> carService.deleteCar(carId));
    }
}
