package site.wuct.scholars.service.impl;

import site.wuct.scholars.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.wuct.scholars.model.Person;
import site.wuct.scholars.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository PersonRepository;

    @Override
    public List<Person> findAll() {
        return PersonRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return PersonRepository.findById(id).orElse(null);
    }

    @Override
    public Person save(Person Person) {
        return PersonRepository.save(Person);
    }

    @Override
    public void deleteById(Long id) {
        PersonRepository.deleteById(id);
    }
}
