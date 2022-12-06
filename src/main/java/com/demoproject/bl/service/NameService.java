package com.demoproject.bl.service;

import com.demoproject.bl.domain.Name;
import com.demoproject.bl.repository.NameRepository;
import com.demoproject.bl.service.dto.NameDTO;
import com.demoproject.bl.service.mapper.NameMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Name}.
 */
@Service
@Transactional
public class NameService {

    private final Logger log = LoggerFactory.getLogger(NameService.class);

    private final NameRepository nameRepository;

    private final NameMapper nameMapper;

    public NameService(NameRepository nameRepository, NameMapper nameMapper) {
        this.nameRepository = nameRepository;
        this.nameMapper = nameMapper;
    }

    /**
     * Save a name.
     *
     * @param nameDTO the entity to save.
     * @return the persisted entity.
     */
    public NameDTO save(NameDTO nameDTO) {
        log.debug("Request to save Name : {}", nameDTO);
        Name name = nameMapper.toEntity(nameDTO);
        name = nameRepository.save(name);
        return nameMapper.toDto(name);
    }

    /**
     * Update a name.
     *
     * @param nameDTO the entity to save.
     * @return the persisted entity.
     */
    public NameDTO update(NameDTO nameDTO) {
        log.debug("Request to update Name : {}", nameDTO);
        Name name = nameMapper.toEntity(nameDTO);
        name = nameRepository.save(name);
        return nameMapper.toDto(name);
    }

    /**
     * Partially update a name.
     *
     * @param nameDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NameDTO> partialUpdate(NameDTO nameDTO) {
        log.debug("Request to partially update Name : {}", nameDTO);

        return nameRepository
            .findById(nameDTO.getId())
            .map(existingName -> {
                nameMapper.partialUpdate(existingName, nameDTO);

                return existingName;
            })
            .map(nameRepository::save)
            .map(nameMapper::toDto);
    }

    /**
     * Get all the names.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NameDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Names");
        return nameRepository.findAll(pageable).map(nameMapper::toDto);
    }

    /**
     * Get one name by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NameDTO> findOne(Long id) {
        log.debug("Request to get Name : {}", id);
        return nameRepository.findById(id).map(nameMapper::toDto);
    }

    /**
     * Delete the name by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Name : {}", id);
        nameRepository.deleteById(id);
    }
}
