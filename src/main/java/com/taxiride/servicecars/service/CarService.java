package com.taxiride.servicecars.service;

import com.taxiride.servicecars.repo.model.Car;

import java.util.List;

public interface CarService {
  List<Car> fetchAllCars();
  Car fetchCarById(long id) throws IllegalArgumentException;
  long createCar(String brand, String model, String type);
  void updateCar(long id, String brand, String model, String type) throws IllegalArgumentException;
  void deleteCar(long id);
}
