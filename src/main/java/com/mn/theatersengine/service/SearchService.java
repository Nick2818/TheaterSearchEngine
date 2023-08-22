package com.mn.theatersengine.service;

import java.util.List;

public interface SearchService {

    List<Object[]> searchEntitiesByKeyword(String query);

    List<Object[]> allEntities();
}
