package site.wuct.scholars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import site.wuct.scholars.model.Grant;
import site.wuct.scholars.service.impl.GrantsServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/grants")
@CrossOrigin
public class GrantsController {

    @Autowired
    private GrantsServiceImpl grantsService;

    /**
     * Get all grants
     * @return grants list
     */
    @GetMapping
    public List<Grant> getAllGrants() {
        return grantsService.findAll();
    }

    /**
     * Get grant by id
     * 
     * @param id grant id
     * @return grant
     */
    @GetMapping("/{id}")
    public ResponseEntity<Grant> getGrantById(@PathVariable Long id) {
        Grant grant = grantsService.findById(id);
        return grant != null ? ResponseEntity.ok(grant) : ResponseEntity.notFound().build();
    }

    @GetMapping("/scholar/{pid}")
    public ResponseEntity<List<Grant>> getGrantsByPersonId(@PathVariable Integer pid) {
        List<Grant> grants = grantsService.findGrantsByPersonId(pid);
        return grants != null ? ResponseEntity.ok(grants) : ResponseEntity.notFound().build();
    }

    /**
     * Create grant
     * 
     * @param grant grant
     * @return grant
     */
    @PostMapping
    public Grant createGrant(@RequestBody Grant grant) {
        return grantsService.save(grant);
    }
}
