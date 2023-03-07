package com.bee.carrental.api.repository;

import com.bee.carrental.api.entity.Car;
import com.bee.carrental.api.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Car> {

    @Query("SELECT m FROM Model m WHERE m.name = :modelName AND m.brand.name = :brandName")
    Optional<Model> findByNameAndBrandName(@Param("modelName") String modelName, @Param("brandName") String brandName);

}