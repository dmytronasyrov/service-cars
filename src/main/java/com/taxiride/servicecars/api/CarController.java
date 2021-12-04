package com.taxiride.servicecars.api;

import com.taxiride.servicecars.service.impl.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cars")
@RestController
public final class CarController {

  private final CarServiceImpl carServiceImpl;

  @GetMapping
  public ResponseEntity<List<com.taxiride.servicecars.repo.model.Car>> index() {
    final List<com.taxiride.servicecars.repo.model.Car> cars = carServiceImpl.fetchAllCars();

    return ResponseEntity.ok(cars);
  }

  @GetMapping("/{id}")
  public ResponseEntity<com.taxiride.servicecars.repo.model.Car> showById(@PathVariable long id) {
    try {
      final com.taxiride.servicecars.repo.model.Car car = carServiceImpl.fetchCarById(id);

      return ResponseEntity.ok(car);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody com.taxiride.servicecars.api.dto.Car car) {
    final String brand = car.brand();
    final String model = car.model();
    final String type = car.type();
    final long carId = carServiceImpl.createCar(brand, model, type);
    final String carUri = String.format("/cars/%d", carId);

    return ResponseEntity.created(URI.create(carUri)).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> change(@PathVariable long id, @RequestBody com.taxiride.servicecars.api.dto.Car car) {
    final String brand = car.brand();
    final String model = car.model();
    final String type = car.type();

    try {
      carServiceImpl.updateCar(id, brand, model, type);

      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable long id) {
    carServiceImpl.deleteCar(id);

    return ResponseEntity.noContent().build();
  }
}
