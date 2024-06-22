package site.wuct.scholars.controller;

import site.wuct.scholars.model.Scholar;
import site.wuct.scholars.service.ScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholar")
public class ScholarController {

    @Autowired
    private ScholarService ScholarService;

    @GetMapping
    public List<Scholar> getAllScholar() {
        return ScholarService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scholar> getScholarById(@PathVariable Long id) {
        Scholar Scholar = ScholarService.findById(id);
        return Scholar != null ? ResponseEntity.ok(Scholar) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Scholar createScholar(@RequestBody Scholar Scholar) {
        return ScholarService.save(Scholar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScholar(@PathVariable Long id) {
        ScholarService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
