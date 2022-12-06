import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../title-bl-comp.test-samples';

import { TitleBlCompFormService } from './title-bl-comp-form.service';

describe('TitleBlComp Form Service', () => {
  let service: TitleBlCompFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TitleBlCompFormService);
  });

  describe('Service methods', () => {
    describe('createTitleBlCompFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTitleBlCompFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            otherTitles: expect.any(Object),
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
            topics: expect.any(Object),
            genre: expect.any(Object),
            languages: expect.any(Object),
            notes: expect.any(Object),
            provenance: expect.any(Object),
          })
        );
      });

      it('passing ITitleBlComp should create a new form with FormGroup', () => {
        const formGroup = service.createTitleBlCompFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            otherTitles: expect.any(Object),
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
            topics: expect.any(Object),
            genre: expect.any(Object),
            languages: expect.any(Object),
            notes: expect.any(Object),
            provenance: expect.any(Object),
          })
        );
      });
    });

    describe('getTitleBlComp', () => {
      it('should return NewTitleBlComp for default TitleBlComp initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTitleBlCompFormGroup(sampleWithNewData);

        const title = service.getTitleBlComp(formGroup) as any;

        expect(title).toMatchObject(sampleWithNewData);
      });

      it('should return NewTitleBlComp for empty TitleBlComp initial value', () => {
        const formGroup = service.createTitleBlCompFormGroup();

        const title = service.getTitleBlComp(formGroup) as any;

        expect(title).toMatchObject({});
      });

      it('should return ITitleBlComp', () => {
        const formGroup = service.createTitleBlCompFormGroup(sampleWithRequiredData);

        const title = service.getTitleBlComp(formGroup) as any;

        expect(title).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITitleBlComp should not enable id FormControl', () => {
        const formGroup = service.createTitleBlCompFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTitleBlComp should disable id FormControl', () => {
        const formGroup = service.createTitleBlCompFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
