package com.demoproject.bl.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.demoproject.bl.domain.Topic} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TopicDTO implements Serializable {

    private Long id;

    private String topic;

    private String typeOfTopic;

    private String blRecordId;

    private String typeOfResource;

    private String contentType;

    private String materialType;

    private String bnbNumber;

    private String archivalResourceKey;

    private String isbn;

    private String name;

    private String datesAssociatedWithName;

    private String typeOfName;

    private String role;

    private String allNames;

    private String title;

    private String variantTitles;

    private String seriesTitle;

    private String numberWithinSeries;

    private String countryOfPublication;

    private String placeOfPublication;

    private String publisher;

    private String dateOfPublication;

    private String edition;

    private String physicalDescription;

    private String deweyClassification;

    private String blShelfmark;

    private String genre;

    private String languages;

    private String notes;

    private String provenance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTypeOfTopic() {
        return typeOfTopic;
    }

    public void setTypeOfTopic(String typeOfTopic) {
        this.typeOfTopic = typeOfTopic;
    }

    public String getBlRecordId() {
        return blRecordId;
    }

    public void setBlRecordId(String blRecordId) {
        this.blRecordId = blRecordId;
    }

    public String getTypeOfResource() {
        return typeOfResource;
    }

    public void setTypeOfResource(String typeOfResource) {
        this.typeOfResource = typeOfResource;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getBnbNumber() {
        return bnbNumber;
    }

    public void setBnbNumber(String bnbNumber) {
        this.bnbNumber = bnbNumber;
    }

    public String getArchivalResourceKey() {
        return archivalResourceKey;
    }

    public void setArchivalResourceKey(String archivalResourceKey) {
        this.archivalResourceKey = archivalResourceKey;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatesAssociatedWithName() {
        return datesAssociatedWithName;
    }

    public void setDatesAssociatedWithName(String datesAssociatedWithName) {
        this.datesAssociatedWithName = datesAssociatedWithName;
    }

    public String getTypeOfName() {
        return typeOfName;
    }

    public void setTypeOfName(String typeOfName) {
        this.typeOfName = typeOfName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAllNames() {
        return allNames;
    }

    public void setAllNames(String allNames) {
        this.allNames = allNames;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVariantTitles() {
        return variantTitles;
    }

    public void setVariantTitles(String variantTitles) {
        this.variantTitles = variantTitles;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getNumberWithinSeries() {
        return numberWithinSeries;
    }

    public void setNumberWithinSeries(String numberWithinSeries) {
        this.numberWithinSeries = numberWithinSeries;
    }

    public String getCountryOfPublication() {
        return countryOfPublication;
    }

    public void setCountryOfPublication(String countryOfPublication) {
        this.countryOfPublication = countryOfPublication;
    }

    public String getPlaceOfPublication() {
        return placeOfPublication;
    }

    public void setPlaceOfPublication(String placeOfPublication) {
        this.placeOfPublication = placeOfPublication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPhysicalDescription() {
        return physicalDescription;
    }

    public void setPhysicalDescription(String physicalDescription) {
        this.physicalDescription = physicalDescription;
    }

    public String getDeweyClassification() {
        return deweyClassification;
    }

    public void setDeweyClassification(String deweyClassification) {
        this.deweyClassification = deweyClassification;
    }

    public String getBlShelfmark() {
        return blShelfmark;
    }

    public void setBlShelfmark(String blShelfmark) {
        this.blShelfmark = blShelfmark;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TopicDTO)) {
            return false;
        }

        TopicDTO topicDTO = (TopicDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, topicDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TopicDTO{" +
            "id=" + getId() +
            ", topic='" + getTopic() + "'" +
            ", typeOfTopic='" + getTypeOfTopic() + "'" +
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
            ", deweyClassification='" + getDeweyClassification() + "'" +
            ", blShelfmark='" + getBlShelfmark() + "'" +
            ", genre='" + getGenre() + "'" +
            ", languages='" + getLanguages() + "'" +
            ", notes='" + getNotes() + "'" +
            ", provenance='" + getProvenance() + "'" +
            "}";
    }
}
