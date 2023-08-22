package com.mn.theatersengine.converter;

import com.mn.theatersengine.dto.ActorDTO;
import com.mn.theatersengine.entity.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorConverter {

    private final TheaterConverter theaterConverter;

    @Autowired
    public ActorConverter(TheaterConverter theaterConverter) {
        this.theaterConverter = theaterConverter;
    }


    public ActorEntity convertDTOtoEntity(ActorDTO actorDTO){

        ActorEntity ae = new ActorEntity();

        ae.setId(actorDTO.getId());
        ae.setName(actorDTO.getName());
        ae.setSurname(actorDTO.getSurname());
        ae.setGender(actorDTO.getGender());
        ae.setAge(actorDTO.getAge());

        return ae;
    }

    public ActorDTO convertEntityToDTO(ActorEntity actorEntity){

        ActorDTO ad = new ActorDTO();

        ad.setId(actorEntity.getId());
        ad.setAge(actorEntity.getAge());
        ad.setName(actorEntity.getName());
        ad.setSurname(actorEntity.getSurname());
        ad.setGender(actorEntity.getGender());
        ad.setTheaterId(actorEntity.getTheater().getId());

        return ad;
    }
}
