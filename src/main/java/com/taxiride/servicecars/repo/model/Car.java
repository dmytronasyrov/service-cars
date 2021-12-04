package com.taxiride.servicecars.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public final class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String brand;
  private String model;
  private String type;

  public Car() {
  }

  public Car(String brand, String model, String type) {
    this.brand = brand;
    this.model = model;
    this.type = type;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
