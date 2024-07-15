package site.wuct.scholars.service.impl;

import site.wuct.scholars.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import site.wuct.scholars.model.Person;
import site.wuct.scholars.service.PeopleService;

import java.util.Optional;
import site.wuct.scholars.model.Location;

import java.util.Arrays;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository PeopleRepository;
    @Autowired
    private InRepository InRepository;
    @Autowired
    private LocationsRepository locationsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> findAll() {
        System.out.println("PeopleServiceImpl.findAll: " + PeopleRepository.findAll());
        return PeopleRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person findById(Integer id) {
        return PeopleRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person save(Person People) {
        return PeopleRepository.save(People);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        PeopleRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SneakyThrows
    public List<Person> findPeopleByLocationId(Integer id) {
        Optional<Location> locationOpt = locationsRepository.findById(id);
        if (locationOpt.isPresent()) {
            return InRepository.findPeopleByLocationId(id);
        } else {
            return Arrays.asList();
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean addPersonToLocation(Integer personId, Integer locationId) {
        return false;
    }
}
