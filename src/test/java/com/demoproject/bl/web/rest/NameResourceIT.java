package com.demoproject.bl.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.demoproject.bl.IntegrationTest;
import com.demoproject.bl.domain.Name;
import com.demoproject.bl.repository.NameRepository;
import com.demoproject.bl.service.dto.NameDTO;
import com.demoproject.bl.service.mapper.NameMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NameResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NameResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DATES_ASSOCIATED_WITH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DATES_ASSOCIATED_WITH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_NAMES = "BBBBBBBBBB";

    private static final String DEFAULT_BL_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_BL_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_RESOURCE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_RESOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BNB_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BNB_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ARCHIVAL_RESOURCE_KEY = "AAAAAAAAAA";
    private static final String UPDATED_ARCHIVAL_RESOURCE_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_ISBN = "AAAAAAAAAA";
    private static final String UPDATED_ISBN = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_VARIANT_TITLES = "AAAAAAAAAA";
    private static final String UPDATED_VARIANT_TITLES = "BBBBBBBBBB";

    private static final String DEFAULT_SERIES_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_SERIES_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_WITHIN_SERIES = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_WITHIN_SERIES = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_OF_PUBLICATION = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_PUBLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_PLACE_OF_PUBLICATION = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_PUBLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLISHER = "AAAAAAAAAA";
    private static final String UPDATED_PUBLISHER = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_PUBLICATION = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_PUBLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_EDITION = "AAAAAAAAAA";
    private static final String UPDATED_EDITION = "BBBBBBBBBB";

    private static final String DEFAULT_PHYSICAL_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PHYSICAL_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DEWEY_CLASSIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_DEWEY_CLASSIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_BL_SHELFMARK = "AAAAAAAAAA";
    private static final String UPDATED_BL_SHELFMARK = "BBBBBBBBBB";

    private static final String DEFAULT_TOPICS = "AAAAAAAAAA";
    private static final String UPDATED_TOPICS = "BBBBBBBBBB";

    private static final String DEFAULT_GENRE = "AAAAAAAAAA";
    private static final String UPDATED_GENRE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGES = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGES = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_PROVENANCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVENANCE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/names";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private NameMapper nameMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNameMockMvc;

    private Name name;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Name createEntity(EntityManager em) {
        Name name = new Name()
            .name(DEFAULT_NAME)
            .datesAssociatedWithName(DEFAULT_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(DEFAULT_TYPE_OF_NAME)
            .role(DEFAULT_ROLE)
            .otherNames(DEFAULT_OTHER_NAMES)
            .blRecordId(DEFAULT_BL_RECORD_ID)
            .typeOfResource(DEFAULT_TYPE_OF_RESOURCE)
            .contentType(DEFAULT_CONTENT_TYPE)
            .materialType(DEFAULT_MATERIAL_TYPE)
            .bnbNumber(DEFAULT_BNB_NUMBER)
            .archivalResourceKey(DEFAULT_ARCHIVAL_RESOURCE_KEY)
            .isbn(DEFAULT_ISBN)
            .title(DEFAULT_TITLE)
            .variantTitles(DEFAULT_VARIANT_TITLES)
            .seriesTitle(DEFAULT_SERIES_TITLE)
            .numberWithinSeries(DEFAULT_NUMBER_WITHIN_SERIES)
            .countryOfPublication(DEFAULT_COUNTRY_OF_PUBLICATION)
            .placeOfPublication(DEFAULT_PLACE_OF_PUBLICATION)
            .publisher(DEFAULT_PUBLISHER)
            .dateOfPublication(DEFAULT_DATE_OF_PUBLICATION)
            .edition(DEFAULT_EDITION)
            .physicalDescription(DEFAULT_PHYSICAL_DESCRIPTION)
            .deweyClassification(DEFAULT_DEWEY_CLASSIFICATION)
            .blShelfmark(DEFAULT_BL_SHELFMARK)
            .topics(DEFAULT_TOPICS)
            .genre(DEFAULT_GENRE)
            .languages(DEFAULT_LANGUAGES)
            .notes(DEFAULT_NOTES)
            .provenance(DEFAULT_PROVENANCE);
        return name;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Name createUpdatedEntity(EntityManager em) {
        Name name = new Name()
            .name(UPDATED_NAME)
            .datesAssociatedWithName(UPDATED_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .role(UPDATED_ROLE)
            .otherNames(UPDATED_OTHER_NAMES)
            .blRecordId(UPDATED_BL_RECORD_ID)
            .typeOfResource(UPDATED_TYPE_OF_RESOURCE)
            .contentType(UPDATED_CONTENT_TYPE)
            .materialType(UPDATED_MATERIAL_TYPE)
            .bnbNumber(UPDATED_BNB_NUMBER)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .isbn(UPDATED_ISBN)
            .title(UPDATED_TITLE)
            .variantTitles(UPDATED_VARIANT_TITLES)
            .seriesTitle(UPDATED_SERIES_TITLE)
            .numberWithinSeries(UPDATED_NUMBER_WITHIN_SERIES)
            .countryOfPublication(UPDATED_COUNTRY_OF_PUBLICATION)
            .placeOfPublication(UPDATED_PLACE_OF_PUBLICATION)
            .publisher(UPDATED_PUBLISHER)
            .dateOfPublication(UPDATED_DATE_OF_PUBLICATION)
            .edition(UPDATED_EDITION)
            .physicalDescription(UPDATED_PHYSICAL_DESCRIPTION)
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .topics(UPDATED_TOPICS)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);
        return name;
    }

    @BeforeEach
    public void initTest() {
        name = createEntity(em);
    }

    @Test
    @Transactional
    void createName() throws Exception {
        int databaseSizeBeforeCreate = nameRepository.findAll().size();
        // Create the Name
        NameDTO nameDTO = nameMapper.toDto(name);
        restNameMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nameDTO)))
            .andExpect(status().isCreated());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeCreate + 1);
        Name testName = nameList.get(nameList.size() - 1);
        assertThat(testName.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testName.getDatesAssociatedWithName()).isEqualTo(DEFAULT_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testName.getTypeOfName()).isEqualTo(DEFAULT_TYPE_OF_NAME);
        assertThat(testName.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testName.getOtherNames()).isEqualTo(DEFAULT_OTHER_NAMES);
        assertThat(testName.getBlRecordId()).isEqualTo(DEFAULT_BL_RECORD_ID);
        assertThat(testName.getTypeOfResource()).isEqualTo(DEFAULT_TYPE_OF_RESOURCE);
        assertThat(testName.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testName.getMaterialType()).isEqualTo(DEFAULT_MATERIAL_TYPE);
        assertThat(testName.getBnbNumber()).isEqualTo(DEFAULT_BNB_NUMBER);
        assertThat(testName.getArchivalResourceKey()).isEqualTo(DEFAULT_ARCHIVAL_RESOURCE_KEY);
        assertThat(testName.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testName.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testName.getVariantTitles()).isEqualTo(DEFAULT_VARIANT_TITLES);
        assertThat(testName.getSeriesTitle()).isEqualTo(DEFAULT_SERIES_TITLE);
        assertThat(testName.getNumberWithinSeries()).isEqualTo(DEFAULT_NUMBER_WITHIN_SERIES);
        assertThat(testName.getCountryOfPublication()).isEqualTo(DEFAULT_COUNTRY_OF_PUBLICATION);
        assertThat(testName.getPlaceOfPublication()).isEqualTo(DEFAULT_PLACE_OF_PUBLICATION);
        assertThat(testName.getPublisher()).isEqualTo(DEFAULT_PUBLISHER);
        assertThat(testName.getDateOfPublication()).isEqualTo(DEFAULT_DATE_OF_PUBLICATION);
        assertThat(testName.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testName.getPhysicalDescription()).isEqualTo(DEFAULT_PHYSICAL_DESCRIPTION);
        assertThat(testName.getDeweyClassification()).isEqualTo(DEFAULT_DEWEY_CLASSIFICATION);
        assertThat(testName.getBlShelfmark()).isEqualTo(DEFAULT_BL_SHELFMARK);
        assertThat(testName.getTopics()).isEqualTo(DEFAULT_TOPICS);
        assertThat(testName.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testName.getLanguages()).isEqualTo(DEFAULT_LANGUAGES);
        assertThat(testName.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testName.getProvenance()).isEqualTo(DEFAULT_PROVENANCE);
    }

    @Test
    @Transactional
    void createNameWithExistingId() throws Exception {
        // Create the Name with an existing ID
        name.setId(1L);
        NameDTO nameDTO = nameMapper.toDto(name);

        int databaseSizeBeforeCreate = nameRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNameMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNames() throws Exception {
        // Initialize the database
        nameRepository.saveAndFlush(name);

        // Get all the nameList
        restNameMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(name.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].datesAssociatedWithName").value(hasItem(DEFAULT_DATES_ASSOCIATED_WITH_NAME)))
            .andExpect(jsonPath("$.[*].typeOfName").value(hasItem(DEFAULT_TYPE_OF_NAME)))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].otherNames").value(hasItem(DEFAULT_OTHER_NAMES)))
            .andExpect(jsonPath("$.[*].blRecordId").value(hasItem(DEFAULT_BL_RECORD_ID)))
            .andExpect(jsonPath("$.[*].typeOfResource").value(hasItem(DEFAULT_TYPE_OF_RESOURCE)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].materialType").value(hasItem(DEFAULT_MATERIAL_TYPE)))
            .andExpect(jsonPath("$.[*].bnbNumber").value(hasItem(DEFAULT_BNB_NUMBER)))
            .andExpect(jsonPath("$.[*].archivalResourceKey").value(hasItem(DEFAULT_ARCHIVAL_RESOURCE_KEY)))
            .andExpect(jsonPath("$.[*].isbn").value(hasItem(DEFAULT_ISBN)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].variantTitles").value(hasItem(DEFAULT_VARIANT_TITLES)))
            .andExpect(jsonPath("$.[*].seriesTitle").value(hasItem(DEFAULT_SERIES_TITLE)))
            .andExpect(jsonPath("$.[*].numberWithinSeries").value(hasItem(DEFAULT_NUMBER_WITHIN_SERIES)))
            .andExpect(jsonPath("$.[*].countryOfPublication").value(hasItem(DEFAULT_COUNTRY_OF_PUBLICATION)))
            .andExpect(jsonPath("$.[*].placeOfPublication").value(hasItem(DEFAULT_PLACE_OF_PUBLICATION)))
            .andExpect(jsonPath("$.[*].publisher").value(hasItem(DEFAULT_PUBLISHER)))
            .andExpect(jsonPath("$.[*].dateOfPublication").value(hasItem(DEFAULT_DATE_OF_PUBLICATION)))
            .andExpect(jsonPath("$.[*].edition").value(hasItem(DEFAULT_EDITION)))
            .andExpect(jsonPath("$.[*].physicalDescription").value(hasItem(DEFAULT_PHYSICAL_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].deweyClassification").value(hasItem(DEFAULT_DEWEY_CLASSIFICATION)))
            .andExpect(jsonPath("$.[*].blShelfmark").value(hasItem(DEFAULT_BL_SHELFMARK)))
            .andExpect(jsonPath("$.[*].topics").value(hasItem(DEFAULT_TOPICS)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].languages").value(hasItem(DEFAULT_LANGUAGES)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].provenance").value(hasItem(DEFAULT_PROVENANCE)));
    }

    @Test
    @Transactional
    void getName() throws Exception {
        // Initialize the database
        nameRepository.saveAndFlush(name);

        // Get the name
        restNameMockMvc
            .perform(get(ENTITY_API_URL_ID, name.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(name.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.datesAssociatedWithName").value(DEFAULT_DATES_ASSOCIATED_WITH_NAME))
            .andExpect(jsonPath("$.typeOfName").value(DEFAULT_TYPE_OF_NAME))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.otherNames").value(DEFAULT_OTHER_NAMES))
            .andExpect(jsonPath("$.blRecordId").value(DEFAULT_BL_RECORD_ID))
            .andExpect(jsonPath("$.typeOfResource").value(DEFAULT_TYPE_OF_RESOURCE))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.materialType").value(DEFAULT_MATERIAL_TYPE))
            .andExpect(jsonPath("$.bnbNumber").value(DEFAULT_BNB_NUMBER))
            .andExpect(jsonPath("$.archivalResourceKey").value(DEFAULT_ARCHIVAL_RESOURCE_KEY))
            .andExpect(jsonPath("$.isbn").value(DEFAULT_ISBN))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.variantTitles").value(DEFAULT_VARIANT_TITLES))
            .andExpect(jsonPath("$.seriesTitle").value(DEFAULT_SERIES_TITLE))
            .andExpect(jsonPath("$.numberWithinSeries").value(DEFAULT_NUMBER_WITHIN_SERIES))
            .andExpect(jsonPath("$.countryOfPublication").value(DEFAULT_COUNTRY_OF_PUBLICATION))
            .andExpect(jsonPath("$.placeOfPublication").value(DEFAULT_PLACE_OF_PUBLICATION))
            .andExpect(jsonPath("$.publisher").value(DEFAULT_PUBLISHER))
            .andExpect(jsonPath("$.dateOfPublication").value(DEFAULT_DATE_OF_PUBLICATION))
            .andExpect(jsonPath("$.edition").value(DEFAULT_EDITION))
            .andExpect(jsonPath("$.physicalDescription").value(DEFAULT_PHYSICAL_DESCRIPTION))
            .andExpect(jsonPath("$.deweyClassification").value(DEFAULT_DEWEY_CLASSIFICATION))
            .andExpect(jsonPath("$.blShelfmark").value(DEFAULT_BL_SHELFMARK))
            .andExpect(jsonPath("$.topics").value(DEFAULT_TOPICS))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE))
            .andExpect(jsonPath("$.languages").value(DEFAULT_LANGUAGES))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.provenance").value(DEFAULT_PROVENANCE));
    }

    @Test
    @Transactional
    void getNonExistingName() throws Exception {
        // Get the name
        restNameMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingName() throws Exception {
        // Initialize the database
        nameRepository.saveAndFlush(name);

        int databaseSizeBeforeUpdate = nameRepository.findAll().size();

        // Update the name
        Name updatedName = nameRepository.findById(name.getId()).get();
        // Disconnect from session so that the updates on updatedName are not directly saved in db
        em.detach(updatedName);
        updatedName
            .name(UPDATED_NAME)
            .datesAssociatedWithName(UPDATED_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .role(UPDATED_ROLE)
            .otherNames(UPDATED_OTHER_NAMES)
            .blRecordId(UPDATED_BL_RECORD_ID)
            .typeOfResource(UPDATED_TYPE_OF_RESOURCE)
            .contentType(UPDATED_CONTENT_TYPE)
            .materialType(UPDATED_MATERIAL_TYPE)
            .bnbNumber(UPDATED_BNB_NUMBER)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .isbn(UPDATED_ISBN)
            .title(UPDATED_TITLE)
            .variantTitles(UPDATED_VARIANT_TITLES)
            .seriesTitle(UPDATED_SERIES_TITLE)
            .numberWithinSeries(UPDATED_NUMBER_WITHIN_SERIES)
            .countryOfPublication(UPDATED_COUNTRY_OF_PUBLICATION)
            .placeOfPublication(UPDATED_PLACE_OF_PUBLICATION)
            .publisher(UPDATED_PUBLISHER)
            .dateOfPublication(UPDATED_DATE_OF_PUBLICATION)
            .edition(UPDATED_EDITION)
            .physicalDescription(UPDATED_PHYSICAL_DESCRIPTION)
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .topics(UPDATED_TOPICS)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);
        NameDTO nameDTO = nameMapper.toDto(updatedName);

        restNameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nameDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nameDTO))
            )
            .andExpect(status().isOk());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
        Name testName = nameList.get(nameList.size() - 1);
        assertThat(testName.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testName.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testName.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testName.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testName.getOtherNames()).isEqualTo(UPDATED_OTHER_NAMES);
        assertThat(testName.getBlRecordId()).isEqualTo(UPDATED_BL_RECORD_ID);
        assertThat(testName.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testName.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testName.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testName.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testName.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testName.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testName.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testName.getVariantTitles()).isEqualTo(UPDATED_VARIANT_TITLES);
        assertThat(testName.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testName.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testName.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testName.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testName.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testName.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testName.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testName.getPhysicalDescription()).isEqualTo(UPDATED_PHYSICAL_DESCRIPTION);
        assertThat(testName.getDeweyClassification()).isEqualTo(UPDATED_DEWEY_CLASSIFICATION);
        assertThat(testName.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testName.getTopics()).isEqualTo(UPDATED_TOPICS);
        assertThat(testName.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testName.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testName.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testName.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void putNonExistingName() throws Exception {
        int databaseSizeBeforeUpdate = nameRepository.findAll().size();
        name.setId(count.incrementAndGet());

        // Create the Name
        NameDTO nameDTO = nameMapper.toDto(name);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nameDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchName() throws Exception {
        int databaseSizeBeforeUpdate = nameRepository.findAll().size();
        name.setId(count.incrementAndGet());

        // Create the Name
        NameDTO nameDTO = nameMapper.toDto(name);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamName() throws Exception {
        int databaseSizeBeforeUpdate = nameRepository.findAll().size();
        name.setId(count.incrementAndGet());

        // Create the Name
        NameDTO nameDTO = nameMapper.toDto(name);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNameMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nameDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNameWithPatch() throws Exception {
        // Initialize the database
        nameRepository.saveAndFlush(name);

        int databaseSizeBeforeUpdate = nameRepository.findAll().size();

        // Update the name using partial update
        Name partialUpdatedName = new Name();
        partialUpdatedName.setId(name.getId());

        partialUpdatedName
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .otherNames(UPDATED_OTHER_NAMES)
            .typeOfResource(UPDATED_TYPE_OF_RESOURCE)
            .contentType(UPDATED_CONTENT_TYPE)
            .variantTitles(UPDATED_VARIANT_TITLES)
            .seriesTitle(UPDATED_SERIES_TITLE)
            .numberWithinSeries(UPDATED_NUMBER_WITHIN_SERIES)
            .publisher(UPDATED_PUBLISHER)
            .dateOfPublication(UPDATED_DATE_OF_PUBLICATION)
            .genre(UPDATED_GENRE)
            .notes(UPDATED_NOTES);

        restNameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedName.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedName))
            )
            .andExpect(status().isOk());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
        Name testName = nameList.get(nameList.size() - 1);
        assertThat(testName.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testName.getDatesAssociatedWithName()).isEqualTo(DEFAULT_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testName.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testName.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testName.getOtherNames()).isEqualTo(UPDATED_OTHER_NAMES);
        assertThat(testName.getBlRecordId()).isEqualTo(DEFAULT_BL_RECORD_ID);
        assertThat(testName.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testName.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testName.getMaterialType()).isEqualTo(DEFAULT_MATERIAL_TYPE);
        assertThat(testName.getBnbNumber()).isEqualTo(DEFAULT_BNB_NUMBER);
        assertThat(testName.getArchivalResourceKey()).isEqualTo(DEFAULT_ARCHIVAL_RESOURCE_KEY);
        assertThat(testName.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testName.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testName.getVariantTitles()).isEqualTo(UPDATED_VARIANT_TITLES);
        assertThat(testName.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testName.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testName.getCountryOfPublication()).isEqualTo(DEFAULT_COUNTRY_OF_PUBLICATION);
        assertThat(testName.getPlaceOfPublication()).isEqualTo(DEFAULT_PLACE_OF_PUBLICATION);
        assertThat(testName.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testName.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testName.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testName.getPhysicalDescription()).isEqualTo(DEFAULT_PHYSICAL_DESCRIPTION);
        assertThat(testName.getDeweyClassification()).isEqualTo(DEFAULT_DEWEY_CLASSIFICATION);
        assertThat(testName.getBlShelfmark()).isEqualTo(DEFAULT_BL_SHELFMARK);
        assertThat(testName.getTopics()).isEqualTo(DEFAULT_TOPICS);
        assertThat(testName.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testName.getLanguages()).isEqualTo(DEFAULT_LANGUAGES);
        assertThat(testName.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testName.getProvenance()).isEqualTo(DEFAULT_PROVENANCE);
    }

    @Test
    @Transactional
    void fullUpdateNameWithPatch() throws Exception {
        // Initialize the database
        nameRepository.saveAndFlush(name);

        int databaseSizeBeforeUpdate = nameRepository.findAll().size();

        // Update the name using partial update
        Name partialUpdatedName = new Name();
        partialUpdatedName.setId(name.getId());

        partialUpdatedName
            .name(UPDATED_NAME)
            .datesAssociatedWithName(UPDATED_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .role(UPDATED_ROLE)
            .otherNames(UPDATED_OTHER_NAMES)
            .blRecordId(UPDATED_BL_RECORD_ID)
            .typeOfResource(UPDATED_TYPE_OF_RESOURCE)
            .contentType(UPDATED_CONTENT_TYPE)
            .materialType(UPDATED_MATERIAL_TYPE)
            .bnbNumber(UPDATED_BNB_NUMBER)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .isbn(UPDATED_ISBN)
            .title(UPDATED_TITLE)
            .variantTitles(UPDATED_VARIANT_TITLES)
            .seriesTitle(UPDATED_SERIES_TITLE)
            .numberWithinSeries(UPDATED_NUMBER_WITHIN_SERIES)
            .countryOfPublication(UPDATED_COUNTRY_OF_PUBLICATION)
            .placeOfPublication(UPDATED_PLACE_OF_PUBLICATION)
            .publisher(UPDATED_PUBLISHER)
            .dateOfPublication(UPDATED_DATE_OF_PUBLICATION)
            .edition(UPDATED_EDITION)
            .physicalDescription(UPDATED_PHYSICAL_DESCRIPTION)
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .topics(UPDATED_TOPICS)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);

        restNameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedName.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedName))
            )
            .andExpect(status().isOk());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
        Name testName = nameList.get(nameList.size() - 1);
        assertThat(testName.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testName.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testName.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testName.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testName.getOtherNames()).isEqualTo(UPDATED_OTHER_NAMES);
        assertThat(testName.getBlRecordId()).isEqualTo(UPDATED_BL_RECORD_ID);
        assertThat(testName.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testName.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testName.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testName.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testName.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testName.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testName.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testName.getVariantTitles()).isEqualTo(UPDATED_VARIANT_TITLES);
        assertThat(testName.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testName.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testName.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testName.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testName.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testName.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testName.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testName.getPhysicalDescription()).isEqualTo(UPDATED_PHYSICAL_DESCRIPTION);
        assertThat(testName.getDeweyClassification()).isEqualTo(UPDATED_DEWEY_CLASSIFICATION);
        assertThat(testName.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testName.getTopics()).isEqualTo(UPDATED_TOPICS);
        assertThat(testName.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testName.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testName.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testName.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void patchNonExistingName() throws Exception {
        int databaseSizeBeforeUpdate = nameRepository.findAll().size();
        name.setId(count.incrementAndGet());

        // Create the Name
        NameDTO nameDTO = nameMapper.toDto(name);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nameDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchName() throws Exception {
        int databaseSizeBeforeUpdate = nameRepository.findAll().size();
        name.setId(count.incrementAndGet());

        // Create the Name
        NameDTO nameDTO = nameMapper.toDto(name);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamName() throws Exception {
        int databaseSizeBeforeUpdate = nameRepository.findAll().size();
        name.setId(count.incrementAndGet());

        // Create the Name
        NameDTO nameDTO = nameMapper.toDto(name);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNameMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(nameDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Name in the database
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteName() throws Exception {
        // Initialize the database
        nameRepository.saveAndFlush(name);

        int databaseSizeBeforeDelete = nameRepository.findAll().size();

        // Delete the name
        restNameMockMvc
            .perform(delete(ENTITY_API_URL_ID, name.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Name> nameList = nameRepository.findAll();
        assertThat(nameList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
