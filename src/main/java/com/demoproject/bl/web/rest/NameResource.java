package com.demoproject.bl.web.rest;

import com.demoproject.bl.repository.NameRepository;
import com.demoproject.bl.service.NameService;
import com.demoproject.bl.service.dto.NameDTO;
import com.demoproject.bl.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.demoproject.bl.domain.Name}.
 */
@RestController
@RequestMapping("/api")
public class NameResource {

    private final Logger log = LoggerFactory.getLogger(NameResource.class);

    private static final String ENTITY_NAME = "name";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NameService nameService;

    private final NameRepository nameRepository;

    public NameResource(NameService nameService, NameRepository nameRepository) {
        this.nameService = nameService;
        this.nameRepository = nameRepository;
    }

    /**
     * {@code POST  /names} : Create a new name.
     *
     * @param nameDTO the nameDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nameDTO, or with status {@code 400 (Bad Request)} if the name has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/names")
    public ResponseEntity<NameDTO> createName(@RequestBody NameDTO nameDTO) throws URISyntaxException {
        log.debug("REST request to save Name : {}", nameDTO);
        if (nameDTO.getId() != null) {
            throw new BadRequestAlertException("A new name cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NameDTO result = nameService.save(nameDTO);
        return ResponseEntity
            .created(new URI("/api/names/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /names/:id} : Updates an existing name.
     *
     * @param id the id of the nameDTO to save.
     * @param nameDTO the nameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nameDTO,
     * or with status {@code 400 (Bad Request)} if the nameDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/names/{id}")
    public ResponseEntity<NameDTO> updateName(@PathVariable(value = "id", required = false) final Long id, @RequestBody NameDTO nameDTO)
        throws URISyntaxException {
        log.debug("REST request to update Name : {}, {}", id, nameDTO);
        if (nameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nameDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NameDTO result = nameService.update(nameDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nameDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /names/:id} : Partial updates given fields of an existing name, field will ignore if it is null
     *
     * @param id the id of the nameDTO to save.
     * @param nameDTO the nameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nameDTO,
     * or with status {@code 400 (Bad Request)} if the nameDTO is not valid,
     * or with status {@code 404 (Not Found)} if the nameDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the nameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/names/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NameDTO> partialUpdateName(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NameDTO nameDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Name partially : {}, {}", id, nameDTO);
        if (nameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nameDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NameDTO> result = nameService.partialUpdate(nameDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nameDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /names} : get all the names.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of names in body.
     */
    @GetMapping("/names")
    public ResponseEntity<List<NameDTO>> getAllNames(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Names");
        Page<NameDTO> page = nameService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /names/:id} : get the "id" name.
     *
     * @param id the id of the nameDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nameDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/names/{id}")
    public ResponseEntity<NameDTO> getName(@PathVariable Long id) {
        log.debug("REST request to get Name : {}", id);
        Optional<NameDTO> nameDTO = nameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nameDTO);
    }

    /**
     * {@code DELETE  /names/:id} : delete the "id" name.
     *
     * @param id the id of the nameDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/names/{id}")
    public ResponseEntity<Void> deleteName(@PathVariable Long id) {
        log.debug("REST request to delete Name : {}", id);
        nameService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
