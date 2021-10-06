package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DataBaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  @Transactional(readOnly = true)
  public List<CityDTO> index(){
    List<City> cities = cityRepository.findAll(Sort.by("name"));
    return cities.stream().map(CityDTO::new).collect(Collectors.toList());
  }

  public void delete(Long id) {
    try {
      cityRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e){
      throw new ResourceNotFoundException("Id not found " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DataBaseException("Integrity violation");
    }
  }

}
