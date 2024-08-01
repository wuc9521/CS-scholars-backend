package site.wuct.scholars.service.impl;

import site.wuct.scholars.service.LocationsService;
import site.wuct.scholars.model.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import site.wuct.scholars.repository.LocationsRepository;
import site.wuct.scholars.repository.InRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class LocationsServiceImpl implements LocationsService {
    @Autowired
    private LocationsRepository locationsRepository;
    @Autowired
    private InRepository inRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> findAll() {
        return locationsRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location findById(Integer id) {
        return locationsRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location save(Location location) {
        locationsRepository.save(location);
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        locationsRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> findLocationsByPersonId(Integer id) {
        return inRepository.findLocationsByPersonId(id);
    }

    public List<Object[]> getLocationsByMajorCount(String major) {
        return locationsRepository.getLocationsByMajorCount(major);
    }

    public List<Object[]> getLocationsByGrantCount(String major) {
        return locationsRepository.getLocationsByGrantCount(major);
    }

    public List<Object[]> getLocationsByMaxHIndex(String major) {
        return locationsRepository.getLocationsByMaxHIndex(major);
    }

    public Map<String, Object> getLocationProfile(Integer locid) {
        Map<String, Object> profile = locationsRepository.getLocationProfile(locid);
        
        List<Map<String, Object>> scholars = locationsRepository.getLocationScholarsSortedByPublications(locid);
        Map<String, Object> profileCopy = new HashMap<>(profile);
        profileCopy.put("scholars", scholars);
        
        return profileCopy;
    }
}
