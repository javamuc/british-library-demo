import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IRecordBlComp } from '../record-bl-comp.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../record-bl-comp.test-samples';

import { RecordBlCompService } from './record-bl-comp.service';

const requireRestSample: IRecordBlComp = {
  ...sampleWithRequiredData,
};

describe('RecordBlComp Service', () => {
  let service: RecordBlCompService;
  let httpMock: HttpTestingController;
  let expectedResult: IRecordBlComp | IRecordBlComp[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RecordBlCompService);
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

    it('should create a RecordBlComp', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const record = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(record).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RecordBlComp', () => {
      const record = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(record).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RecordBlComp', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RecordBlComp', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RecordBlComp', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRecordBlCompToCollectionIfMissing', () => {
      it('should add a RecordBlComp to an empty array', () => {
        const record: IRecordBlComp = sampleWithRequiredData;
        expectedResult = service.addRecordBlCompToCollectionIfMissing([], record);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(record);
      });

      it('should not add a RecordBlComp to an array that contains it', () => {
        const record: IRecordBlComp = sampleWithRequiredData;
        const recordCollection: IRecordBlComp[] = [
          {
            ...record,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRecordBlCompToCollectionIfMissing(recordCollection, record);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RecordBlComp to an array that doesn't contain it", () => {
        const record: IRecordBlComp = sampleWithRequiredData;
        const recordCollection: IRecordBlComp[] = [sampleWithPartialData];
        expectedResult = service.addRecordBlCompToCollectionIfMissing(recordCollection, record);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(record);
      });

      it('should add only unique RecordBlComp to an array', () => {
        const recordArray: IRecordBlComp[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const recordCollection: IRecordBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addRecordBlCompToCollectionIfMissing(recordCollection, ...recordArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const record: IRecordBlComp = sampleWithRequiredData;
        const record2: IRecordBlComp = sampleWithPartialData;
        expectedResult = service.addRecordBlCompToCollectionIfMissing([], record, record2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(record);
        expect(expectedResult).toContain(record2);
      });

      it('should accept null and undefined values', () => {
        const record: IRecordBlComp = sampleWithRequiredData;
        expectedResult = service.addRecordBlCompToCollectionIfMissing([], null, record, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(record);
      });

      it('should return initial array if no RecordBlComp is added', () => {
        const recordCollection: IRecordBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addRecordBlCompToCollectionIfMissing(recordCollection, undefined, null);
        expect(expectedResult).toEqual(recordCollection);
      });
    });

    describe('compareRecordBlComp', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRecordBlComp(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareRecordBlComp(entity1, entity2);
        const compareResult2 = service.compareRecordBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareRecordBlComp(entity1, entity2);
        const compareResult2 = service.compareRecordBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareRecordBlComp(entity1, entity2);
        const compareResult2 = service.compareRecordBlComp(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
