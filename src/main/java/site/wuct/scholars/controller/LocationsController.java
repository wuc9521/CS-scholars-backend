package site.wuct.scholars.controller;

import org.springframework.beans.factory.annotation.Autowired;

import site.wuct.scholars.model.Location;
import site.wuct.scholars.service.impl.LocationsServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/locations")
@CrossOrigin
public class LocationsController {
    @Autowired
    private LocationsServiceImpl locationsService;

    /**
     * Get all locations
     * 
     * @return locations list
     */
    @GetMapping
    public List<Location> getAllLocations() {
        return locationsService.findAll();
    }

    /**
     * Get location by id
     * 
     * @param id location id
     * @return location
     */
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Integer id) {
        Location location = locationsService.findById(id);
        return location != null ? ResponseEntity.ok(location) : ResponseEntity.notFound().build();
    }

    /**
     * Add a location
     * 
     * @param location given location
     * @return true if success
     */
    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationsService.save(location));
    }

    /**
     * Delete a location
     * 
     * @param id location id
     * @return true if success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) {
        locationsService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Get locations by person id
     * 
     * @param id person id
     * @return locations list
     */
    @GetMapping("/person/{personId}")
    public ResponseEntity<List<Location>> getLocationsByPersonId(@PathVariable Integer personId) {
        List<Location> locations = locationsService.findLocationsByPersonId(personId);
        return locations != null ? ResponseEntity.ok(locations) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-people-count")
    public ResponseEntity<List<Object[]>> getLocationsByMajorCount(@RequestParam String major) {
        return ResponseEntity.ok(locationsService.getLocationsByMajorCount(major));
    }

    @GetMapping("/by-grant-count")
    public ResponseEntity<List<Object[]>> getLocationsByGrantCount(@RequestParam String major) {
        return ResponseEntity.ok(locationsService.getLocationsByGrantCount(major));
    }

    @GetMapping("/by-max-hindex")
    public ResponseEntity<List<Object[]>> getLocationsByMaxHIndex(@RequestParam String major) {
        return ResponseEntity.ok(locationsService.getLocationsByMaxHIndex(major));
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<Map<String, Object>> getLocationProfile(@PathVariable Integer id) {
        Map<String, Object> profile = locationsService.getLocationProfile(id);
        return profile != null ? ResponseEntity.ok(profile) : ResponseEntity.notFound().build();
    }
}
