package com.demoproject.bl.web.rest;

import com.demoproject.bl.repository.RecordRepository;
import com.demoproject.bl.service.RecordService;
import com.demoproject.bl.service.dto.RecordDTO;
import com.demoproject.bl.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.demoproject.bl.domain.Record}.
 */
@RestController
@RequestMapping("/api")
public class RecordResource {

    private final Logger log = LoggerFactory.getLogger(RecordResource.class);

    private static final String ENTITY_NAME = "record";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecordService recordService;

    private final RecordRepository recordRepository;

    public RecordResource(RecordService recordService, RecordRepository recordRepository) {
        this.recordService = recordService;
        this.recordRepository = recordRepository;
    }

    /**
     * {@code POST  /records} : Create a new record.
     *
     * @param recordDTO the recordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recordDTO, or with status {@code 400 (Bad Request)} if the record has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/records")
    public ResponseEntity<RecordDTO> createRecord(@RequestBody RecordDTO recordDTO) throws URISyntaxException {
        log.debug("REST request to save Record : {}", recordDTO);
        if (recordDTO.getId() != null) {
            throw new BadRequestAlertException("A new record cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecordDTO result = recordService.save(recordDTO);
        return ResponseEntity
            .created(new URI("/api/records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /records/:id} : Updates an existing record.
     *
     * @param id the id of the recordDTO to save.
     * @param recordDTO the recordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recordDTO,
     * or with status {@code 400 (Bad Request)} if the recordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/records/{id}")
    public ResponseEntity<RecordDTO> updateRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RecordDTO recordDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Record : {}, {}", id, recordDTO);
        if (recordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, recordDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RecordDTO result = recordService.update(recordDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /records/:id} : Partial updates given fields of an existing record, field will ignore if it is null
     *
     * @param id the id of the recordDTO to save.
     * @param recordDTO the recordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recordDTO,
     * or with status {@code 400 (Bad Request)} if the recordDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recordDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/records/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RecordDTO> partialUpdateRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RecordDTO recordDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Record partially : {}, {}", id, recordDTO);
        if (recordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, recordDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecordDTO> result = recordService.partialUpdate(recordDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recordDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /records} : get all the records.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of records in body.
     */
    @GetMapping("/records")
    public List<RecordDTO> getAllRecords() {
        log.debug("REST request to get all Records");
        return recordService.findAll();
    }

    /**
     * {@code GET  /records/:id} : get the "id" record.
     *
     * @param id the id of the recordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/records/{id}")
    public ResponseEntity<RecordDTO> getRecord(@PathVariable Long id) {
        log.debug("REST request to get Record : {}", id);
        Optional<RecordDTO> recordDTO = recordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recordDTO);
    }

    /**
     * {@code DELETE  /records/:id} : delete the "id" record.
     *
     * @param id the id of the recordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/records/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        log.debug("REST request to delete Record : {}", id);
        recordService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
