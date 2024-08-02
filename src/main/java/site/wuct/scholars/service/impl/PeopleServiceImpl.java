package site.wuct.scholars.service.impl;

import site.wuct.scholars.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Transactional
    public void addNewPerson(String name, String major, int hindex, String location) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withFunctionName("AddNewPerson");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_name", name);
        inParams.put("p_major", major);
        inParams.put("p_hindex", hindex);
        inParams.put("p_location", location);

        jdbcCall.execute(inParams);
    }

    @Transactional
    public void updateHIndex(String name, int newHindex) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withFunctionName("UpdateHIndex");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_name", name);
        inParams.put("p_new_hindex", newHindex);

        jdbcCall.execute(inParams);
    }

    @Transactional
    public void addPublicationAndAssociate(String pmid, String doi, String authorName) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withFunctionName("AddPublicationAndAssociate");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_pmid", pmid);
        inParams.put("p_doi", doi);
        inParams.put("p_author_name", authorName);

        jdbcCall.execute(inParams);
    }

    @Transactional
    public void assignGrantToPerson(java.sql.Date budgetStart, String personName) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withFunctionName("AssignGrantToPerson");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_budget_start", budgetStart);
        inParams.put("p_person_name", personName);

        jdbcCall.execute(inParams);
    }

    @Transactional
    public void changePersonLocation(String personName, String locName) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withFunctionName("ChangePersonLocation");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_person_name", personName);
        inParams.put("p_loc_name", locName);

        jdbcCall.execute(inParams);
    }
}
