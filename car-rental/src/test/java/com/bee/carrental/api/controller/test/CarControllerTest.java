package com.bee.carrental.api.controller.test;

import com.bee.carrental.api.controller.CarController;
import com.bee.carrental.api.entity.Brand;
import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.entity.Model;
import com.bee.carrental.api.presenter.CarDTO;
import com.bee.carrental.api.presenter.CarResponseDTO;
import com.bee.carrental.api.presenter.CarUpdateDTO;
import com.bee.carrental.api.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    private static final String BASE_URL = "/api/v1/cars";
    private static final Long VALID_CAR_ID = 1L;
    private static final Long INVALID_CAR_ID = 100L;
    private static final String VALID_MODEL_NAME = "Model 1";
    private static final String VALID_BRAND_NAME = "Brand 1";
    private static final String INVALID_MODEL_NAME = "Invalid Model";
    private static final String INVALID_BRAND_NAME = "Invalid Brand";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 10;
    private static final String SORT_FIELD = "id";
    private static final Sort.Direction SORT_DIRECTION = Sort.Direction.ASC;

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

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
    private CarController carController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
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
        carUpdateDTO.setBrand("Ford");
        carUpdateDTO.setModel("Fusion");
        carUpdateDTO.setAvailable(true);

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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Test create car - Success")
    void testCreateCarSuccess() throws Exception {
        when(carService.createCar(any(CarDTO.class))).thenReturn(carResponseDTO);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(carResponseDTO.getId().intValue())))
                .andExpect(jsonPath("$.brand", equalTo(carResponseDTO.getBrand())))
                .andExpect(jsonPath("$.model", equalTo(carResponseDTO.getModel())))
                .andExpect(jsonPath("$.available", equalTo(carResponseDTO.getAvailable())));

        verify(carService, times(1)).createCar(any(CarDTO.class));
    }

    @Test
    @DisplayName("Test create car - Invalid request body")
    void testCreateCarInvalidRequestBody() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString("invalid")))
                .andExpect(status().isBadRequest());

        verify(carService, never()).createCar(any(CarDTO.class));
    }

    @Test
    @DisplayName("Test update car - Success")
    void testUpdateSuccess() throws Exception {
        when(carService.updateCar(any(CarUpdateDTO.class))).thenReturn(carResponseDTO);

        mockMvc.perform(put(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carUpdateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(carResponseDTO.getId().intValue())))
                .andExpect(jsonPath("$.brand", equalTo(carResponseDTO.getBrand())))
                .andExpect(jsonPath("$.model", equalTo(carResponseDTO.getModel())))
                .andExpect(jsonPath("$.available", equalTo(carResponseDTO.getAvailable())));

        verify(carService, times(1)).updateCar(any(CarUpdateDTO.class));
    }

    @Test
    @DisplayName("Test update car - Success")
    void testUpdateCarInvalidRequestBody() throws Exception {
        mockMvc.perform(put(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString("invalid")))
                .andExpect(status().isBadRequest());

        verify(carService, never()).updateCar(any(CarUpdateDTO.class));
    }

    @Test
    @DisplayName("Test delete car by Id- Invalid request body")
    public void testDeleteById() throws Exception {

        mockMvc.perform(delete(BASE_URL + "/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(carService, times(1)).deleteCar(new Long(3));
    }

}

