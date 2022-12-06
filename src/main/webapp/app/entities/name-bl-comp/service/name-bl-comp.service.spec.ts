import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { INameBlComp } from '../name-bl-comp.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../name-bl-comp.test-samples';

import { NameBlCompService } from './name-bl-comp.service';

const requireRestSample: INameBlComp = {
  ...sampleWithRequiredData,
};

describe('NameBlComp Service', () => {
  let service: NameBlCompService;
  let httpMock: HttpTestingController;
  let expectedResult: INameBlComp | INameBlComp[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(NameBlCompService);
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

    it('should create a NameBlComp', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const name = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(name).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a NameBlComp', () => {
      const name = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(name).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a NameBlComp', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of NameBlComp', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a NameBlComp', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addNameBlCompToCollectionIfMissing', () => {
      it('should add a NameBlComp to an empty array', () => {
        const name: INameBlComp = sampleWithRequiredData;
        expectedResult = service.addNameBlCompToCollectionIfMissing([], name);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(name);
      });

      it('should not add a NameBlComp to an array that contains it', () => {
        const name: INameBlComp = sampleWithRequiredData;
        const nameCollection: INameBlComp[] = [
          {
            ...name,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addNameBlCompToCollectionIfMissing(nameCollection, name);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a NameBlComp to an array that doesn't contain it", () => {
        const name: INameBlComp = sampleWithRequiredData;
        const nameCollection: INameBlComp[] = [sampleWithPartialData];
        expectedResult = service.addNameBlCompToCollectionIfMissing(nameCollection, name);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(name);
      });

      it('should add only unique NameBlComp to an array', () => {
        const nameArray: INameBlComp[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const nameCollection: INameBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addNameBlCompToCollectionIfMissing(nameCollection, ...nameArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const name: INameBlComp = sampleWithRequiredData;
        const name2: INameBlComp = sampleWithPartialData;
        expectedResult = service.addNameBlCompToCollectionIfMissing([], name, name2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(name);
        expect(expectedResult).toContain(name2);
      });

      it('should accept null and undefined values', () => {
        const name: INameBlComp = sampleWithRequiredData;
        expectedResult = service.addNameBlCompToCollectionIfMissing([], null, name, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(name);
      });

      it('should return initial array if no NameBlComp is added', () => {
        const nameCollection: INameBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addNameBlCompToCollectionIfMissing(nameCollection, undefined, null);
        expect(expectedResult).toEqual(nameCollection);
      });
    });

    describe('compareNameBlComp', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareNameBlComp(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareNameBlComp(entity1, entity2);
        const compareResult2 = service.compareNameBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareNameBlComp(entity1, entity2);
        const compareResult2 = service.compareNameBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareNameBlComp(entity1, entity2);
        const compareResult2 = service.compareNameBlComp(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
