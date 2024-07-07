package site.wuct.scholars.service;

import site.wuct.scholars.model.Location;
import java.util.List;

public interface LocationsService {
    /**
     * find all locations in the table
     * 
     * @return
     */
    List<Location> findAll();

    /**
     * find location by id
     * 
     * @param id location id
     * @return location
     */
    Location findById(Long id);

    /**
     * save location
     * 
     * @param location location
     * @return location
     */
    Location save(Location location);

    /**
     * delete location by id
     * 
     * @param id location id
     */
    void deleteById(Long id);
}