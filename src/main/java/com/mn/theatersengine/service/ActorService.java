package com.mn.theatersengine.service;

import com.mn.theatersengine.dto.ActorDTO;
import com.mn.theatersengine.entity.ActorEntity;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ActorService {

    List<ActorDTO> getAllActors();

    ActorDTO saveActor(ActorDTO actorDTO);

    void deleteActor(Long actorId);

    void updateActor(ActorEntity actor);
}
