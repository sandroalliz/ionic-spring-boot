package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.City;
import com.sandro.cursojava.domain.State;
import com.sandro.cursojava.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateService {

  @Autowired
  private StateRepository stateRepository;

  public List<State> findAll() {
    return stateRepository.findAllByOrderByName();
  }

  public List<City> getCities(Integer id) {

    return new ArrayList<>();
  }
}
