package site.wuct.scholars.service;

import site.wuct.scholars.model.Grant;
import java.util.*;;

public interface GrantsService {
    /**
     * find all grants in the database
     * 
     * @return
     */
    List<Grant> findAll();

    /**
     * find a grant by id
     * 
     * @param id the id of the grant
     * @return the grant
     */
    Grant findById(Long id);

    /**
     * save a grant to the database
     * 
     * @param grant the grant to save
     * @return the saved grant
     */
    Grant save(Grant grant);

    /**
     * delete a grant by id
     * 
     * @param id the id of the grant
     */
    void deleteById(Long id);
}
