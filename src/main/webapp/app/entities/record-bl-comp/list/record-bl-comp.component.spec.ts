import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { RecordBlCompService } from '../service/record-bl-comp.service';

import { RecordBlCompComponent } from './record-bl-comp.component';

describe('RecordBlComp Management Component', () => {
  let comp: RecordBlCompComponent;
  let fixture: ComponentFixture<RecordBlCompComponent>;
  let service: RecordBlCompService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'record-bl-comp', component: RecordBlCompComponent }]), HttpClientTestingModule],
      declarations: [RecordBlCompComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(RecordBlCompComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RecordBlCompComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(RecordBlCompService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.records?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to recordService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getRecordBlCompIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getRecordBlCompIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
