package com.bee.carrental.api.service.test;

import com.bee.carrental.api.entity.Brand;
import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.entity.Model;
import com.bee.carrental.api.exception.*;
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
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarServiceImplTest {


    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Brand brandEntity;

    @Mock
    private Model modelEntity;

    @Mock
    private Car carEntity;

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
        createCarDTO();
        createCarResponseDTO();
        createCarUpdateDTO();
        createBrandEntity();
        createModelEntity();
        createCarEntity();
    }

    private void createCarDTO() {
        this.carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setBrand("Ford");
        carDTO.setModel("Fusion");
        carDTO.setAvailable(true);
    }

    private void createCarResponseDTO() {
        this.carResponseDTO = new CarResponseDTO();
        carResponseDTO.setId(1L);
        carResponseDTO.setBrand("Ford");
        carResponseDTO.setModel("Fusion");
        carResponseDTO.setAvailable(true);
    }

    private void createCarUpdateDTO() {
        this.carUpdateDTO = new CarUpdateDTO();
        carUpdateDTO.setId(1L);
        carUpdateDTO.setBrand("Chevrolet");
        carUpdateDTO.setModel("Camaro");
        carUpdateDTO.setAvailable(false);

    }

    private void createBrandEntity() {
        this.brandEntity = new Brand();
        brandEntity.setName("Ford");
        brandEntity.setId(1L);
    }

    private void createModelEntity() {
        this.modelEntity = new Model();
        modelEntity.setId(1L);
        modelEntity.setBrand(this.brandEntity);

    }

    private void createCarEntity() {
        this.carEntity = new Car();
        carEntity.setId(1L);
        carEntity.setModel(this.modelEntity);
        carEntity.getModel().setName("Fusion");
        carEntity.setAvailable(true);
    }

    @Test
    @DisplayName("Test deleteCar when car exists")
    public void testDeleteCar_CarExists() {
        Long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(true);

        carService.deleteCar(carId);
        verify(carRepository, Mockito.times(1)).deleteById(carId);
    }

    @Test
    @DisplayName("Test deleteCar when car does not exist")
    public void testDeleteCar_CarDoesNotExist() {
        Long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(false);

        Assertions.assertThrows(CarNotFoundException.class, () -> carService.deleteCar(carId));
    }

    @Test
    public void testCreateCarSuccess() {

        // definir comportamento dos mocks
        when(modelMapper.map(carDTO, Car.class)).thenReturn(carEntity);
        when(modelMapper.map(carEntity, CarResponseDTO.class)).thenReturn(carResponseDTO);
        when(carRepository.existsById(carEntity.getId())).thenReturn(false);
        when(modelRepository.findByNameAndBrandName(Mockito.any(), Mockito.any())).thenReturn(Optional.of(modelEntity));
        when(carRepository.save(Mockito.any())).thenReturn(carEntity);

        // execução do teste
        CarResponseDTO createdCarResponseDTO = carService.createCar(carDTO);

        // verificações
        assertNotNull(createdCarResponseDTO);
        assertEquals(carEntity.getId(), createdCarResponseDTO.getId());
        assertEquals(carEntity.getModel().getBrand().getName(), createdCarResponseDTO.getBrand());
        assertEquals(carEntity.getModel().getName(), createdCarResponseDTO.getModel());
        assertEquals(carEntity.getAvailable(), createdCarResponseDTO.getAvailable());

        // verifica se os mocks foram utilizados corretamente
        verify(modelMapper).map(carDTO, Car.class);
        verify(carRepository).existsById(carEntity.getId());
        verify(modelRepository).findByNameAndBrandName(modelEntity.getName(), brandEntity.getName());
        verify(carRepository).save(carEntity);
    }


    @Test
    public void testCreateCarWithExistingId() {
        // Arrange
        carDTO.setId(1L);
        when(modelMapper.map(carDTO, Car.class)).thenReturn(carEntity);
        when(modelRepository.findByNameAndBrandName(anyString(), anyString())).thenReturn(Optional.empty());
        when(carRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(InvalidCarDataException.class, () -> carService.createCar(carDTO));
    }

    @Test
    public void testCreateCarAlreadyExistsException() {
        // Arrange
        carDTO.setId(1L);
        when(modelMapper.map(carDTO, Car.class)).thenReturn(carEntity);
        when(modelRepository.findByNameAndBrandName(anyString(), anyString())).thenReturn(Optional.empty());
        when(carRepository.existsById(anyLong())).thenReturn(true);

        assertThrows(CarAlreadyExistsException.class, () -> carService.createCar(carDTO));
    }


    @Test
    public void testCreateCarFailureWithSaveException() {
        // Arrange
        when(modelMapper.map(carDTO, Car.class)).thenReturn(carEntity);
        when(modelMapper.map(carEntity, CarResponseDTO.class)).thenReturn(carResponseDTO);
        when(carRepository.existsById(carEntity.getId())).thenReturn(false);
        when(modelRepository.findByNameAndBrandName(Mockito.any(), Mockito.any())).thenReturn(Optional.of(modelEntity));
        when(carRepository.save(Mockito.any(Car.class))).thenThrow(new DataAccessException("") {});

        assertThrows(CarCreationException.class, () -> carService.createCar(carDTO));
    }


}
