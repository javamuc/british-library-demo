package com.demoproject.bl.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.demoproject.bl.IntegrationTest;
import com.demoproject.bl.domain.Classification;
import com.demoproject.bl.repository.ClassificationRepository;
import com.demoproject.bl.service.dto.ClassificationDTO;
import com.demoproject.bl.service.mapper.ClassificationMapper;
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
 * Integration tests for the {@link ClassificationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClassificationResourceIT {

    private static final String DEFAULT_DEWEY_CLASSIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_DEWEY_CLASSIFICATION = "BBBBBBBBBB";

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

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DATES_ASSOCIATED_WITH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DATES_ASSOCIATED_WITH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final String DEFAULT_ALL_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_ALL_NAMES = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/classifications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClassificationRepository classificationRepository;

    @Autowired
    private ClassificationMapper classificationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClassificationMockMvc;

    private Classification classification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classification createEntity(EntityManager em) {
        Classification classification = new Classification()
            .deweyClassification(DEFAULT_DEWEY_CLASSIFICATION)
            .blRecordId(DEFAULT_BL_RECORD_ID)
            .typeOfResource(DEFAULT_TYPE_OF_RESOURCE)
            .contentType(DEFAULT_CONTENT_TYPE)
            .materialType(DEFAULT_MATERIAL_TYPE)
            .bnbNumber(DEFAULT_BNB_NUMBER)
            .archivalResourceKey(DEFAULT_ARCHIVAL_RESOURCE_KEY)
            .isbn(DEFAULT_ISBN)
            .name(DEFAULT_NAME)
            .datesAssociatedWithName(DEFAULT_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(DEFAULT_TYPE_OF_NAME)
            .role(DEFAULT_ROLE)
            .allNames(DEFAULT_ALL_NAMES)
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
            .blShelfmark(DEFAULT_BL_SHELFMARK)
            .topics(DEFAULT_TOPICS)
            .genre(DEFAULT_GENRE)
            .languages(DEFAULT_LANGUAGES)
            .notes(DEFAULT_NOTES)
            .provenance(DEFAULT_PROVENANCE);
        return classification;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classification createUpdatedEntity(EntityManager em) {
        Classification classification = new Classification()
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blRecordId(UPDATED_BL_RECORD_ID)
            .typeOfResource(UPDATED_TYPE_OF_RESOURCE)
            .contentType(UPDATED_CONTENT_TYPE)
            .materialType(UPDATED_MATERIAL_TYPE)
            .bnbNumber(UPDATED_BNB_NUMBER)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .isbn(UPDATED_ISBN)
            .name(UPDATED_NAME)
            .datesAssociatedWithName(UPDATED_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .role(UPDATED_ROLE)
            .allNames(UPDATED_ALL_NAMES)
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
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .topics(UPDATED_TOPICS)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);
        return classification;
    }

    @BeforeEach
    public void initTest() {
        classification = createEntity(em);
    }

    @Test
    @Transactional
    void createClassification() throws Exception {
        int databaseSizeBeforeCreate = classificationRepository.findAll().size();
        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);
        restClassificationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeCreate + 1);
        Classification testClassification = classificationList.get(classificationList.size() - 1);
        assertThat(testClassification.getDeweyClassification()).isEqualTo(DEFAULT_DEWEY_CLASSIFICATION);
        assertThat(testClassification.getBlRecordId()).isEqualTo(DEFAULT_BL_RECORD_ID);
        assertThat(testClassification.getTypeOfResource()).isEqualTo(DEFAULT_TYPE_OF_RESOURCE);
        assertThat(testClassification.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testClassification.getMaterialType()).isEqualTo(DEFAULT_MATERIAL_TYPE);
        assertThat(testClassification.getBnbNumber()).isEqualTo(DEFAULT_BNB_NUMBER);
        assertThat(testClassification.getArchivalResourceKey()).isEqualTo(DEFAULT_ARCHIVAL_RESOURCE_KEY);
        assertThat(testClassification.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testClassification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testClassification.getDatesAssociatedWithName()).isEqualTo(DEFAULT_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testClassification.getTypeOfName()).isEqualTo(DEFAULT_TYPE_OF_NAME);
        assertThat(testClassification.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testClassification.getAllNames()).isEqualTo(DEFAULT_ALL_NAMES);
        assertThat(testClassification.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testClassification.getVariantTitles()).isEqualTo(DEFAULT_VARIANT_TITLES);
        assertThat(testClassification.getSeriesTitle()).isEqualTo(DEFAULT_SERIES_TITLE);
        assertThat(testClassification.getNumberWithinSeries()).isEqualTo(DEFAULT_NUMBER_WITHIN_SERIES);
        assertThat(testClassification.getCountryOfPublication()).isEqualTo(DEFAULT_COUNTRY_OF_PUBLICATION);
        assertThat(testClassification.getPlaceOfPublication()).isEqualTo(DEFAULT_PLACE_OF_PUBLICATION);
        assertThat(testClassification.getPublisher()).isEqualTo(DEFAULT_PUBLISHER);
        assertThat(testClassification.getDateOfPublication()).isEqualTo(DEFAULT_DATE_OF_PUBLICATION);
        assertThat(testClassification.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testClassification.getPhysicalDescription()).isEqualTo(DEFAULT_PHYSICAL_DESCRIPTION);
        assertThat(testClassification.getBlShelfmark()).isEqualTo(DEFAULT_BL_SHELFMARK);
        assertThat(testClassification.getTopics()).isEqualTo(DEFAULT_TOPICS);
        assertThat(testClassification.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testClassification.getLanguages()).isEqualTo(DEFAULT_LANGUAGES);
        assertThat(testClassification.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testClassification.getProvenance()).isEqualTo(DEFAULT_PROVENANCE);
    }

    @Test
    @Transactional
    void createClassificationWithExistingId() throws Exception {
        // Create the Classification with an existing ID
        classification.setId(1L);
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        int databaseSizeBeforeCreate = classificationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClassificationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClassifications() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        // Get all the classificationList
        restClassificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(classification.getId().intValue())))
            .andExpect(jsonPath("$.[*].deweyClassification").value(hasItem(DEFAULT_DEWEY_CLASSIFICATION)))
            .andExpect(jsonPath("$.[*].blRecordId").value(hasItem(DEFAULT_BL_RECORD_ID)))
            .andExpect(jsonPath("$.[*].typeOfResource").value(hasItem(DEFAULT_TYPE_OF_RESOURCE)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].materialType").value(hasItem(DEFAULT_MATERIAL_TYPE)))
            .andExpect(jsonPath("$.[*].bnbNumber").value(hasItem(DEFAULT_BNB_NUMBER)))
            .andExpect(jsonPath("$.[*].archivalResourceKey").value(hasItem(DEFAULT_ARCHIVAL_RESOURCE_KEY)))
            .andExpect(jsonPath("$.[*].isbn").value(hasItem(DEFAULT_ISBN)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].datesAssociatedWithName").value(hasItem(DEFAULT_DATES_ASSOCIATED_WITH_NAME)))
            .andExpect(jsonPath("$.[*].typeOfName").value(hasItem(DEFAULT_TYPE_OF_NAME)))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].allNames").value(hasItem(DEFAULT_ALL_NAMES)))
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
            .andExpect(jsonPath("$.[*].blShelfmark").value(hasItem(DEFAULT_BL_SHELFMARK)))
            .andExpect(jsonPath("$.[*].topics").value(hasItem(DEFAULT_TOPICS)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].languages").value(hasItem(DEFAULT_LANGUAGES)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].provenance").value(hasItem(DEFAULT_PROVENANCE)));
    }

    @Test
    @Transactional
    void getClassification() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        // Get the classification
        restClassificationMockMvc
            .perform(get(ENTITY_API_URL_ID, classification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(classification.getId().intValue()))
            .andExpect(jsonPath("$.deweyClassification").value(DEFAULT_DEWEY_CLASSIFICATION))
            .andExpect(jsonPath("$.blRecordId").value(DEFAULT_BL_RECORD_ID))
            .andExpect(jsonPath("$.typeOfResource").value(DEFAULT_TYPE_OF_RESOURCE))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.materialType").value(DEFAULT_MATERIAL_TYPE))
            .andExpect(jsonPath("$.bnbNumber").value(DEFAULT_BNB_NUMBER))
            .andExpect(jsonPath("$.archivalResourceKey").value(DEFAULT_ARCHIVAL_RESOURCE_KEY))
            .andExpect(jsonPath("$.isbn").value(DEFAULT_ISBN))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.datesAssociatedWithName").value(DEFAULT_DATES_ASSOCIATED_WITH_NAME))
            .andExpect(jsonPath("$.typeOfName").value(DEFAULT_TYPE_OF_NAME))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.allNames").value(DEFAULT_ALL_NAMES))
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
            .andExpect(jsonPath("$.blShelfmark").value(DEFAULT_BL_SHELFMARK))
            .andExpect(jsonPath("$.topics").value(DEFAULT_TOPICS))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE))
            .andExpect(jsonPath("$.languages").value(DEFAULT_LANGUAGES))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.provenance").value(DEFAULT_PROVENANCE));
    }

    @Test
    @Transactional
    void getNonExistingClassification() throws Exception {
        // Get the classification
        restClassificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClassification() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();

        // Update the classification
        Classification updatedClassification = classificationRepository.findById(classification.getId()).get();
        // Disconnect from session so that the updates on updatedClassification are not directly saved in db
        em.detach(updatedClassification);
        updatedClassification
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blRecordId(UPDATED_BL_RECORD_ID)
            .typeOfResource(UPDATED_TYPE_OF_RESOURCE)
            .contentType(UPDATED_CONTENT_TYPE)
            .materialType(UPDATED_MATERIAL_TYPE)
            .bnbNumber(UPDATED_BNB_NUMBER)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .isbn(UPDATED_ISBN)
            .name(UPDATED_NAME)
            .datesAssociatedWithName(UPDATED_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .role(UPDATED_ROLE)
            .allNames(UPDATED_ALL_NAMES)
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
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .topics(UPDATED_TOPICS)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);
        ClassificationDTO classificationDTO = classificationMapper.toDto(updatedClassification);

        restClassificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, classificationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isOk());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
        Classification testClassification = classificationList.get(classificationList.size() - 1);
        assertThat(testClassification.getDeweyClassification()).isEqualTo(UPDATED_DEWEY_CLASSIFICATION);
        assertThat(testClassification.getBlRecordId()).isEqualTo(UPDATED_BL_RECORD_ID);
        assertThat(testClassification.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testClassification.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testClassification.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testClassification.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testClassification.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testClassification.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testClassification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClassification.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testClassification.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testClassification.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testClassification.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testClassification.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testClassification.getVariantTitles()).isEqualTo(UPDATED_VARIANT_TITLES);
        assertThat(testClassification.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testClassification.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testClassification.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testClassification.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testClassification.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testClassification.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testClassification.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testClassification.getPhysicalDescription()).isEqualTo(UPDATED_PHYSICAL_DESCRIPTION);
        assertThat(testClassification.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testClassification.getTopics()).isEqualTo(UPDATED_TOPICS);
        assertThat(testClassification.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testClassification.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testClassification.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testClassification.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void putNonExistingClassification() throws Exception {
        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();
        classification.setId(count.incrementAndGet());

        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClassificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, classificationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClassification() throws Exception {
        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();
        classification.setId(count.incrementAndGet());

        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClassificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClassification() throws Exception {
        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();
        classification.setId(count.incrementAndGet());

        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClassificationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClassificationWithPatch() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();

        // Update the classification using partial update
        Classification partialUpdatedClassification = new Classification();
        partialUpdatedClassification.setId(classification.getId());

        partialUpdatedClassification
            .contentType(UPDATED_CONTENT_TYPE)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .isbn(UPDATED_ISBN)
            .name(UPDATED_NAME)
            .datesAssociatedWithName(UPDATED_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .role(UPDATED_ROLE)
            .allNames(UPDATED_ALL_NAMES)
            .variantTitles(UPDATED_VARIANT_TITLES)
            .publisher(UPDATED_PUBLISHER)
            .edition(UPDATED_EDITION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .topics(UPDATED_TOPICS)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .provenance(UPDATED_PROVENANCE);

        restClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClassification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClassification))
            )
            .andExpect(status().isOk());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
        Classification testClassification = classificationList.get(classificationList.size() - 1);
        assertThat(testClassification.getDeweyClassification()).isEqualTo(DEFAULT_DEWEY_CLASSIFICATION);
        assertThat(testClassification.getBlRecordId()).isEqualTo(DEFAULT_BL_RECORD_ID);
        assertThat(testClassification.getTypeOfResource()).isEqualTo(DEFAULT_TYPE_OF_RESOURCE);
        assertThat(testClassification.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testClassification.getMaterialType()).isEqualTo(DEFAULT_MATERIAL_TYPE);
        assertThat(testClassification.getBnbNumber()).isEqualTo(DEFAULT_BNB_NUMBER);
        assertThat(testClassification.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testClassification.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testClassification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClassification.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testClassification.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testClassification.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testClassification.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testClassification.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testClassification.getVariantTitles()).isEqualTo(UPDATED_VARIANT_TITLES);
        assertThat(testClassification.getSeriesTitle()).isEqualTo(DEFAULT_SERIES_TITLE);
        assertThat(testClassification.getNumberWithinSeries()).isEqualTo(DEFAULT_NUMBER_WITHIN_SERIES);
        assertThat(testClassification.getCountryOfPublication()).isEqualTo(DEFAULT_COUNTRY_OF_PUBLICATION);
        assertThat(testClassification.getPlaceOfPublication()).isEqualTo(DEFAULT_PLACE_OF_PUBLICATION);
        assertThat(testClassification.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testClassification.getDateOfPublication()).isEqualTo(DEFAULT_DATE_OF_PUBLICATION);
        assertThat(testClassification.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testClassification.getPhysicalDescription()).isEqualTo(DEFAULT_PHYSICAL_DESCRIPTION);
        assertThat(testClassification.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testClassification.getTopics()).isEqualTo(UPDATED_TOPICS);
        assertThat(testClassification.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testClassification.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testClassification.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testClassification.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void fullUpdateClassificationWithPatch() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();

        // Update the classification using partial update
        Classification partialUpdatedClassification = new Classification();
        partialUpdatedClassification.setId(classification.getId());

        partialUpdatedClassification
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blRecordId(UPDATED_BL_RECORD_ID)
            .typeOfResource(UPDATED_TYPE_OF_RESOURCE)
            .contentType(UPDATED_CONTENT_TYPE)
            .materialType(UPDATED_MATERIAL_TYPE)
            .bnbNumber(UPDATED_BNB_NUMBER)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .isbn(UPDATED_ISBN)
            .name(UPDATED_NAME)
            .datesAssociatedWithName(UPDATED_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .role(UPDATED_ROLE)
            .allNames(UPDATED_ALL_NAMES)
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
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .topics(UPDATED_TOPICS)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);

        restClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClassification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClassification))
            )
            .andExpect(status().isOk());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
        Classification testClassification = classificationList.get(classificationList.size() - 1);
        assertThat(testClassification.getDeweyClassification()).isEqualTo(UPDATED_DEWEY_CLASSIFICATION);
        assertThat(testClassification.getBlRecordId()).isEqualTo(UPDATED_BL_RECORD_ID);
        assertThat(testClassification.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testClassification.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testClassification.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testClassification.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testClassification.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testClassification.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testClassification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClassification.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testClassification.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testClassification.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testClassification.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testClassification.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testClassification.getVariantTitles()).isEqualTo(UPDATED_VARIANT_TITLES);
        assertThat(testClassification.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testClassification.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testClassification.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testClassification.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testClassification.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testClassification.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testClassification.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testClassification.getPhysicalDescription()).isEqualTo(UPDATED_PHYSICAL_DESCRIPTION);
        assertThat(testClassification.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testClassification.getTopics()).isEqualTo(UPDATED_TOPICS);
        assertThat(testClassification.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testClassification.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testClassification.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testClassification.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void patchNonExistingClassification() throws Exception {
        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();
        classification.setId(count.incrementAndGet());

        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, classificationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClassification() throws Exception {
        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();
        classification.setId(count.incrementAndGet());

        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClassification() throws Exception {
        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();
        classification.setId(count.incrementAndGet());

        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClassificationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(classificationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClassification() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        int databaseSizeBeforeDelete = classificationRepository.findAll().size();

        // Delete the classification
        restClassificationMockMvc
            .perform(delete(ENTITY_API_URL_ID, classification.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
