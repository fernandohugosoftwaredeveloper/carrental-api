package com.bee.carrental.api.config;

import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.presenter.CarDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(Car.class, CarDTO.class)
                .addMapping(src -> src.getModel().getBrand().getName(), CarDTO::setBrand)
                .addMapping(src -> src.getModel().getName(), CarDTO::setModel);

        modelMapper.createTypeMap(CarDTO.class, Car.class)
                .addMapping(CarDTO::getBrand, (dest, value) -> dest.getModel().getBrand().setName((String) value))
                .addMapping(CarDTO::getModel, (dest, value) -> dest.getModel().setName((String) value));

        return modelMapper;
    }
}





