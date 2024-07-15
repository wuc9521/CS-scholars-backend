package site.wuct.scholars.service.impl;

import site.wuct.scholars.service.GrantsService;
import site.wuct.scholars.model.Grant;
import site.wuct.scholars.repository.GrantsRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrantsServiceImpl implements GrantsService {

    @Autowired
    private GrantsRepository GrantsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Grant> findAll() {
        return GrantsRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grant findById(Long id) {
        return GrantsRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grant save(Grant grant) {
        return GrantsRepository.save(grant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        GrantsRepository.deleteById(id);
    }

}
