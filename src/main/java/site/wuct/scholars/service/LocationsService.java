package site.wuct.scholars.service;

import site.wuct.scholars.model.Location;
import java.util.List;

public interface LocationsService {
    List<Location> findAll();

    Location findById(Long id);
}