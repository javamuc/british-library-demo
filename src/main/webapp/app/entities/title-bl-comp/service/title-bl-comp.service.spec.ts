import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITitleBlComp } from '../title-bl-comp.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../title-bl-comp.test-samples';

import { TitleBlCompService } from './title-bl-comp.service';

const requireRestSample: ITitleBlComp = {
  ...sampleWithRequiredData,
};

describe('TitleBlComp Service', () => {
  let service: TitleBlCompService;
  let httpMock: HttpTestingController;
  let expectedResult: ITitleBlComp | ITitleBlComp[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TitleBlCompService);
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

    it('should create a TitleBlComp', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const title = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(title).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TitleBlComp', () => {
      const title = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(title).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TitleBlComp', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TitleBlComp', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TitleBlComp', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTitleBlCompToCollectionIfMissing', () => {
      it('should add a TitleBlComp to an empty array', () => {
        const title: ITitleBlComp = sampleWithRequiredData;
        expectedResult = service.addTitleBlCompToCollectionIfMissing([], title);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(title);
      });

      it('should not add a TitleBlComp to an array that contains it', () => {
        const title: ITitleBlComp = sampleWithRequiredData;
        const titleCollection: ITitleBlComp[] = [
          {
            ...title,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTitleBlCompToCollectionIfMissing(titleCollection, title);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TitleBlComp to an array that doesn't contain it", () => {
        const title: ITitleBlComp = sampleWithRequiredData;
        const titleCollection: ITitleBlComp[] = [sampleWithPartialData];
        expectedResult = service.addTitleBlCompToCollectionIfMissing(titleCollection, title);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(title);
      });

      it('should add only unique TitleBlComp to an array', () => {
        const titleArray: ITitleBlComp[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const titleCollection: ITitleBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addTitleBlCompToCollectionIfMissing(titleCollection, ...titleArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const title: ITitleBlComp = sampleWithRequiredData;
        const title2: ITitleBlComp = sampleWithPartialData;
        expectedResult = service.addTitleBlCompToCollectionIfMissing([], title, title2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(title);
        expect(expectedResult).toContain(title2);
      });

      it('should accept null and undefined values', () => {
        const title: ITitleBlComp = sampleWithRequiredData;
        expectedResult = service.addTitleBlCompToCollectionIfMissing([], null, title, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(title);
      });

      it('should return initial array if no TitleBlComp is added', () => {
        const titleCollection: ITitleBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addTitleBlCompToCollectionIfMissing(titleCollection, undefined, null);
        expect(expectedResult).toEqual(titleCollection);
      });
    });

    describe('compareTitleBlComp', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTitleBlComp(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTitleBlComp(entity1, entity2);
        const compareResult2 = service.compareTitleBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTitleBlComp(entity1, entity2);
        const compareResult2 = service.compareTitleBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTitleBlComp(entity1, entity2);
        const compareResult2 = service.compareTitleBlComp(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
