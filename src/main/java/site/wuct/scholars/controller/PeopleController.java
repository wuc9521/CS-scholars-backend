package site.wuct.scholars.controller;

import site.wuct.scholars.service.impl.PeopleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scholar")
@CrossOrigin
public class PeopleController {

    @Autowired
    private PeopleServiceImpl peopleService;

    /**
     * Get people by publication count
     * 
     * @param location the location
     * @param major    the major
     * @return a list of people with their publication count
     */
    @GetMapping("/publication-count")
    public ResponseEntity<List<Object[]>> getPeopleByPublicationCount(
            @RequestParam String location,
            @RequestParam String major) {
        return ResponseEntity.ok(peopleService.getPeopleByPublicationCount(location, major));
    }

    /**
     * Get people by h-index
     * 
     * @param location the location
     * @param major    the major
     * @return a list of people with their h-index
     */
    @GetMapping("/hindex")
    public ResponseEntity<List<Object[]>> getPeopleByHIndex(
            @RequestParam String location,
            @RequestParam String major) {
        return ResponseEntity.ok(peopleService.getPeopleByHIndex(location, major));
    }

    /**
     * Get people with publications but no grants
     * 
     * @param location the location
     * @param major    the major
     * @return a list of people with publications but no grants
     */
    @GetMapping("/publications-no-grants")
    public ResponseEntity<List<Object[]>> getPeopleWithPublicationsNoGrants(
            @RequestParam String location,
            @RequestParam String major) {
        return ResponseEntity.ok(peopleService.getPeopleWithPublicationsNoGrants(location, major));
    }

    /**
     * Get person profile
     * 
     * @param personId the person ID
     * @return the person profile
     */
    @GetMapping("/{personId}/profile")
    public ResponseEntity<Map<String, Object>> getPersonProfile(@PathVariable Long personId) {
        Map<String, Object> profile = peopleService.getPersonProfile(personId);
        return ResponseEntity.ok(profile);
    }

    /**
     * Get person publications
     * 
     * @param name     the person name
     * @param major    the person major
     * @param hindex   the person h-index
     * @param location the person location
     * @return a success message
     */
    @PostMapping("/add")
    public ResponseEntity<String> addNewPerson(
            @RequestParam String name,
            @RequestParam String major,
            @RequestParam int hindex,
            @RequestParam String location) {
        peopleService.addNewPerson(name, major, hindex, location);
        return ResponseEntity.ok("Person added successfully");
    }

    /**
     * Update h-index
     * 
     * @param name      the person name
     * @param newHindex the new h-index
     * @return a success message
     */
    @PutMapping("/update-hindex")
    public ResponseEntity<String> updateHIndex(@RequestParam String name, @RequestParam int newHindex) {
        peopleService.updateHIndex(name, newHindex);
        return ResponseEntity.ok("H-index updated successfully");
    }

    /**
     * Add publication and associate
     * @param pmid the pmid
     * @param doi the doi
     * @param authorName the author name
     * @return a success message
     */
    @PostMapping("/add-publication")
    public ResponseEntity<String> addPublicationAndAssociate(@RequestParam String pmid, @RequestParam String doi,
            @RequestParam String authorName) {
        peopleService.addPublicationAndAssociate(pmid, doi, authorName);
        return ResponseEntity.ok("Publication added and associated successfully");
    }

    /**
     * Assign grant to person
     * @param budgetStart the budget start date
     * @param personName the person name
     * @return a success message
     */
    @PostMapping("/assign-grant")
    public ResponseEntity<String> assignGrantToPerson(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date budgetStart,
            @RequestParam String personName) {
        peopleService.assignGrantToPerson(new java.sql.Date(budgetStart.getTime()), personName);
        return ResponseEntity.ok("Grant assigned successfully");
    }

    /**
     * Change person location
     * @param personName the person name
     * @param locName the location name
     * @return a success message
     */
    @PutMapping("/change-location")
    public ResponseEntity<String> changePersonLocation(@RequestParam String personName, @RequestParam String locName) {
        peopleService.changePersonLocation(personName, locName);
        return ResponseEntity.ok("Person's location changed successfully");
    }
}
