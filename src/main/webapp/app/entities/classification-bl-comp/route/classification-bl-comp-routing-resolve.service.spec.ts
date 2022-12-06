import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IClassificationBlComp } from '../classification-bl-comp.model';
import { ClassificationBlCompService } from '../service/classification-bl-comp.service';

import { ClassificationBlCompRoutingResolveService } from './classification-bl-comp-routing-resolve.service';

describe('ClassificationBlComp routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ClassificationBlCompRoutingResolveService;
  let service: ClassificationBlCompService;
  let resultClassificationBlComp: IClassificationBlComp | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(ClassificationBlCompRoutingResolveService);
    service = TestBed.inject(ClassificationBlCompService);
    resultClassificationBlComp = undefined;
  });

  describe('resolve', () => {
    it('should return IClassificationBlComp returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultClassificationBlComp = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultClassificationBlComp).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultClassificationBlComp = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultClassificationBlComp).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IClassificationBlComp>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultClassificationBlComp = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultClassificationBlComp).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
