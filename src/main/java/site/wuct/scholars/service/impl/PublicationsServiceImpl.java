package site.wuct.scholars.service.impl;

import site.wuct.scholars.model.Publication;
import site.wuct.scholars.repository.PublicationsRepository;
import site.wuct.scholars.service.PublicationsService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PublicationsServiceImpl implements PublicationsService {
    @Autowired
    private PublicationsRepository PublicationsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Publication> findAll() {
        return PublicationsRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Publication findById(Long id) {
        return PublicationsRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Publication save(Publication publication) {
        PublicationsRepository.save(publication);
        return publication;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        PublicationsRepository.deleteById(id);
    }

}
