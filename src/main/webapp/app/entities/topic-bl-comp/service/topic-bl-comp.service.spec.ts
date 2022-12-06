import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITopicBlComp } from '../topic-bl-comp.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../topic-bl-comp.test-samples';

import { TopicBlCompService } from './topic-bl-comp.service';

const requireRestSample: ITopicBlComp = {
  ...sampleWithRequiredData,
};

describe('TopicBlComp Service', () => {
  let service: TopicBlCompService;
  let httpMock: HttpTestingController;
  let expectedResult: ITopicBlComp | ITopicBlComp[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TopicBlCompService);
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

    it('should create a TopicBlComp', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const topic = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(topic).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TopicBlComp', () => {
      const topic = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(topic).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TopicBlComp', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TopicBlComp', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TopicBlComp', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTopicBlCompToCollectionIfMissing', () => {
      it('should add a TopicBlComp to an empty array', () => {
        const topic: ITopicBlComp = sampleWithRequiredData;
        expectedResult = service.addTopicBlCompToCollectionIfMissing([], topic);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(topic);
      });

      it('should not add a TopicBlComp to an array that contains it', () => {
        const topic: ITopicBlComp = sampleWithRequiredData;
        const topicCollection: ITopicBlComp[] = [
          {
            ...topic,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTopicBlCompToCollectionIfMissing(topicCollection, topic);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TopicBlComp to an array that doesn't contain it", () => {
        const topic: ITopicBlComp = sampleWithRequiredData;
        const topicCollection: ITopicBlComp[] = [sampleWithPartialData];
        expectedResult = service.addTopicBlCompToCollectionIfMissing(topicCollection, topic);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(topic);
      });

      it('should add only unique TopicBlComp to an array', () => {
        const topicArray: ITopicBlComp[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const topicCollection: ITopicBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addTopicBlCompToCollectionIfMissing(topicCollection, ...topicArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const topic: ITopicBlComp = sampleWithRequiredData;
        const topic2: ITopicBlComp = sampleWithPartialData;
        expectedResult = service.addTopicBlCompToCollectionIfMissing([], topic, topic2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(topic);
        expect(expectedResult).toContain(topic2);
      });

      it('should accept null and undefined values', () => {
        const topic: ITopicBlComp = sampleWithRequiredData;
        expectedResult = service.addTopicBlCompToCollectionIfMissing([], null, topic, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(topic);
      });

      it('should return initial array if no TopicBlComp is added', () => {
        const topicCollection: ITopicBlComp[] = [sampleWithRequiredData];
        expectedResult = service.addTopicBlCompToCollectionIfMissing(topicCollection, undefined, null);
        expect(expectedResult).toEqual(topicCollection);
      });
    });

    describe('compareTopicBlComp', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTopicBlComp(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTopicBlComp(entity1, entity2);
        const compareResult2 = service.compareTopicBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTopicBlComp(entity1, entity2);
        const compareResult2 = service.compareTopicBlComp(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTopicBlComp(entity1, entity2);
        const compareResult2 = service.compareTopicBlComp(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
