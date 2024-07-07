package site.wuct.scholars.controller;

import site.wuct.scholars.model.Person;
import site.wuct.scholars.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@CrossOrigin
public class PeopleController {

    @Autowired
    private PeopleService PeopleService;

    /**
     * Get all People
     * @return People list
     */
    @GetMapping
    public List<Person> getAllPeople() {
        return PeopleService.findAll();
    }

    /**
     * Get People by id
     * 
     * @param id People id
     * @return People
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPeopleById(@PathVariable Long id) {
        Person People = PeopleService.findById(id);
        return People != null ? ResponseEntity.ok(People) : ResponseEntity.notFound().build();
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
    public ResponseEntity<Void> deletePeople(@PathVariable Long id) {
        PeopleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
