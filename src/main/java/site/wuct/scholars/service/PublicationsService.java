package site.wuct.scholars.service;

import site.wuct.scholars.model.Publication;
import java.util.List;

public interface PublicationsService {
    /**
     * Find all publications
     * 
     * @return publications list
     */
    List<Publication> findAll();

    /**
     * Find publication by id
     * 
     * @param id publication id
     * @return publication
     */
    Publication findById(Long id);

    /**
     * Save publication
     * 
     * @param publication publication
     * @return publication
     */
    Publication save(Publication publication);

    /**
     * Delete publication by id
     * 
     * @param id publication id
     */
    void deleteById(Long id);

    /**
     * Find publications by person id
     * 
     * @param id person id
     * @return publications list
     */
    List<Publication> findPublicationsByPersonId(Integer id);
}
