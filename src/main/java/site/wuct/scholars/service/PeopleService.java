package site.wuct.scholars.service;

import site.wuct.scholars.model.People;

import java.util.List;

public interface PeopleService {
    List<People> findAll();
    People findById(Long id);
    People save(People People);
    void deleteById(Long id);
}

