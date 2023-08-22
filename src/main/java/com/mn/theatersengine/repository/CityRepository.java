package com.mn.theatersengine.repository;

import com.mn.theatersengine.entity.CityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {

    @Query("SELECT c FROM CityEntity c WHERE c.name LIKE %:query% OR c.country LIKE %:query% OR c.region LIKE %:query%")
    List<CityEntity> search(@RequestParam("query") String query);
}
