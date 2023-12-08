package com.example.socialmedia.controller;

import com.example.socialmedia.CurrencyServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyServiceController {

  @Autowired
  private CurrencyServiceConfiguration configuration;

  @RequestMapping("/currency-configuration")
  public CurrencyServiceConfiguration retrieveAllCourses(){
    return configuration;
  }

}
