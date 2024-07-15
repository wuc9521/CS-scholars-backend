package site.wuct.scholars.service.impl;

import site.wuct.scholars.service.LocationsService;
import site.wuct.scholars.model.Location;
import java.util.List;
import site.wuct.scholars.repository.LocationsRepository;
import site.wuct.scholars.repository.InRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
