package site.wuct.scholars.service;

import site.wuct.scholars.model.Person;

import java.util.List;

public interface PeopleService {
    /**
     * find all People in the table
     * @return People list
     */
    List<Person> findAll();

    /**
     * find People by id
     * @param id People id
     * @return People
     */
    Person findById(Integer id);

    /**
     * save People
     * @param People People
     * @return People
     */
    Person save(Person People);

    /**
     * delete People by id
     * @param id People id
     */
    void deleteById(Integer id);
    /**
     * Get People by location id
     * @param id location id
     * @return People list
     */
    List<Person> findPeopleByLocationId(Integer locationId);

    /**
     * Add a person to a location
     * @param personId given person id
     * @param locationId given location id
     * @return true if success
     */
    boolean addPersonToLocation(Integer personId, Integer locationId);

    /**
     * Get people by publication count in a location and major
     * @param locName
     * @param major
     * @return
     */
    List<Object[]> getPeopleByPublicationCount(String locName, String major);
    
    /**
     * Get people by h-index in a location and major
     * @param locName
     * @param major
     * @return
     */
    List<Object[]> getPeopleByHIndex(String locName, String major);
    
    /**
     * Get people with publications but no grants in a location and major
     * @param locName
     * @param major
     * @return
     */
    List<Object[]> getPeopleWithPublicationsNoGrants(String locName, String major);
}

