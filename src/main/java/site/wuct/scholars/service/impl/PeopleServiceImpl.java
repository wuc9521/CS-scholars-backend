package site.wuct.scholars.service.impl;

import site.wuct.scholars.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.SneakyThrows;
import site.wuct.scholars.model.Person;
import site.wuct.scholars.service.PeopleService;

import java.util.Optional;
import site.wuct.scholars.model.Location;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Object[]> getPeopleByPublicationCount(String locName, String major) {
        return PeopleRepository.getPeopleByPublicationCount(locName, major);
    }

    public List<Object[]> getPeopleByHIndex(String locName, String major) {
        return PeopleRepository.getPeopleByHIndex(locName, major);
    }

    public List<Object[]> getPeopleWithPublicationsNoGrants(String locName, String major) {
        return PeopleRepository.getPeopleWithPublicationsNoGrants(locName, major);
    }

    public Map<String, Object> getPersonProfile(Long personId) {
        Map<String, Object> profile = PeopleRepository.getPersonProfile(personId);
        if (profile == null) {
            throw new RuntimeException("Person not found");
        }

        List<Map<String, Object>> publications = PeopleRepository.getPersonPublications(personId);
        List<Map<String, Object>> grants = PeopleRepository.getPersonGrants(personId);
        
        Map<String, Object> profileCopy = new HashMap<>();
        profileCopy.putAll(profile);
        profileCopy.put("publications", publications);
        profileCopy.put("grants", grants);

        return profileCopy;
    }
}
