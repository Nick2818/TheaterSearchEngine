package com.mn.theatersengine.converter;

import com.mn.theatersengine.dto.CityDTO;
import com.mn.theatersengine.entity.CityEntity;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {

    public CityEntity convertDTOtoEntity(CityDTO cityDTO){
        CityEntity ce = new CityEntity();

        ce.setId(cityDTO.getId());
        ce.setName(cityDTO.getName());
        ce.setCountry(cityDTO.getCountry());
        ce.setRegion(cityDTO.getRegion());

        return ce;
    }

    public CityDTO convertEntityToDTO(CityEntity cityEntity){
        CityDTO cd = new CityDTO();

        cd.setId(cityEntity.getId());
        cd.setName(cityEntity.getName());
        cd.setCountry(cityEntity.getCountry());
        cd.setRegion(cityEntity.getRegion());

        return cd;
    }
}
