package com.demoproject.bl.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.demoproject.bl.IntegrationTest;
import com.demoproject.bl.domain.Title;
import com.demoproject.bl.repository.TitleRepository;
import com.demoproject.bl.service.dto.TitleDTO;
import com.demoproject.bl.service.mapper.TitleMapper;
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
 * Integration tests for the {@link TitleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TitleResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_TITLES = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_TITLES = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/titles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTitleMockMvc;

    private Title title;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Title createEntity(EntityManager em) {
        Title title = new Title()
            .title(DEFAULT_TITLE)
            .otherTitles(DEFAULT_OTHER_TITLES)
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
        return title;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Title createUpdatedEntity(EntityManager em) {
        Title title = new Title()
            .title(UPDATED_TITLE)
            .otherTitles(UPDATED_OTHER_TITLES)
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
        return title;
    }

    @BeforeEach
    public void initTest() {
        title = createEntity(em);
    }

    @Test
    @Transactional
    void createTitle() throws Exception {
        int databaseSizeBeforeCreate = titleRepository.findAll().size();
        // Create the Title
        TitleDTO titleDTO = titleMapper.toDto(title);
        restTitleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(titleDTO)))
            .andExpect(status().isCreated());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeCreate + 1);
        Title testTitle = titleList.get(titleList.size() - 1);
        assertThat(testTitle.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTitle.getOtherTitles()).isEqualTo(DEFAULT_OTHER_TITLES);
        assertThat(testTitle.getBlRecordId()).isEqualTo(DEFAULT_BL_RECORD_ID);
        assertThat(testTitle.getTypeOfResource()).isEqualTo(DEFAULT_TYPE_OF_RESOURCE);
        assertThat(testTitle.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testTitle.getMaterialType()).isEqualTo(DEFAULT_MATERIAL_TYPE);
        assertThat(testTitle.getBnbNumber()).isEqualTo(DEFAULT_BNB_NUMBER);
        assertThat(testTitle.getArchivalResourceKey()).isEqualTo(DEFAULT_ARCHIVAL_RESOURCE_KEY);
        assertThat(testTitle.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testTitle.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTitle.getDatesAssociatedWithName()).isEqualTo(DEFAULT_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testTitle.getTypeOfName()).isEqualTo(DEFAULT_TYPE_OF_NAME);
        assertThat(testTitle.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testTitle.getAllNames()).isEqualTo(DEFAULT_ALL_NAMES);
        assertThat(testTitle.getSeriesTitle()).isEqualTo(DEFAULT_SERIES_TITLE);
        assertThat(testTitle.getNumberWithinSeries()).isEqualTo(DEFAULT_NUMBER_WITHIN_SERIES);
        assertThat(testTitle.getCountryOfPublication()).isEqualTo(DEFAULT_COUNTRY_OF_PUBLICATION);
        assertThat(testTitle.getPlaceOfPublication()).isEqualTo(DEFAULT_PLACE_OF_PUBLICATION);
        assertThat(testTitle.getPublisher()).isEqualTo(DEFAULT_PUBLISHER);
        assertThat(testTitle.getDateOfPublication()).isEqualTo(DEFAULT_DATE_OF_PUBLICATION);
        assertThat(testTitle.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testTitle.getPhysicalDescription()).isEqualTo(DEFAULT_PHYSICAL_DESCRIPTION);
        assertThat(testTitle.getDeweyClassification()).isEqualTo(DEFAULT_DEWEY_CLASSIFICATION);
        assertThat(testTitle.getBlShelfmark()).isEqualTo(DEFAULT_BL_SHELFMARK);
        assertThat(testTitle.getTopics()).isEqualTo(DEFAULT_TOPICS);
        assertThat(testTitle.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testTitle.getLanguages()).isEqualTo(DEFAULT_LANGUAGES);
        assertThat(testTitle.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testTitle.getProvenance()).isEqualTo(DEFAULT_PROVENANCE);
    }

    @Test
    @Transactional
    void createTitleWithExistingId() throws Exception {
        // Create the Title with an existing ID
        title.setId(1L);
        TitleDTO titleDTO = titleMapper.toDto(title);

        int databaseSizeBeforeCreate = titleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTitleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(titleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTitles() throws Exception {
        // Initialize the database
        titleRepository.saveAndFlush(title);

        // Get all the titleList
        restTitleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(title.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].otherTitles").value(hasItem(DEFAULT_OTHER_TITLES)))
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
    void getTitle() throws Exception {
        // Initialize the database
        titleRepository.saveAndFlush(title);

        // Get the title
        restTitleMockMvc
            .perform(get(ENTITY_API_URL_ID, title.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(title.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.otherTitles").value(DEFAULT_OTHER_TITLES))
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
    void getNonExistingTitle() throws Exception {
        // Get the title
        restTitleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTitle() throws Exception {
        // Initialize the database
        titleRepository.saveAndFlush(title);

        int databaseSizeBeforeUpdate = titleRepository.findAll().size();

        // Update the title
        Title updatedTitle = titleRepository.findById(title.getId()).get();
        // Disconnect from session so that the updates on updatedTitle are not directly saved in db
        em.detach(updatedTitle);
        updatedTitle
            .title(UPDATED_TITLE)
            .otherTitles(UPDATED_OTHER_TITLES)
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
        TitleDTO titleDTO = titleMapper.toDto(updatedTitle);

        restTitleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, titleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(titleDTO))
            )
            .andExpect(status().isOk());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
        Title testTitle = titleList.get(titleList.size() - 1);
        assertThat(testTitle.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTitle.getOtherTitles()).isEqualTo(UPDATED_OTHER_TITLES);
        assertThat(testTitle.getBlRecordId()).isEqualTo(UPDATED_BL_RECORD_ID);
        assertThat(testTitle.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testTitle.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testTitle.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testTitle.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testTitle.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testTitle.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testTitle.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTitle.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testTitle.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testTitle.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testTitle.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testTitle.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testTitle.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testTitle.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testTitle.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testTitle.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testTitle.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testTitle.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testTitle.getPhysicalDescription()).isEqualTo(UPDATED_PHYSICAL_DESCRIPTION);
        assertThat(testTitle.getDeweyClassification()).isEqualTo(UPDATED_DEWEY_CLASSIFICATION);
        assertThat(testTitle.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testTitle.getTopics()).isEqualTo(UPDATED_TOPICS);
        assertThat(testTitle.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testTitle.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testTitle.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testTitle.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void putNonExistingTitle() throws Exception {
        int databaseSizeBeforeUpdate = titleRepository.findAll().size();
        title.setId(count.incrementAndGet());

        // Create the Title
        TitleDTO titleDTO = titleMapper.toDto(title);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTitleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, titleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(titleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTitle() throws Exception {
        int databaseSizeBeforeUpdate = titleRepository.findAll().size();
        title.setId(count.incrementAndGet());

        // Create the Title
        TitleDTO titleDTO = titleMapper.toDto(title);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTitleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(titleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTitle() throws Exception {
        int databaseSizeBeforeUpdate = titleRepository.findAll().size();
        title.setId(count.incrementAndGet());

        // Create the Title
        TitleDTO titleDTO = titleMapper.toDto(title);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTitleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(titleDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTitleWithPatch() throws Exception {
        // Initialize the database
        titleRepository.saveAndFlush(title);

        int databaseSizeBeforeUpdate = titleRepository.findAll().size();

        // Update the title using partial update
        Title partialUpdatedTitle = new Title();
        partialUpdatedTitle.setId(title.getId());

        partialUpdatedTitle
            .otherTitles(UPDATED_OTHER_TITLES)
            .materialType(UPDATED_MATERIAL_TYPE)
            .bnbNumber(UPDATED_BNB_NUMBER)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .isbn(UPDATED_ISBN)
            .allNames(UPDATED_ALL_NAMES)
            .seriesTitle(UPDATED_SERIES_TITLE)
            .numberWithinSeries(UPDATED_NUMBER_WITHIN_SERIES)
            .placeOfPublication(UPDATED_PLACE_OF_PUBLICATION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES);

        restTitleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTitle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTitle))
            )
            .andExpect(status().isOk());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
        Title testTitle = titleList.get(titleList.size() - 1);
        assertThat(testTitle.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTitle.getOtherTitles()).isEqualTo(UPDATED_OTHER_TITLES);
        assertThat(testTitle.getBlRecordId()).isEqualTo(DEFAULT_BL_RECORD_ID);
        assertThat(testTitle.getTypeOfResource()).isEqualTo(DEFAULT_TYPE_OF_RESOURCE);
        assertThat(testTitle.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testTitle.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testTitle.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testTitle.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testTitle.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testTitle.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTitle.getDatesAssociatedWithName()).isEqualTo(DEFAULT_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testTitle.getTypeOfName()).isEqualTo(DEFAULT_TYPE_OF_NAME);
        assertThat(testTitle.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testTitle.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testTitle.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testTitle.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testTitle.getCountryOfPublication()).isEqualTo(DEFAULT_COUNTRY_OF_PUBLICATION);
        assertThat(testTitle.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testTitle.getPublisher()).isEqualTo(DEFAULT_PUBLISHER);
        assertThat(testTitle.getDateOfPublication()).isEqualTo(DEFAULT_DATE_OF_PUBLICATION);
        assertThat(testTitle.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testTitle.getPhysicalDescription()).isEqualTo(DEFAULT_PHYSICAL_DESCRIPTION);
        assertThat(testTitle.getDeweyClassification()).isEqualTo(DEFAULT_DEWEY_CLASSIFICATION);
        assertThat(testTitle.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testTitle.getTopics()).isEqualTo(DEFAULT_TOPICS);
        assertThat(testTitle.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testTitle.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testTitle.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testTitle.getProvenance()).isEqualTo(DEFAULT_PROVENANCE);
    }

    @Test
    @Transactional
    void fullUpdateTitleWithPatch() throws Exception {
        // Initialize the database
        titleRepository.saveAndFlush(title);

        int databaseSizeBeforeUpdate = titleRepository.findAll().size();

        // Update the title using partial update
        Title partialUpdatedTitle = new Title();
        partialUpdatedTitle.setId(title.getId());

        partialUpdatedTitle
            .title(UPDATED_TITLE)
            .otherTitles(UPDATED_OTHER_TITLES)
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

        restTitleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTitle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTitle))
            )
            .andExpect(status().isOk());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
        Title testTitle = titleList.get(titleList.size() - 1);
        assertThat(testTitle.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTitle.getOtherTitles()).isEqualTo(UPDATED_OTHER_TITLES);
        assertThat(testTitle.getBlRecordId()).isEqualTo(UPDATED_BL_RECORD_ID);
        assertThat(testTitle.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testTitle.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testTitle.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testTitle.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testTitle.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testTitle.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testTitle.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTitle.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testTitle.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testTitle.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testTitle.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testTitle.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testTitle.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testTitle.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testTitle.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testTitle.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testTitle.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testTitle.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testTitle.getPhysicalDescription()).isEqualTo(UPDATED_PHYSICAL_DESCRIPTION);
        assertThat(testTitle.getDeweyClassification()).isEqualTo(UPDATED_DEWEY_CLASSIFICATION);
        assertThat(testTitle.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testTitle.getTopics()).isEqualTo(UPDATED_TOPICS);
        assertThat(testTitle.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testTitle.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testTitle.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testTitle.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void patchNonExistingTitle() throws Exception {
        int databaseSizeBeforeUpdate = titleRepository.findAll().size();
        title.setId(count.incrementAndGet());

        // Create the Title
        TitleDTO titleDTO = titleMapper.toDto(title);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTitleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, titleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(titleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTitle() throws Exception {
        int databaseSizeBeforeUpdate = titleRepository.findAll().size();
        title.setId(count.incrementAndGet());

        // Create the Title
        TitleDTO titleDTO = titleMapper.toDto(title);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTitleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(titleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTitle() throws Exception {
        int databaseSizeBeforeUpdate = titleRepository.findAll().size();
        title.setId(count.incrementAndGet());

        // Create the Title
        TitleDTO titleDTO = titleMapper.toDto(title);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTitleMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(titleDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Title in the database
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTitle() throws Exception {
        // Initialize the database
        titleRepository.saveAndFlush(title);

        int databaseSizeBeforeDelete = titleRepository.findAll().size();

        // Delete the title
        restTitleMockMvc
            .perform(delete(ENTITY_API_URL_ID, title.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Title> titleList = titleRepository.findAll();
        assertThat(titleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
