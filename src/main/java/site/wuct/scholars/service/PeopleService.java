package site.wuct.scholars.service;

import site.wuct.scholars.model.Person;

import java.util.List;

public interface PeopleService {
    List<Person> findAll();
    Person findById(Long id);
    Person save(Person People);
    void deleteById(Long id);
}

