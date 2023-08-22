package com.mn.theatersengine.service;

import com.mn.theatersengine.dto.CityDTO;
import com.mn.theatersengine.entity.CityEntity;

import java.util.List;

public interface CityService {

    List<CityDTO> getAllCities();

    CityDTO saveCity(CityDTO cityDTO);

    boolean checkCity(String country, String Region);

    void updateCity(CityEntity city);
}
