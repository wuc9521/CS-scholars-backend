package site.wuct.scholars.service;

import site.wuct.scholars.model.Publication;
import java.util.List;

public interface PublicationsService {
    List<Publication> findAll();
    Publication findById(Long id);
    Publication save(Publication publication);
    void deleteById(Long id);
}
