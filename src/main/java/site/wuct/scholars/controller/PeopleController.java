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
    private PeopleServiceImpl PeopleService;

    /**
     * Get all People
     * 
     * @return People list
     */
    @GetMapping
    public List<Person> getAllPeople() {
        System.out.println(PeopleService.findAll());
        return PeopleService.findAll();
    }

    /**
     * Get People by id
     * 
     * @param id People id
     * @return People
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPeopleById(@PathVariable Integer id) {
        Person People = PeopleService.findById(id);
        return People != null ? ResponseEntity.ok(People) : ResponseEntity.notFound().build();
    }

    /**
     * Get People by location id
     * 
     * @param id location id
     * @return People list
     */
    @GetMapping("/by-location")
    public ResponseEntity<List<Person>> getPeopleByLocationId(@RequestParam("locid") Integer locid) {
        List<Person> people = PeopleService.findPeopleByLocationId(locid);
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
        boolean success = PeopleService.addPersonToLocation(personId, locationId);
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
        return PeopleService.save(People);
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
        PeopleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
