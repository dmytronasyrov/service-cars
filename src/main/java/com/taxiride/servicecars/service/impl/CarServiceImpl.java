package com.taxiride.servicecars.service.impl;

import com.taxiride.servicecars.repo.CarRepo;
import com.taxiride.servicecars.repo.model.Car;
import com.taxiride.servicecars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class CarServiceImpl implements CarService {

  private final CarRepo carRepo;

  public List<Car> fetchAllCars() {
    return carRepo.findAll();
  }

  public Car fetchCarById(long id) throws IllegalArgumentException {
    final Optional<Car> maybeCar = carRepo.findById(id);

    if (maybeCar.isPresent())
      return maybeCar.get();
    else
      throw new IllegalArgumentException("Invalid car ID");
  }

  public long createCar(String brand, String model, String type) {
    final Car car = new Car(brand, model, type);
    final Car savedCar = carRepo.save(car);

    return savedCar.getId();
  }

  public void updateCar(long id, String brand, String model, String type) throws IllegalArgumentException {
    final Optional<Car> maybeCar = carRepo.findById(id);

    if (maybeCar.isEmpty())
      throw new IllegalArgumentException("Invalid car ID");

    final Car car = maybeCar.get();
    if (brand != null && !brand.isBlank()) car.setBrand(brand);
    if (model != null && !model.isBlank()) car.setModel(model);
    if (type != null && !type.isBlank()) car.setType(type);
    carRepo.save(car);
  }

  public void deleteCar(long id) {
    carRepo.deleteById(id);
  }
}
