package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private CityRepository cityRepository;

  @Transactional
  public EventDTO update(Long id, EventDTO eventDTO) {
    try {
      Event event = eventRepository.getOne(id);
      event.setName(eventDTO.getName());
      event.setDate(eventDTO.getDate());
      event.setUrl(eventDTO.getUrl());
      event = eventRepository.save(event);
      return new EventDTO(event);
    }catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    }
  }


}
