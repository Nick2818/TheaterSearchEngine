package com.mn.theatersengine.service.impl;

import com.mn.theatersengine.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {


    private  final EntityManager entityManager;

    @Autowired
    public SearchServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object[]> searchEntitiesByKeyword(String keyword) {
        String jpql = "SELECT a, t, c FROM ActorEntity a " +
                "LEFT JOIN a.theater t " +
                "LEFT JOIN t.city c " +
                "WHERE a.name LIKE :keyword OR a.surname LIKE :keyword OR a.gender LIKE :keyword OR  " +
                "t.theaterName LIKE :keyword OR " +
                "c.name LIKE :keyword OR c.region LIKE :keyword OR c.country LIKE :keyword";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("keyword", "%" + keyword + "%");

        List<Object[]> result = query.getResultList();
        return result;
    }

    @Override
    public List<Object[]> allEntities() {
        String jpql = "SELECT a, t, c FROM ActorEntity a " +
                "LEFT JOIN a.theater t " +
                "LEFT JOIN t.city c ";

        Query query = entityManager.createQuery(jpql);

        List<Object[]> result = query.getResultList();
        return result;
    }
}
