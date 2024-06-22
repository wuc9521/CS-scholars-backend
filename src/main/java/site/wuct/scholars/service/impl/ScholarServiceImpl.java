package site.wuct.scholars.service.impl;

import site.wuct.scholars.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.wuct.scholars.model.Scholar;
import site.wuct.scholars.service.ScholarService;

import java.util.List;

@Service
public class ScholarServiceImpl implements ScholarService {

    @Autowired
    private ScholarRepository ScholarRepository;

    @Override
    public List<Scholar> findAll() {
        return ScholarRepository.findAll();
    }

    @Override
    public Scholar findById(Long id) {
        return ScholarRepository.findById(id).orElse(null);
    }

    @Override
    public Scholar save(Scholar Scholar) {
        return ScholarRepository.save(Scholar);
    }

    @Override
    public void deleteById(Long id) {
        ScholarRepository.deleteById(id);
    }
}
