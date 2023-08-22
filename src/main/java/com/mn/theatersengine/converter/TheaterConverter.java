package com.mn.theatersengine.converter;

import com.mn.theatersengine.dto.TheaterDTO;
import com.mn.theatersengine.entity.TheaterEntity;
import org.springframework.stereotype.Component;

@Component
public class TheaterConverter {

    public TheaterEntity convertDTOtoEntity(TheaterDTO theaterDTO){

        TheaterEntity te = new TheaterEntity();

        te.setId(theaterDTO.getId());
        te.setTheaterName(theaterDTO.getTheaterName());

        return te;
    }

    public TheaterDTO convertEntityToDTO(TheaterEntity theaterEntity){

        TheaterDTO td = new TheaterDTO();

        td.setId(theaterEntity.getId());
        td.setTheaterName(theaterEntity.getTheaterName());
        td.setCityId(theaterEntity.getCity().getId());

        return td;
    }
}
