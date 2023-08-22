package com.mn.theatersengine.service.impl;

import com.mn.theatersengine.converter.TheaterConverter;
import com.mn.theatersengine.dto.TheaterDTO;
import com.mn.theatersengine.entity.CityEntity;
import com.mn.theatersengine.entity.TheaterEntity;
import com.mn.theatersengine.exception.BusinessException;
import com.mn.theatersengine.exception.ErrorModel;
import com.mn.theatersengine.repository.CityRepository;
import com.mn.theatersengine.repository.TheaterRepository;
import com.mn.theatersengine.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;
    private final TheaterConverter theaterConverter;

    private final CityRepository cityRepository;

    @Autowired
    public TheaterServiceImpl(TheaterRepository theaterRepository, TheaterConverter theaterConverter, CityRepository cityRepository) {
        this.theaterRepository = theaterRepository;
        this.theaterConverter = theaterConverter;
        this.cityRepository = cityRepository;
    }


    @Override
    public TheaterDTO saveTheater(TheaterDTO theaterDTO) {
        Optional<CityEntity> optionalCityEntity = cityRepository.findById(theaterDTO.getCityId());

        if(optionalCityEntity.isPresent()){
            TheaterEntity theaterEntity = theaterConverter.convertDTOtoEntity(theaterDTO);

            theaterEntity.setCity(optionalCityEntity.get());

            theaterEntity = theaterRepository.save(theaterEntity);

            return theaterConverter.convertEntityToDTO(theaterEntity);
        }else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("CITY_ID_NOT_EXISTS");
            errorModel.setMessage("City does not exist, please check again cities list!");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
    }

    @Override
    public List<TheaterDTO> getAllTheaters() {
        List<TheaterEntity> theaterEntityList = (List<TheaterEntity>) theaterRepository.findAll();

        List<TheaterDTO> theaterDTOS = new ArrayList<>();

        for(TheaterEntity te: theaterEntityList){
            TheaterDTO  theaterDTO = theaterConverter.convertEntityToDTO(te);

            theaterDTOS.add(theaterDTO);
        }

        return theaterDTOS;
    }

    @Override
    public void deleteTheater(Long theaterId) {

        theaterRepository.deleteById(theaterId);

    }

    @Override
    public void updateTheater(TheaterEntity theater) {
        Optional<TheaterEntity> theaterEntity = theaterRepository.findById(theater.getId());

        TheaterEntity theater1 = theaterEntity.get();

        theater1.setTheaterName(theater.getTheaterName());
        theater1.setCity(theater.getCity());

        theaterRepository.save(theater1);
    }
}
