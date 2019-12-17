package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.City;
import com.sandro.cursojava.domain.State;
import com.sandro.cursojava.repository.CityRepository;
import com.sandro.cursojava.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  public List<City> getCitiesByState(Integer stateId) {
    return cityRepository.findCities(stateId);
  }
}
