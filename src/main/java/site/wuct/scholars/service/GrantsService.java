package site.wuct.scholars.service;

import site.wuct.scholars.model.Grant;
import java.util.*;;

public interface GrantsService {
    List<Grant> findAll();
    Grant findById(Long id);
    Grant save(Grant grant);
    void deleteById(Long id);
}
