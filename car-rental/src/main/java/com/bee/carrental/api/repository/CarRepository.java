package com.bee.carrental.api.repository;

import java.util.List;

import com.bee.carrental.api.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    //List<Car> findByModelNameAndAvailableIsTrueOrModelBrandNameAndAvailableIsTrue(String modelName, String brandName);

    @Query("SELECT c FROM Car c JOIN c.model m JOIN m.brand b WHERE (m.name = :modelName OR b.name = :brandName) AND c.available = true")
    List<Car> findAvailableCarsByModelOrBrandName(@Param("modelName") String modelName, @Param("brandName") String brandName);





}
