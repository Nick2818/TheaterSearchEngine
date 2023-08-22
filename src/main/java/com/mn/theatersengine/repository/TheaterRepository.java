package com.mn.theatersengine.repository;

import com.mn.theatersengine.entity.TheaterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface TheaterRepository extends CrudRepository<TheaterEntity, Long> {

    @Query("SELECT t FROM TheaterEntity t WHERE t.theaterName LIKE %:query")
    List<TheaterEntity> search(@RequestParam("query") String query);

}
