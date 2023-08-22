package com.mn.theatersengine.service;

import com.mn.theatersengine.dto.TheaterDTO;
import com.mn.theatersengine.entity.TheaterEntity;

import java.util.List;

public interface TheaterService {

    TheaterDTO saveTheater(TheaterDTO theaterDTO);

    List<TheaterDTO> getAllTheaters();

    void deleteTheater(Long theaterId);

    void updateTheater(TheaterEntity theater);

}
