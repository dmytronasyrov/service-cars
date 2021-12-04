package com.taxiride.servicecars.repo;

import com.taxiride.servicecars.repo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {
}
