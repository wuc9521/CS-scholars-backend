package site.wuct.scholars.service;

import site.wuct.scholars.model.Scholar;

import java.util.List;

public interface ScholarService {
    List<Scholar> findAll();
    Scholar findById(Long id);
    Scholar save(Scholar Scholar);
    void deleteById(Long id);
}

