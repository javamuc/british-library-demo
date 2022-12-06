import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../record-bl-comp.test-samples';

import { RecordBlCompFormService } from './record-bl-comp-form.service';

describe('RecordBlComp Form Service', () => {
  let service: RecordBlCompFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecordBlCompFormService);
  });

  describe('Service methods', () => {
    describe('createRecordBlCompFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRecordBlCompFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
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
            topics: expect.any(Object),
            genre: expect.any(Object),
            languages: expect.any(Object),
            notes: expect.any(Object),
            provenance: expect.any(Object),
          })
        );
      });

      it('passing IRecordBlComp should create a new form with FormGroup', () => {
        const formGroup = service.createRecordBlCompFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
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
            topics: expect.any(Object),
            genre: expect.any(Object),
            languages: expect.any(Object),
            notes: expect.any(Object),
            provenance: expect.any(Object),
          })
        );
      });
    });

    describe('getRecordBlComp', () => {
      it('should return NewRecordBlComp for default RecordBlComp initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRecordBlCompFormGroup(sampleWithNewData);

        const record = service.getRecordBlComp(formGroup) as any;

        expect(record).toMatchObject(sampleWithNewData);
      });

      it('should return NewRecordBlComp for empty RecordBlComp initial value', () => {
        const formGroup = service.createRecordBlCompFormGroup();

        const record = service.getRecordBlComp(formGroup) as any;

        expect(record).toMatchObject({});
      });

      it('should return IRecordBlComp', () => {
        const formGroup = service.createRecordBlCompFormGroup(sampleWithRequiredData);

        const record = service.getRecordBlComp(formGroup) as any;

        expect(record).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRecordBlComp should not enable id FormControl', () => {
        const formGroup = service.createRecordBlCompFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRecordBlComp should disable id FormControl', () => {
        const formGroup = service.createRecordBlCompFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
