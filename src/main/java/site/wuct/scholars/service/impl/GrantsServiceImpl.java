package site.wuct.scholars.service.impl;

import site.wuct.scholars.service.GrantsService;
import site.wuct.scholars.model.Grant;
import site.wuct.scholars.repository.GrantsRepository;
import site.wuct.scholars.repository.HasRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrantsServiceImpl implements GrantsService {

    @Autowired
    private GrantsRepository grantsRepository;
    @Autowired
    private HasRepository hasRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Grant> findAll() {
        return grantsRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grant findById(Long id) {
        return grantsRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Grant> findGrantsByPersonId(Integer pid) {
        return hasRepository.findGrantsByPersonId(pid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grant save(Grant grant) {
        return grantsRepository.save(grant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        grantsRepository.deleteById(id);
    }

}
