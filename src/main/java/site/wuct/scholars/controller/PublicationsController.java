package site.wuct.scholars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.wuct.scholars.model.Publication;
import site.wuct.scholars.service.impl.PublicationsServiceImpl;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/pub")
@CrossOrigin
public class PublicationsController {
    @Autowired
    private PublicationsServiceImpl publicationsService;

    /**
     * Get all Publications
     * 
     * @return Publications list
     */
    @GetMapping
    public List<Publication> getAllPublications() {
        return publicationsService.findAll();
    }

    /**
     * Get Publications by id
     * 
     * @param id Publications id
     * @return Publications
     */
    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationsById(@PathVariable Long id) {
        Publication publication = publicationsService.findById(id);
        return publication != null ? ResponseEntity.ok(publication) : ResponseEntity.notFound().build();
    }

    /**
     * Get all publications by person id
     * 
     * @param pid person id
     * @return publications list
     */
    @GetMapping("/scholar/{pid}")
    public ResponseEntity<List<Publication>> getPublicationsByPersonId(@PathVariable Integer pid) {
        List<Publication> publications = publicationsService.findPublicationsByPersonId(pid);
        return publications != null ? ResponseEntity.ok(publications) : ResponseEntity.notFound().build();
    }

    /**
     * Create Publications
     * 
     * @param publication Publications
     * @return Publications
     */
    @PostMapping
    public Publication createPublications(@RequestBody Publication publication) {
        return publicationsService.save(publication);
    }

    /**
     * Update Publications
     * 
     * @param id Publications id
     * @return Publications
     */
    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublications(@PathVariable Long id, @RequestBody Publication publication) {
        Publication existingPublication = publicationsService.findById(id);
        if (existingPublication != null) {
            publication.setPubid(id);
            publicationsService.save(publication);
            return ResponseEntity.ok(publication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete Publications
     * 
     * @param id Publications id
     * @return Publications
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublications(@PathVariable Long id) {
        publicationsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}