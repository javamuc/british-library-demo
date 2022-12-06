import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../classification-bl-comp.test-samples';

import { ClassificationBlCompFormService } from './classification-bl-comp-form.service';

describe('ClassificationBlComp Form Service', () => {
  let service: ClassificationBlCompFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClassificationBlCompFormService);
  });

  describe('Service methods', () => {
    describe('createClassificationBlCompFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createClassificationBlCompFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deweyClassification: expect.any(Object),
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
            blShelfmark: expect.any(Object),
            topics: expect.any(Object),
            genre: expect.any(Object),
            languages: expect.any(Object),
            notes: expect.any(Object),
            provenance: expect.any(Object),
          })
        );
      });

      it('passing IClassificationBlComp should create a new form with FormGroup', () => {
        const formGroup = service.createClassificationBlCompFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deweyClassification: expect.any(Object),
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
            blShelfmark: expect.any(Object),
            topics: expect.any(Object),
            genre: expect.any(Object),
            languages: expect.any(Object),
            notes: expect.any(Object),
            provenance: expect.any(Object),
          })
        );
      });
    });

    describe('getClassificationBlComp', () => {
      it('should return NewClassificationBlComp for default ClassificationBlComp initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createClassificationBlCompFormGroup(sampleWithNewData);

        const classification = service.getClassificationBlComp(formGroup) as any;

        expect(classification).toMatchObject(sampleWithNewData);
      });

      it('should return NewClassificationBlComp for empty ClassificationBlComp initial value', () => {
        const formGroup = service.createClassificationBlCompFormGroup();

        const classification = service.getClassificationBlComp(formGroup) as any;

        expect(classification).toMatchObject({});
      });

      it('should return IClassificationBlComp', () => {
        const formGroup = service.createClassificationBlCompFormGroup(sampleWithRequiredData);

        const classification = service.getClassificationBlComp(formGroup) as any;

        expect(classification).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IClassificationBlComp should not enable id FormControl', () => {
        const formGroup = service.createClassificationBlCompFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewClassificationBlComp should disable id FormControl', () => {
        const formGroup = service.createClassificationBlCompFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
