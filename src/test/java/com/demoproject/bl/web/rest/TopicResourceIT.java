package com.demoproject.bl.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.demoproject.bl.IntegrationTest;
import com.demoproject.bl.domain.Topic;
import com.demoproject.bl.repository.TopicRepository;
import com.demoproject.bl.service.dto.TopicDTO;
import com.demoproject.bl.service.mapper.TopicMapper;
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
 * Integration tests for the {@link TopicResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TopicResourceIT {

    private static final String DEFAULT_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_TOPIC = "BBBBBBBBBB";

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

    private static final String DEFAULT_DEWEY_CLASSIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_DEWEY_CLASSIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_BL_SHELFMARK = "AAAAAAAAAA";
    private static final String UPDATED_BL_SHELFMARK = "BBBBBBBBBB";

    private static final String DEFAULT_GENRE = "AAAAAAAAAA";
    private static final String UPDATED_GENRE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGES = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGES = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_PROVENANCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVENANCE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/topics";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTopicMockMvc;

    private Topic topic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Topic createEntity(EntityManager em) {
        Topic topic = new Topic()
            .topic(DEFAULT_TOPIC)
            .typeOfTopic(DEFAULT_TYPE_OF_TOPIC)
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
            .deweyClassification(DEFAULT_DEWEY_CLASSIFICATION)
            .blShelfmark(DEFAULT_BL_SHELFMARK)
            .genre(DEFAULT_GENRE)
            .languages(DEFAULT_LANGUAGES)
            .notes(DEFAULT_NOTES)
            .provenance(DEFAULT_PROVENANCE);
        return topic;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Topic createUpdatedEntity(EntityManager em) {
        Topic topic = new Topic()
            .topic(UPDATED_TOPIC)
            .typeOfTopic(UPDATED_TYPE_OF_TOPIC)
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
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);
        return topic;
    }

    @BeforeEach
    public void initTest() {
        topic = createEntity(em);
    }

    @Test
    @Transactional
    void createTopic() throws Exception {
        int databaseSizeBeforeCreate = topicRepository.findAll().size();
        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);
        restTopicMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicDTO)))
            .andExpect(status().isCreated());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeCreate + 1);
        Topic testTopic = topicList.get(topicList.size() - 1);
        assertThat(testTopic.getTopic()).isEqualTo(DEFAULT_TOPIC);
        assertThat(testTopic.getTypeOfTopic()).isEqualTo(DEFAULT_TYPE_OF_TOPIC);
        assertThat(testTopic.getBlRecordId()).isEqualTo(DEFAULT_BL_RECORD_ID);
        assertThat(testTopic.getTypeOfResource()).isEqualTo(DEFAULT_TYPE_OF_RESOURCE);
        assertThat(testTopic.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testTopic.getMaterialType()).isEqualTo(DEFAULT_MATERIAL_TYPE);
        assertThat(testTopic.getBnbNumber()).isEqualTo(DEFAULT_BNB_NUMBER);
        assertThat(testTopic.getArchivalResourceKey()).isEqualTo(DEFAULT_ARCHIVAL_RESOURCE_KEY);
        assertThat(testTopic.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testTopic.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTopic.getDatesAssociatedWithName()).isEqualTo(DEFAULT_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testTopic.getTypeOfName()).isEqualTo(DEFAULT_TYPE_OF_NAME);
        assertThat(testTopic.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testTopic.getAllNames()).isEqualTo(DEFAULT_ALL_NAMES);
        assertThat(testTopic.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTopic.getVariantTitles()).isEqualTo(DEFAULT_VARIANT_TITLES);
        assertThat(testTopic.getSeriesTitle()).isEqualTo(DEFAULT_SERIES_TITLE);
        assertThat(testTopic.getNumberWithinSeries()).isEqualTo(DEFAULT_NUMBER_WITHIN_SERIES);
        assertThat(testTopic.getCountryOfPublication()).isEqualTo(DEFAULT_COUNTRY_OF_PUBLICATION);
        assertThat(testTopic.getPlaceOfPublication()).isEqualTo(DEFAULT_PLACE_OF_PUBLICATION);
        assertThat(testTopic.getPublisher()).isEqualTo(DEFAULT_PUBLISHER);
        assertThat(testTopic.getDateOfPublication()).isEqualTo(DEFAULT_DATE_OF_PUBLICATION);
        assertThat(testTopic.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testTopic.getPhysicalDescription()).isEqualTo(DEFAULT_PHYSICAL_DESCRIPTION);
        assertThat(testTopic.getDeweyClassification()).isEqualTo(DEFAULT_DEWEY_CLASSIFICATION);
        assertThat(testTopic.getBlShelfmark()).isEqualTo(DEFAULT_BL_SHELFMARK);
        assertThat(testTopic.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testTopic.getLanguages()).isEqualTo(DEFAULT_LANGUAGES);
        assertThat(testTopic.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testTopic.getProvenance()).isEqualTo(DEFAULT_PROVENANCE);
    }

    @Test
    @Transactional
    void createTopicWithExistingId() throws Exception {
        // Create the Topic with an existing ID
        topic.setId(1L);
        TopicDTO topicDTO = topicMapper.toDto(topic);

        int databaseSizeBeforeCreate = topicRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTopicMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTopics() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        // Get all the topicList
        restTopicMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(topic.getId().intValue())))
            .andExpect(jsonPath("$.[*].topic").value(hasItem(DEFAULT_TOPIC)))
            .andExpect(jsonPath("$.[*].typeOfTopic").value(hasItem(DEFAULT_TYPE_OF_TOPIC)))
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
            .andExpect(jsonPath("$.[*].deweyClassification").value(hasItem(DEFAULT_DEWEY_CLASSIFICATION)))
            .andExpect(jsonPath("$.[*].blShelfmark").value(hasItem(DEFAULT_BL_SHELFMARK)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].languages").value(hasItem(DEFAULT_LANGUAGES)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].provenance").value(hasItem(DEFAULT_PROVENANCE)));
    }

    @Test
    @Transactional
    void getTopic() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        // Get the topic
        restTopicMockMvc
            .perform(get(ENTITY_API_URL_ID, topic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(topic.getId().intValue()))
            .andExpect(jsonPath("$.topic").value(DEFAULT_TOPIC))
            .andExpect(jsonPath("$.typeOfTopic").value(DEFAULT_TYPE_OF_TOPIC))
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
            .andExpect(jsonPath("$.deweyClassification").value(DEFAULT_DEWEY_CLASSIFICATION))
            .andExpect(jsonPath("$.blShelfmark").value(DEFAULT_BL_SHELFMARK))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE))
            .andExpect(jsonPath("$.languages").value(DEFAULT_LANGUAGES))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.provenance").value(DEFAULT_PROVENANCE));
    }

    @Test
    @Transactional
    void getNonExistingTopic() throws Exception {
        // Get the topic
        restTopicMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTopic() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        int databaseSizeBeforeUpdate = topicRepository.findAll().size();

        // Update the topic
        Topic updatedTopic = topicRepository.findById(topic.getId()).get();
        // Disconnect from session so that the updates on updatedTopic are not directly saved in db
        em.detach(updatedTopic);
        updatedTopic
            .topic(UPDATED_TOPIC)
            .typeOfTopic(UPDATED_TYPE_OF_TOPIC)
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
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);
        TopicDTO topicDTO = topicMapper.toDto(updatedTopic);

        restTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, topicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(topicDTO))
            )
            .andExpect(status().isOk());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
        Topic testTopic = topicList.get(topicList.size() - 1);
        assertThat(testTopic.getTopic()).isEqualTo(UPDATED_TOPIC);
        assertThat(testTopic.getTypeOfTopic()).isEqualTo(UPDATED_TYPE_OF_TOPIC);
        assertThat(testTopic.getBlRecordId()).isEqualTo(UPDATED_BL_RECORD_ID);
        assertThat(testTopic.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testTopic.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testTopic.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testTopic.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testTopic.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testTopic.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testTopic.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTopic.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testTopic.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testTopic.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testTopic.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testTopic.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTopic.getVariantTitles()).isEqualTo(UPDATED_VARIANT_TITLES);
        assertThat(testTopic.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testTopic.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testTopic.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testTopic.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testTopic.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testTopic.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testTopic.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testTopic.getPhysicalDescription()).isEqualTo(UPDATED_PHYSICAL_DESCRIPTION);
        assertThat(testTopic.getDeweyClassification()).isEqualTo(UPDATED_DEWEY_CLASSIFICATION);
        assertThat(testTopic.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testTopic.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testTopic.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testTopic.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testTopic.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void putNonExistingTopic() throws Exception {
        int databaseSizeBeforeUpdate = topicRepository.findAll().size();
        topic.setId(count.incrementAndGet());

        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, topicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(topicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTopic() throws Exception {
        int databaseSizeBeforeUpdate = topicRepository.findAll().size();
        topic.setId(count.incrementAndGet());

        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(topicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTopic() throws Exception {
        int databaseSizeBeforeUpdate = topicRepository.findAll().size();
        topic.setId(count.incrementAndGet());

        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTopicMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(topicDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTopicWithPatch() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        int databaseSizeBeforeUpdate = topicRepository.findAll().size();

        // Update the topic using partial update
        Topic partialUpdatedTopic = new Topic();
        partialUpdatedTopic.setId(topic.getId());

        partialUpdatedTopic
            .contentType(UPDATED_CONTENT_TYPE)
            .bnbNumber(UPDATED_BNB_NUMBER)
            .archivalResourceKey(UPDATED_ARCHIVAL_RESOURCE_KEY)
            .datesAssociatedWithName(UPDATED_DATES_ASSOCIATED_WITH_NAME)
            .typeOfName(UPDATED_TYPE_OF_NAME)
            .role(UPDATED_ROLE)
            .allNames(UPDATED_ALL_NAMES)
            .title(UPDATED_TITLE)
            .numberWithinSeries(UPDATED_NUMBER_WITHIN_SERIES)
            .countryOfPublication(UPDATED_COUNTRY_OF_PUBLICATION)
            .edition(UPDATED_EDITION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES);

        restTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTopic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTopic))
            )
            .andExpect(status().isOk());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
        Topic testTopic = topicList.get(topicList.size() - 1);
        assertThat(testTopic.getTopic()).isEqualTo(DEFAULT_TOPIC);
        assertThat(testTopic.getTypeOfTopic()).isEqualTo(DEFAULT_TYPE_OF_TOPIC);
        assertThat(testTopic.getBlRecordId()).isEqualTo(DEFAULT_BL_RECORD_ID);
        assertThat(testTopic.getTypeOfResource()).isEqualTo(DEFAULT_TYPE_OF_RESOURCE);
        assertThat(testTopic.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testTopic.getMaterialType()).isEqualTo(DEFAULT_MATERIAL_TYPE);
        assertThat(testTopic.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testTopic.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testTopic.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testTopic.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTopic.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testTopic.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testTopic.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testTopic.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testTopic.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTopic.getVariantTitles()).isEqualTo(DEFAULT_VARIANT_TITLES);
        assertThat(testTopic.getSeriesTitle()).isEqualTo(DEFAULT_SERIES_TITLE);
        assertThat(testTopic.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testTopic.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testTopic.getPlaceOfPublication()).isEqualTo(DEFAULT_PLACE_OF_PUBLICATION);
        assertThat(testTopic.getPublisher()).isEqualTo(DEFAULT_PUBLISHER);
        assertThat(testTopic.getDateOfPublication()).isEqualTo(DEFAULT_DATE_OF_PUBLICATION);
        assertThat(testTopic.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testTopic.getPhysicalDescription()).isEqualTo(DEFAULT_PHYSICAL_DESCRIPTION);
        assertThat(testTopic.getDeweyClassification()).isEqualTo(DEFAULT_DEWEY_CLASSIFICATION);
        assertThat(testTopic.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testTopic.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testTopic.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testTopic.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testTopic.getProvenance()).isEqualTo(DEFAULT_PROVENANCE);
    }

    @Test
    @Transactional
    void fullUpdateTopicWithPatch() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        int databaseSizeBeforeUpdate = topicRepository.findAll().size();

        // Update the topic using partial update
        Topic partialUpdatedTopic = new Topic();
        partialUpdatedTopic.setId(topic.getId());

        partialUpdatedTopic
            .topic(UPDATED_TOPIC)
            .typeOfTopic(UPDATED_TYPE_OF_TOPIC)
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
            .deweyClassification(UPDATED_DEWEY_CLASSIFICATION)
            .blShelfmark(UPDATED_BL_SHELFMARK)
            .genre(UPDATED_GENRE)
            .languages(UPDATED_LANGUAGES)
            .notes(UPDATED_NOTES)
            .provenance(UPDATED_PROVENANCE);

        restTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTopic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTopic))
            )
            .andExpect(status().isOk());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
        Topic testTopic = topicList.get(topicList.size() - 1);
        assertThat(testTopic.getTopic()).isEqualTo(UPDATED_TOPIC);
        assertThat(testTopic.getTypeOfTopic()).isEqualTo(UPDATED_TYPE_OF_TOPIC);
        assertThat(testTopic.getBlRecordId()).isEqualTo(UPDATED_BL_RECORD_ID);
        assertThat(testTopic.getTypeOfResource()).isEqualTo(UPDATED_TYPE_OF_RESOURCE);
        assertThat(testTopic.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testTopic.getMaterialType()).isEqualTo(UPDATED_MATERIAL_TYPE);
        assertThat(testTopic.getBnbNumber()).isEqualTo(UPDATED_BNB_NUMBER);
        assertThat(testTopic.getArchivalResourceKey()).isEqualTo(UPDATED_ARCHIVAL_RESOURCE_KEY);
        assertThat(testTopic.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testTopic.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTopic.getDatesAssociatedWithName()).isEqualTo(UPDATED_DATES_ASSOCIATED_WITH_NAME);
        assertThat(testTopic.getTypeOfName()).isEqualTo(UPDATED_TYPE_OF_NAME);
        assertThat(testTopic.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testTopic.getAllNames()).isEqualTo(UPDATED_ALL_NAMES);
        assertThat(testTopic.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTopic.getVariantTitles()).isEqualTo(UPDATED_VARIANT_TITLES);
        assertThat(testTopic.getSeriesTitle()).isEqualTo(UPDATED_SERIES_TITLE);
        assertThat(testTopic.getNumberWithinSeries()).isEqualTo(UPDATED_NUMBER_WITHIN_SERIES);
        assertThat(testTopic.getCountryOfPublication()).isEqualTo(UPDATED_COUNTRY_OF_PUBLICATION);
        assertThat(testTopic.getPlaceOfPublication()).isEqualTo(UPDATED_PLACE_OF_PUBLICATION);
        assertThat(testTopic.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testTopic.getDateOfPublication()).isEqualTo(UPDATED_DATE_OF_PUBLICATION);
        assertThat(testTopic.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testTopic.getPhysicalDescription()).isEqualTo(UPDATED_PHYSICAL_DESCRIPTION);
        assertThat(testTopic.getDeweyClassification()).isEqualTo(UPDATED_DEWEY_CLASSIFICATION);
        assertThat(testTopic.getBlShelfmark()).isEqualTo(UPDATED_BL_SHELFMARK);
        assertThat(testTopic.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testTopic.getLanguages()).isEqualTo(UPDATED_LANGUAGES);
        assertThat(testTopic.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testTopic.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
    }

    @Test
    @Transactional
    void patchNonExistingTopic() throws Exception {
        int databaseSizeBeforeUpdate = topicRepository.findAll().size();
        topic.setId(count.incrementAndGet());

        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, topicDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(topicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTopic() throws Exception {
        int databaseSizeBeforeUpdate = topicRepository.findAll().size();
        topic.setId(count.incrementAndGet());

        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(topicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTopic() throws Exception {
        int databaseSizeBeforeUpdate = topicRepository.findAll().size();
        topic.setId(count.incrementAndGet());

        // Create the Topic
        TopicDTO topicDTO = topicMapper.toDto(topic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTopicMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(topicDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Topic in the database
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTopic() throws Exception {
        // Initialize the database
        topicRepository.saveAndFlush(topic);

        int databaseSizeBeforeDelete = topicRepository.findAll().size();

        // Delete the topic
        restTopicMockMvc
            .perform(delete(ENTITY_API_URL_ID, topic.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Topic> topicList = topicRepository.findAll();
        assertThat(topicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
