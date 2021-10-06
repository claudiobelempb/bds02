package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

  @Autowired
  private CityService cityService;

  @GetMapping
  public ResponseEntity<List<CityDTO>> index() {
    List<CityDTO> cityDTOS = cityService.index();
    return ResponseEntity.ok().body(cityDTOS);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> detele(@PathVariable Long id){
    cityService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
