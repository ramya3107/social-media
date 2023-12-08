package com.example.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity(name= "user_details")
public class User {

  protected User() {
  }


  @Id
  @GeneratedValue
  private int id;

  @Size(min = 3,message = "Name should contain minimum of 3 characters ")
  //@JsonProperty("user-name")
  private String name;
  @Past(message = "Date of birth should be in past")
  private LocalDate dob;

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", dob=" + dob +
        '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public User(Integer id, String name, LocalDate dob){
    super();
    this.id=id;
    this.name=name;
    this.dob=dob;

  }
}
