package com.demoproject.bl.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Classification.
 */
@Entity
@Table(name = "classification")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Classification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "dewey_classification")
    private String deweyClassification;

    @Column(name = "bl_record_id")
    private String blRecordId;

    @Column(name = "type_of_resource")
    private String typeOfResource;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "material_type")
    private String materialType;

    @Column(name = "bnb_number")
    private String bnbNumber;

    @Column(name = "archival_resource_key")
    private String archivalResourceKey;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "dates_associated_with_name")
    private String datesAssociatedWithName;

    @Column(name = "type_of_name")
    private String typeOfName;

    @Column(name = "role")
    private String role;

    @Column(name = "all_names")
    private String allNames;

    @Column(name = "title")
    private String title;

    @Column(name = "variant_titles")
    private String variantTitles;

    @Column(name = "series_title")
    private String seriesTitle;

    @Column(name = "number_within_series")
    private String numberWithinSeries;

    @Column(name = "country_of_publication")
    private String countryOfPublication;

    @Column(name = "place_of_publication")
    private String placeOfPublication;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "date_of_publication")
    private String dateOfPublication;

    @Column(name = "edition")
    private String edition;

    @Column(name = "physical_description")
    private String physicalDescription;

    @Column(name = "bl_shelfmark")
    private String blShelfmark;

    @Column(name = "topics")
    private String topics;

    @Column(name = "genre")
    private String genre;

    @Column(name = "languages")
    private String languages;

    @Column(name = "notes")
    private String notes;

    @Column(name = "provenance")
    private String provenance;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Classification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeweyClassification() {
        return this.deweyClassification;
    }

    public Classification deweyClassification(String deweyClassification) {
        this.setDeweyClassification(deweyClassification);
        return this;
    }

    public void setDeweyClassification(String deweyClassification) {
        this.deweyClassification = deweyClassification;
    }

    public String getBlRecordId() {
        return this.blRecordId;
    }

    public Classification blRecordId(String blRecordId) {
        this.setBlRecordId(blRecordId);
        return this;
    }

    public void setBlRecordId(String blRecordId) {
        this.blRecordId = blRecordId;
    }

    public String getTypeOfResource() {
        return this.typeOfResource;
    }

    public Classification typeOfResource(String typeOfResource) {
        this.setTypeOfResource(typeOfResource);
        return this;
    }

    public void setTypeOfResource(String typeOfResource) {
        this.typeOfResource = typeOfResource;
    }

    public String getContentType() {
        return this.contentType;
    }

    public Classification contentType(String contentType) {
        this.setContentType(contentType);
        return this;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMaterialType() {
        return this.materialType;
    }

    public Classification materialType(String materialType) {
        this.setMaterialType(materialType);
        return this;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getBnbNumber() {
        return this.bnbNumber;
    }

    public Classification bnbNumber(String bnbNumber) {
        this.setBnbNumber(bnbNumber);
        return this;
    }

    public void setBnbNumber(String bnbNumber) {
        this.bnbNumber = bnbNumber;
    }

    public String getArchivalResourceKey() {
        return this.archivalResourceKey;
    }

    public Classification archivalResourceKey(String archivalResourceKey) {
        this.setArchivalResourceKey(archivalResourceKey);
        return this;
    }

    public void setArchivalResourceKey(String archivalResourceKey) {
        this.archivalResourceKey = archivalResourceKey;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Classification isbn(String isbn) {
        this.setIsbn(isbn);
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return this.name;
    }

    public Classification name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatesAssociatedWithName() {
        return this.datesAssociatedWithName;
    }

    public Classification datesAssociatedWithName(String datesAssociatedWithName) {
        this.setDatesAssociatedWithName(datesAssociatedWithName);
        return this;
    }

    public void setDatesAssociatedWithName(String datesAssociatedWithName) {
        this.datesAssociatedWithName = datesAssociatedWithName;
    }

    public String getTypeOfName() {
        return this.typeOfName;
    }

    public Classification typeOfName(String typeOfName) {
        this.setTypeOfName(typeOfName);
        return this;
    }

    public void setTypeOfName(String typeOfName) {
        this.typeOfName = typeOfName;
    }

    public String getRole() {
        return this.role;
    }

    public Classification role(String role) {
        this.setRole(role);
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAllNames() {
        return this.allNames;
    }

    public Classification allNames(String allNames) {
        this.setAllNames(allNames);
        return this;
    }

    public void setAllNames(String allNames) {
        this.allNames = allNames;
    }

    public String getTitle() {
        return this.title;
    }

    public Classification title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVariantTitles() {
        return this.variantTitles;
    }

    public Classification variantTitles(String variantTitles) {
        this.setVariantTitles(variantTitles);
        return this;
    }

    public void setVariantTitles(String variantTitles) {
        this.variantTitles = variantTitles;
    }

    public String getSeriesTitle() {
        return this.seriesTitle;
    }

    public Classification seriesTitle(String seriesTitle) {
        this.setSeriesTitle(seriesTitle);
        return this;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getNumberWithinSeries() {
        return this.numberWithinSeries;
    }

    public Classification numberWithinSeries(String numberWithinSeries) {
        this.setNumberWithinSeries(numberWithinSeries);
        return this;
    }

    public void setNumberWithinSeries(String numberWithinSeries) {
        this.numberWithinSeries = numberWithinSeries;
    }

    public String getCountryOfPublication() {
        return this.countryOfPublication;
    }

    public Classification countryOfPublication(String countryOfPublication) {
        this.setCountryOfPublication(countryOfPublication);
        return this;
    }

    public void setCountryOfPublication(String countryOfPublication) {
        this.countryOfPublication = countryOfPublication;
    }

    public String getPlaceOfPublication() {
        return this.placeOfPublication;
    }

    public Classification placeOfPublication(String placeOfPublication) {
        this.setPlaceOfPublication(placeOfPublication);
        return this;
    }

    public void setPlaceOfPublication(String placeOfPublication) {
        this.placeOfPublication = placeOfPublication;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public Classification publisher(String publisher) {
        this.setPublisher(publisher);
        return this;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDateOfPublication() {
        return this.dateOfPublication;
    }

    public Classification dateOfPublication(String dateOfPublication) {
        this.setDateOfPublication(dateOfPublication);
        return this;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getEdition() {
        return this.edition;
    }

    public Classification edition(String edition) {
        this.setEdition(edition);
        return this;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPhysicalDescription() {
        return this.physicalDescription;
    }

    public Classification physicalDescription(String physicalDescription) {
        this.setPhysicalDescription(physicalDescription);
        return this;
    }

    public void setPhysicalDescription(String physicalDescription) {
        this.physicalDescription = physicalDescription;
    }

    public String getBlShelfmark() {
        return this.blShelfmark;
    }

    public Classification blShelfmark(String blShelfmark) {
        this.setBlShelfmark(blShelfmark);
        return this;
    }

    public void setBlShelfmark(String blShelfmark) {
        this.blShelfmark = blShelfmark;
    }

    public String getTopics() {
        return this.topics;
    }

    public Classification topics(String topics) {
        this.setTopics(topics);
        return this;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getGenre() {
        return this.genre;
    }

    public Classification genre(String genre) {
        this.setGenre(genre);
        return this;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguages() {
        return this.languages;
    }

    public Classification languages(String languages) {
        this.setLanguages(languages);
        return this;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getNotes() {
        return this.notes;
    }

    public Classification notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProvenance() {
        return this.provenance;
    }

    public Classification provenance(String provenance) {
        this.setProvenance(provenance);
        return this;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Classification)) {
            return false;
        }
        return id != null && id.equals(((Classification) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Classification{" +
            "id=" + getId() +
            ", deweyClassification='" + getDeweyClassification() + "'" +
            ", blRecordId='" + getBlRecordId() + "'" +
            ", typeOfResource='" + getTypeOfResource() + "'" +
            ", contentType='" + getContentType() + "'" +
            ", materialType='" + getMaterialType() + "'" +
            ", bnbNumber='" + getBnbNumber() + "'" +
            ", archivalResourceKey='" + getArchivalResourceKey() + "'" +
            ", isbn='" + getIsbn() + "'" +
            ", name='" + getName() + "'" +
            ", datesAssociatedWithName='" + getDatesAssociatedWithName() + "'" +
            ", typeOfName='" + getTypeOfName() + "'" +
            ", role='" + getRole() + "'" +
            ", allNames='" + getAllNames() + "'" +
            ", title='" + getTitle() + "'" +
            ", variantTitles='" + getVariantTitles() + "'" +
            ", seriesTitle='" + getSeriesTitle() + "'" +
            ", numberWithinSeries='" + getNumberWithinSeries() + "'" +
            ", countryOfPublication='" + getCountryOfPublication() + "'" +
            ", placeOfPublication='" + getPlaceOfPublication() + "'" +
            ", publisher='" + getPublisher() + "'" +
            ", dateOfPublication='" + getDateOfPublication() + "'" +
            ", edition='" + getEdition() + "'" +
            ", physicalDescription='" + getPhysicalDescription() + "'" +
            ", blShelfmark='" + getBlShelfmark() + "'" +
            ", topics='" + getTopics() + "'" +
            ", genre='" + getGenre() + "'" +
            ", languages='" + getLanguages() + "'" +
            ", notes='" + getNotes() + "'" +
            ", provenance='" + getProvenance() + "'" +
            "}";
    }
}
