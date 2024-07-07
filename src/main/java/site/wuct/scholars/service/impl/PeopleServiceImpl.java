package site.wuct.scholars.service.impl;

import site.wuct.scholars.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.wuct.scholars.model.Person;
import site.wuct.scholars.service.PeopleService;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository PeopleRepository;

    @Override
    public List<Person> findAll() {
        return PeopleRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return PeopleRepository.findById(id).orElse(null);
    }

    @Override
    public Person save(Person People) {
        return PeopleRepository.save(People);
    }

    @Override
    public void deleteById(Long id) {
        PeopleRepository.deleteById(id);
    }
}
