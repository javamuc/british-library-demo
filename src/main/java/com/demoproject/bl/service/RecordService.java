package com.demoproject.bl.service;

import com.demoproject.bl.domain.Record;
import com.demoproject.bl.repository.RecordRepository;
import com.demoproject.bl.service.dto.RecordDTO;
import com.demoproject.bl.service.mapper.RecordMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Record}.
 */
@Service
@Transactional
public class RecordService {

    private final Logger log = LoggerFactory.getLogger(RecordService.class);

    private final RecordRepository recordRepository;

    private final RecordMapper recordMapper;

    public RecordService(RecordRepository recordRepository, RecordMapper recordMapper) {
        this.recordRepository = recordRepository;
        this.recordMapper = recordMapper;
    }

    /**
     * Save a record.
     *
     * @param recordDTO the entity to save.
     * @return the persisted entity.
     */
    public RecordDTO save(RecordDTO recordDTO) {
        log.debug("Request to save Record : {}", recordDTO);
        Record record = recordMapper.toEntity(recordDTO);
        record = recordRepository.save(record);
        return recordMapper.toDto(record);
    }

    /**
     * Update a record.
     *
     * @param recordDTO the entity to save.
     * @return the persisted entity.
     */
    public RecordDTO update(RecordDTO recordDTO) {
        log.debug("Request to update Record : {}", recordDTO);
        Record record = recordMapper.toEntity(recordDTO);
        record = recordRepository.save(record);
        return recordMapper.toDto(record);
    }

    /**
     * Partially update a record.
     *
     * @param recordDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RecordDTO> partialUpdate(RecordDTO recordDTO) {
        log.debug("Request to partially update Record : {}", recordDTO);

        return recordRepository
            .findById(recordDTO.getId())
            .map(existingRecord -> {
                recordMapper.partialUpdate(existingRecord, recordDTO);

                return existingRecord;
            })
            .map(recordRepository::save)
            .map(recordMapper::toDto);
    }

    /**
     * Get all the records.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecordDTO> findAll() {
        log.debug("Request to get all Records");
        return recordRepository.findAll().stream().map(recordMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one record by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecordDTO> findOne(Long id) {
        log.debug("Request to get Record : {}", id);
        return recordRepository.findById(id).map(recordMapper::toDto);
    }

    /**
     * Delete the record by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Record : {}", id);
        recordRepository.deleteById(id);
    }
}
