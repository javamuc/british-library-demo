import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../topic-bl-comp.test-samples';

import { TopicBlCompFormService } from './topic-bl-comp-form.service';

describe('TopicBlComp Form Service', () => {
  let service: TopicBlCompFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TopicBlCompFormService);
  });

  describe('Service methods', () => {
    describe('createTopicBlCompFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTopicBlCompFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            topic: expect.any(Object),
            typeOfTopic: expect.any(Object),
            blRecordId: expect.any(Object),
            typeOfResource: expect.any(Object),
            contentType: expect.any(Object),
            materialType: expect.any(Object),
            bnbNumber: expect.any(Object),
            archivalResourceKey: expect.any(Object),
            isbn: expect.any(Object),
            name: expect.any(Object),
            datesAssociatedWithName: expect.any(Object),
            typeOfName: expect.any(Object),
            role: expect.any(Object),
            allNames: expect.any(Object),
            title: expect.any(Object),
            variantTitles: expect.any(Object),
            seriesTitle: expect.any(Object),
            numberWithinSeries: expect.any(Object),
            countryOfPublication: expect.any(Object),
            placeOfPublication: expect.any(Object),
            publisher: expect.any(Object),
            dateOfPublication: expect.any(Object),
            edition: expect.any(Object),
            physicalDescription: expect.any(Object),
            deweyClassification: expect.any(Object),
            blShelfmark: expect.any(Object),
            genre: expect.any(Object),
            languages: expect.any(Object),
            notes: expect.any(Object),
            provenance: expect.any(Object),
          })
        );
      });

      it('passing ITopicBlComp should create a new form with FormGroup', () => {
        const formGroup = service.createTopicBlCompFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            topic: expect.any(Object),
            typeOfTopic: expect.any(Object),
            blRecordId: expect.any(Object),
            typeOfResource: expect.any(Object),
            contentType: expect.any(Object),
            materialType: expect.any(Object),
            bnbNumber: expect.any(Object),
            archivalResourceKey: expect.any(Object),
            isbn: expect.any(Object),
            name: expect.any(Object),
            datesAssociatedWithName: expect.any(Object),
            typeOfName: expect.any(Object),
            role: expect.any(Object),
            allNames: expect.any(Object),
            title: expect.any(Object),
            variantTitles: expect.any(Object),
            seriesTitle: expect.any(Object),
            numberWithinSeries: expect.any(Object),
            countryOfPublication: expect.any(Object),
            placeOfPublication: expect.any(Object),
            publisher: expect.any(Object),
            dateOfPublication: expect.any(Object),
            edition: expect.any(Object),
            physicalDescription: expect.any(Object),
            deweyClassification: expect.any(Object),
            blShelfmark: expect.any(Object),
            genre: expect.any(Object),
            languages: expect.any(Object),
            notes: expect.any(Object),
            provenance: expect.any(Object),
          })
        );
      });
    });

    describe('getTopicBlComp', () => {
      it('should return NewTopicBlComp for default TopicBlComp initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTopicBlCompFormGroup(sampleWithNewData);

        const topic = service.getTopicBlComp(formGroup) as any;

        expect(topic).toMatchObject(sampleWithNewData);
      });

      it('should return NewTopicBlComp for empty TopicBlComp initial value', () => {
        const formGroup = service.createTopicBlCompFormGroup();

        const topic = service.getTopicBlComp(formGroup) as any;

        expect(topic).toMatchObject({});
      });

      it('should return ITopicBlComp', () => {
        const formGroup = service.createTopicBlCompFormGroup(sampleWithRequiredData);

        const topic = service.getTopicBlComp(formGroup) as any;

        expect(topic).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITopicBlComp should not enable id FormControl', () => {
        const formGroup = service.createTopicBlCompFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTopicBlComp should disable id FormControl', () => {
        const formGroup = service.createTopicBlCompFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
