package com.mn.theatersengine.service.impl;

import com.mn.theatersengine.converter.ActorConverter;
import com.mn.theatersengine.dto.ActorDTO;
import com.mn.theatersengine.entity.ActorEntity;
import com.mn.theatersengine.entity.TheaterEntity;
import com.mn.theatersengine.exception.BusinessException;
import com.mn.theatersengine.exception.ErrorModel;
import com.mn.theatersengine.repository.ActorRepository;
import com.mn.theatersengine.repository.TheaterRepository;
import com.mn.theatersengine.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    private final ActorConverter actorConverter;

    private final TheaterRepository theaterRepository;


    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository, ActorConverter actorConverter, TheaterRepository theaterRepository) {
        this.actorRepository = actorRepository;
        this.actorConverter = actorConverter;
        this.theaterRepository = theaterRepository;
    }

    @Override
    public List<ActorDTO> getAllActors() {
        List<ActorEntity> actorEntityList = (List<ActorEntity>) actorRepository.findAll();

        List<ActorDTO> actorDTOS = new ArrayList<>();

        for(ActorEntity ae: actorEntityList){
            ActorDTO actorDTO = actorConverter.convertEntityToDTO(ae);
            actorDTOS.add(actorDTO);
        }

        return actorDTOS;
    }

    @Override
    public ActorDTO saveActor(ActorDTO actorDTO) {
        Optional<TheaterEntity> optionalTheaterEntity = theaterRepository.findById(actorDTO.getTheaterId());

        if(optionalTheaterEntity.isPresent()){
            ActorEntity ae = actorConverter.convertDTOtoEntity(actorDTO);
            ae.setTheater(optionalTheaterEntity.get());
            ae = actorRepository.save(ae);
            return actorConverter.convertEntityToDTO(ae);
        }else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("THEATER_ID_NOT_EXISTS");
            errorModel.setMessage("Theater does not exist, please check again theaters list!");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

    }

    @Override
    public void deleteActor(Long actorId) {

        actorRepository.deleteById(actorId);

    }

    @Override
    public void updateActor(ActorEntity actor) {
        Optional<ActorEntity> actorEntity = actorRepository.findById(actor.getId());

        ActorEntity actor1 = actorEntity.get();

        actor1.setTheater(actor.getTheater());
        actor1.setAge(actor.getAge());
        actor1.setName(actor.getName());
        actor1.setGender(actor.getGender());
        actor1.setSurname(actor.getSurname());

        actorRepository.save(actor1);
    }
}
