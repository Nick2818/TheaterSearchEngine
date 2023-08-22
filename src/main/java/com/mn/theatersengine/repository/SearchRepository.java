package com.mn.theatersengine.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository {

    @Query("SELECT t.theaterName, c.name, c.country, c.region, a.name, a.surname, a.age, a.gender " +
            "FROM TheaterEntity t INNER JOIN CityEntity c ON t.city.id = c.id INNER JOIN ActorEntity a ON t.id = a.theater.id WHERE c.name = :search OR c.region = :search OR c.country = :search " +
            "OR t.theaterName = :search OR a.name = :search OR a.surname = :search")
    List<Object[]> search(@Param("search") String search);
}
