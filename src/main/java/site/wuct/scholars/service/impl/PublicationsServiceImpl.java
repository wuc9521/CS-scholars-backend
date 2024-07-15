package site.wuct.scholars.service.impl;

import site.wuct.scholars.model.Publication;
import site.wuct.scholars.repository.PublicationsRepository;
import site.wuct.scholars.repository.PublishRepository;
import site.wuct.scholars.service.PublicationsService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;


@Service
public class PublicationsServiceImpl implements PublicationsService {
    @Autowired
    private PublicationsRepository publicationsRepository;
    @Autowired
    private PublishRepository publishRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Publication> findAll() {
        return publicationsRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Publication findById(Long id) {
        return publicationsRepository.findById(id).orElse(null);
    }


    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    public List<Publication> findPublicationsByPersonId(Integer id) {
        return publishRepository.findPublicationsByPersonId(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Publication save(Publication publication) {
        publicationsRepository.save(publication);
        return publication;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        publicationsRepository.deleteById(id);
    }

}
