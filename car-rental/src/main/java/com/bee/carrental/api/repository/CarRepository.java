package com.bee.carrental.api.repository;

import java.util.List;
import java.util.Optional;

import com.bee.carrental.api.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    //List<Car> findByModelNameAndAvailableIsTrueOrModelBrandNameAndAvailableIsTrue(String modelName, String brandName);

    @Query("SELECT c FROM Car c JOIN c.model m JOIN m.brand b WHERE (m.name = :modelName OR b.name = :brandName) AND c.available = true")
    Page<Car> findAvailableCarsByModelOrBrandName(@Param("modelName") String modelName, @Param("brandName") String brandName, Pageable pageable);

}
