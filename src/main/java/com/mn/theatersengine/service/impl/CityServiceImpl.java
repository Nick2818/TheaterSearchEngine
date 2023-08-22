package com.mn.theatersengine.service.impl;

import com.mn.theatersengine.converter.CityConverter;
import com.mn.theatersengine.dto.CityDTO;
import com.mn.theatersengine.entity.CityEntity;
import com.mn.theatersengine.repository.CityRepository;
import com.mn.theatersengine.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityConverter cityConverter;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CityConverter cityConverter) {
        this.cityRepository = cityRepository;

        this.cityConverter = cityConverter;
    }

    @Override
    public List<CityDTO> getAllCities() {
        List<CityEntity> cityEntityList = (List<CityEntity>) cityRepository.findAll();

        List<CityDTO> cityDTOS = new ArrayList<>();

        for(CityEntity ce: cityEntityList){
            CityDTO cityDTO = cityConverter.convertEntityToDTO(ce);
            cityDTOS.add(cityDTO);
        }

        return cityDTOS;
    }

    @Override
    public CityDTO saveCity(CityDTO cityDTO) {
        CityEntity cityEntity = cityConverter.convertDTOtoEntity(cityDTO);

        cityRepository.save(cityEntity);

        return cityDTO;
    }

    @Override
    public boolean checkCity(String country, String region) {

        return (cityRepository.findAll().iterator().next().getRegion() == region && cityRepository.findAll().iterator().next().getCountry() == country);
    }

    @Override
    public void updateCity(CityEntity city) {

        Optional<CityEntity> cityEntity = cityRepository.findById(city.getId());

        CityEntity city1 = cityEntity.get();

        city1.setName(city.getName());
        city1.setCountry(city.getCountry());
        city1.setRegion(city.getRegion());

        cityRepository.save(city1);

    }
}
