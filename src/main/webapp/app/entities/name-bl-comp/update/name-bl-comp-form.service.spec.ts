import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../name-bl-comp.test-samples';

import { NameBlCompFormService } from './name-bl-comp-form.service';

describe('NameBlComp Form Service', () => {
  let service: NameBlCompFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NameBlCompFormService);
  });

  describe('Service methods', () => {
    describe('createNameBlCompFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createNameBlCompFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            datesAssociatedWithName: expect.any(Object),
            typeOfName: expect.any(Object),
            role: expect.any(Object),
            otherNames: expect.any(Object),
            blRecordId: expect.any(Object),
            typeOfResource: expect.any(Object),
            contentType: expect.any(Object),
            materialType: expect.any(Object),
            bnbNumber: expect.any(Object),
            archivalResourceKey: expect.any(Object),
            isbn: expect.any(Object),
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

      it('passing INameBlComp should create a new form with FormGroup', () => {
        const formGroup = service.createNameBlCompFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            datesAssociatedWithName: expect.any(Object),
            typeOfName: expect.any(Object),
            role: expect.any(Object),
            otherNames: expect.any(Object),
            blRecordId: expect.any(Object),
            typeOfResource: expect.any(Object),
            contentType: expect.any(Object),
            materialType: expect.any(Object),
            bnbNumber: expect.any(Object),
            archivalResourceKey: expect.any(Object),
            isbn: expect.any(Object),
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

    describe('getNameBlComp', () => {
      it('should return NewNameBlComp for default NameBlComp initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createNameBlCompFormGroup(sampleWithNewData);

        const name = service.getNameBlComp(formGroup) as any;

        expect(name).toMatchObject(sampleWithNewData);
      });

      it('should return NewNameBlComp for empty NameBlComp initial value', () => {
        const formGroup = service.createNameBlCompFormGroup();

        const name = service.getNameBlComp(formGroup) as any;

        expect(name).toMatchObject({});
      });

      it('should return INameBlComp', () => {
        const formGroup = service.createNameBlCompFormGroup(sampleWithRequiredData);

        const name = service.getNameBlComp(formGroup) as any;

        expect(name).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing INameBlComp should not enable id FormControl', () => {
        const formGroup = service.createNameBlCompFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewNameBlComp should disable id FormControl', () => {
        const formGroup = service.createNameBlCompFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
