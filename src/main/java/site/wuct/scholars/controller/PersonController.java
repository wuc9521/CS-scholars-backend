package site.wuct.scholars.controller;

import site.wuct.scholars.model.Person;
import site.wuct.scholars.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholar")
public class PersonController {

    @Autowired
    private PersonService PersonService;

    @GetMapping
    public List<Person> getAllPerson() {
        return PersonService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person Person = PersonService.findById(id);
        return Person != null ? ResponseEntity.ok(Person) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Person createPerson(@RequestBody Person Person) {
        return PersonService.save(Person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        PersonService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
