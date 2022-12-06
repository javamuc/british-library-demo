import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IClassificationBlComp } from '../classification-bl-comp.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../classification-bl-comp.test-samples';

import { ClassificationBlCompService } from './classification-bl-comp.service';

const requireRestSample: IClassificationBlComp = {
  ...sampleWithRequiredData,
};

describe('ClassificationBlComp Service', () => {
  let service: ClassificationBlCompService;
  let httpMock: HttpTestingController;
  let expectedResult: IClassificationBlComp | IClassificationBlComp[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ClassificationBlCompService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a ClassificationBlComp', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const classification = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(classification).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ClassificationBlComp', () => {
      const classification = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(classification).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ClassificationBlComp', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ClassificationBlComp', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ClassificationBlComp', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addClassificationBlCompToCollectionIfMissing', () => {
      it('should add a ClassificationBlComp to an empty array', () => {
        const classification: IClassificationBlComp = sampleWithRequiredData;
        expectedResult = service.addClassificationBlCompToCollectionIfMissing([], classification);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(classification);
      });

      it('should not add a ClassificationBlComp to an array that contains it', () => {
        const classification: IClassificationBlComp = sampleWithRequiredData;
        const classificationCollection: IClassificationBlComp[] = [
          {
            ...classification,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addClassificationBlCompToCollectionIfMissing(classificationCollection, classification);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ClassificationBlComp to an array that doesn't contain it", () => {
        const classification: IClassificationBlComp = sampleWithRequiredData;
        const classificationCollection: IClassificationBlComp[] = [sampleWithPartialData];
        expectedResult = service.addClassificationBlCompToCollectionIfMissing(classificationCollection, classification);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(classification);
      });

      it('should add only unique ClassificationBlComp to an array', () => {
        const classificationArray: IClassificationBlComp[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const classificationCollection: IClassificationBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addClassificationBlCompToCollectionIfMissing(classificationCollection, ...classificationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const classification: IClassificationBlComp = sampleWithRequiredData;
        const classification2: IClassificationBlComp = sampleWithPartialData;
        expectedResult = service.addClassificationBlCompToCollectionIfMissing([], classification, classification2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(classification);
        expect(expectedResult).toContain(classification2);
      });

      it('should accept null and undefined values', () => {
        const classification: IClassificationBlComp = sampleWithRequiredData;
        expectedResult = service.addClassificationBlCompToCollectionIfMissing([], null, classification, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(classification);
      });

      it('should return initial array if no ClassificationBlComp is added', () => {
        const classificationCollection: IClassificationBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addClassificationBlCompToCollectionIfMissing(classificationCollection, undefined, null);
        expect(expectedResult).toEqual(classificationCollection);
      });
    });

    describe('compareClassificationBlComp', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareClassificationBlComp(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareClassificationBlComp(entity1, entity2);
        const compareResult2 = service.compareClassificationBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareClassificationBlComp(entity1, entity2);
        const compareResult2 = service.compareClassificationBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareClassificationBlComp(entity1, entity2);
        const compareResult2 = service.compareClassificationBlComp(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
