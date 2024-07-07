package site.wuct.scholars.service.impl;

import site.wuct.scholars.service.LocationsService;
import site.wuct.scholars.model.Location;
import java.util.List;
import site.wuct.scholars.repository.LocationsRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class LocationsServiceImpl implements LocationsService {
    @Autowired
    private LocationsRepository LocationsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> findAll() {
        return LocationsRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location findById(Long id) {
        return LocationsRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location save(Location location) {
        LocationsRepository.save(location);
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        LocationsRepository.deleteById(id);
    }

}
