package com.mn.theatersengine.repository;

import com.mn.theatersengine.entity.ActorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends CrudRepository<ActorEntity, Long> {

    @Query("SELECT a FROM ActorEntity a WHERE a.name LIKE %:query% OR a.surname LIKE %:query% OR a.gender LIKE %:query% OR a.age LIKE %:query%")
    List<ActorEntity> search(@Param("query") String query);

}
