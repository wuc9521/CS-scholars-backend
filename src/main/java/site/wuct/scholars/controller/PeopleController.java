package site.wuct.scholars.controller;

import site.wuct.scholars.model.Person;
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

    // /**
    //  * Get all People
    //  * 
    //  * @return People list
    //  */
    // @GetMapping
    // public List<Person> getAllPeople() {
    //     System.out.println(1111);
    //     return peopleService.findAll();
    // }

    // /**
    //  * Get People by id
    //  * 
    //  * @param id People id
    //  * @return People
    //  */
    // @GetMapping("/{id}")
    // public ResponseEntity<Person> getPeopleById(@PathVariable Integer id) {
    //     Person People = peopleService.findById(id);
    //     return People != null ? ResponseEntity.ok(People) : ResponseEntity.notFound().build();
    // }

    // /**
    //  * Get People by location id
    //  * 
    //  * @param id location id
    //  * @return People list
    //  */
    // @GetMapping("/loc/{locid}")
    // public ResponseEntity<List<Person>> getPeopleByLocationId(@PathVariable Integer locid) {
    //     List<Person> people = peopleService.findPeopleByLocationId(locid);
    //     return people != null ? ResponseEntity.ok(people) : ResponseEntity.notFound().build();
    // }

    // /**
    //  * Add a person to a location
    //  * 
    //  * @param personId   given person id
    //  * @param locationId given location id
    //  * @return true if success
    //  */
    // @PostMapping("/add-to-location")
    // public ResponseEntity<String> addPersonToLocation(@RequestParam Integer personId,
    //         @RequestParam Integer locationId) {
    //     boolean success = peopleService.addPersonToLocation(personId, locationId);
    //     if (success) {
    //         return ResponseEntity.ok("Person added to location successfully.");
    //     } else {
    //         return ResponseEntity.badRequest().body("Person or Location not found.");
    //     }
    // }

    // /**
    //  * Create People
    //  * 
    //  * @param People People
    //  * @return People
    //  */
    // @Deprecated
    // @PostMapping
    // public Person createPeople(@RequestBody Person People) {
    //     return peopleService.save(People);
    // }

    // /**
    //  * Update People
    //  * 
    //  * @param id People id
    //  * @return People
    //  */
    // @Deprecated
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deletePeople(@PathVariable Integer id) {
    //     peopleService.deleteById(id);
    //     return ResponseEntity.ok().build();
    // }

    @GetMapping("/publication-count")
    public ResponseEntity<List<Object[]>> getPeopleByPublicationCount(
            @RequestParam String location,
            @RequestParam String major) {
        return ResponseEntity.ok(peopleService.getPeopleByPublicationCount(location, major));
    }

    @GetMapping("/hindex")
    public ResponseEntity<List<Object[]>> getPeopleByHIndex(
            @RequestParam String location,
            @RequestParam String major) {
        System.out.println("location: " + location + ", major: " + major);
        return ResponseEntity.ok(peopleService.getPeopleByHIndex(location, major));
    }

    @GetMapping("/publications-no-grants")
    public ResponseEntity<List<Object[]>> getPeopleWithPublicationsNoGrants(
            @RequestParam String location,
            @RequestParam String major) {
        return ResponseEntity.ok(peopleService.getPeopleWithPublicationsNoGrants(location, major));
    }

    @GetMapping("/{personId}/profile")
    public ResponseEntity<Map<String, Object>> getPersonProfile(@PathVariable Long personId) {
        System.out.println("personId: " + personId);
        Map<String, Object> profile = peopleService.getPersonProfile(personId);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewPerson(
            @RequestParam String name,
            @RequestParam String major,
            @RequestParam int hindex,
            @RequestParam String location) {
        peopleService.addNewPerson(name, major, hindex, location);
        return ResponseEntity.ok("Person added successfully");
    }

    @PutMapping("/update-hindex")
    public ResponseEntity<String> updateHIndex(@RequestParam String name, @RequestParam int newHindex) {
        peopleService.updateHIndex(name, newHindex);
        return ResponseEntity.ok("H-index updated successfully");
    }

    @PostMapping("/add-publication")
    public ResponseEntity<String> addPublicationAndAssociate(@RequestParam String pmid, @RequestParam String doi, @RequestParam String authorName) {
        peopleService.addPublicationAndAssociate(pmid, doi, authorName);
        return ResponseEntity.ok("Publication added and associated successfully");
    }

    @PostMapping("/assign-grant")
    public ResponseEntity<String> assignGrantToPerson(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date budgetStart, @RequestParam String personName) {
        peopleService.assignGrantToPerson(new java.sql.Date(budgetStart.getTime()), personName);
        return ResponseEntity.ok("Grant assigned successfully");
    }

    @PutMapping("/change-location")
    public ResponseEntity<String> changePersonLocation(@RequestParam String personName, @RequestParam String locName) {
        peopleService.changePersonLocation(personName, locName);
        return ResponseEntity.ok("Person's location changed successfully");
    }
}
