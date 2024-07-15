package site.wuct.scholars.controller;

import site.wuct.scholars.model.Person;
import site.wuct.scholars.service.impl.PeopleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholar")
@CrossOrigin
public class PeopleController {

    @Autowired
    private PeopleServiceImpl peopleService;

    /**
     * Get all People
     * 
     * @return People list
     */
    public List<Person> getAllPeople() {
        System.out.println(peopleService.findAll());
        return peopleService.findAll();
    }

    /**
     * Get People by id
     * 
     * @param id People id
     * @return People
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPeopleById(@PathVariable Integer id) {
        Person People = peopleService.findById(id);
        return People != null ? ResponseEntity.ok(People) : ResponseEntity.notFound().build();
    }

    /**
     * Get People by location id
     * 
     * @param id location id
     * @return People list
     */
    @GetMapping("/loc/{locid}")
    public ResponseEntity<List<Person>> getPeopleByLocationId(@PathVariable Integer locid) {
        List<Person> people = peopleService.findPeopleByLocationId(locid);
        System.out.println(1919);
        return people != null ? ResponseEntity.ok(people) : ResponseEntity.notFound().build();
    }

   

    /**
     * Add a person to a location
     * 
     * @param personId   given person id
     * @param locationId given location id
     * @return true if success
     */
    @PostMapping("/add-to-location")
    public ResponseEntity<String> addPersonToLocation(@RequestParam Integer personId, @RequestParam Integer locationId) {
        boolean success = peopleService.addPersonToLocation(personId, locationId);
        if (success) {
            return ResponseEntity.ok("Person added to location successfully.");
        } else {
            return ResponseEntity.badRequest().body("Person or Location not found.");
        }
    }

    /**
     * Create People
     * 
     * @param People People
     * @return People
     */
    @Deprecated
    @PostMapping
    public Person createPeople(@RequestBody Person People) {
        return peopleService.save(People);
    }

    /**
     * Update People
     * 
     * @param id People id
     * @return People
     */
    @Deprecated
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeople(@PathVariable Integer id) {
        peopleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
