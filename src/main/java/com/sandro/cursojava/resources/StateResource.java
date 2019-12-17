package com.sandro.cursojava.resources;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.domain.City;
import com.sandro.cursojava.domain.State;
import com.sandro.cursojava.dto.CityDTO;
import com.sandro.cursojava.dto.StateDTO;
import com.sandro.cursojava.services.CityService;
import com.sandro.cursojava.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/states")
public class StateResource {

  @Autowired
  private StateService stateService;

  @Autowired
  private CityService cityService;

  @RequestMapping(method= RequestMethod.GET)
  public ResponseEntity<List<StateDTO>> get() {
    List<State> states = stateService.findAll();
    List<StateDTO> statesDTO = states.stream().map(x -> new StateDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok(statesDTO);
  }

  @RequestMapping(value = "/{stateId}/cities", method= RequestMethod.GET)
  public ResponseEntity<List<CityDTO>> get(@PathVariable Integer stateId) {
    List<City> cities = cityService.getCitiesByState(stateId);
    List<CityDTO> citiesDTO = cities.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok(citiesDTO);
  }
}
