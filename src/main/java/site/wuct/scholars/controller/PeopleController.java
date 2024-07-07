package site.wuct.scholars.controller;

import site.wuct.scholars.model.Person;
import site.wuct.scholars.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholar")
@CrossOrigin
public class PeopleController {

    @Autowired
    private PeopleService PeopleService;

    @GetMapping
    public List<Person> getAllPeople() {
        return PeopleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPeopleById(@PathVariable Long id) {
        Person People = PeopleService.findById(id);
        return People != null ? ResponseEntity.ok(People) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Person createPeople(@RequestBody Person People) {
        return PeopleService.save(People);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeople(@PathVariable Long id) {
        PeopleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
