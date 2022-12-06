import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RecordBlCompFormService } from './record-bl-comp-form.service';
import { RecordBlCompService } from '../service/record-bl-comp.service';
import { IRecordBlComp } from '../record-bl-comp.model';

import { RecordBlCompUpdateComponent } from './record-bl-comp-update.component';

describe('RecordBlComp Management Update Component', () => {
  let comp: RecordBlCompUpdateComponent;
  let fixture: ComponentFixture<RecordBlCompUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let recordFormService: RecordBlCompFormService;
  let recordService: RecordBlCompService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RecordBlCompUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(RecordBlCompUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RecordBlCompUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    recordFormService = TestBed.inject(RecordBlCompFormService);
    recordService = TestBed.inject(RecordBlCompService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const record: IRecordBlComp = { id: 456 };

      activatedRoute.data = of({ record });
      comp.ngOnInit();

      expect(comp.record).toEqual(record);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRecordBlComp>>();
      const record = { id: 123 };
      jest.spyOn(recordFormService, 'getRecordBlComp').mockReturnValue(record);
      jest.spyOn(recordService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ record });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: record }));
      saveSubject.complete();

      // THEN
      expect(recordFormService.getRecordBlComp).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(recordService.update).toHaveBeenCalledWith(expect.objectContaining(record));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRecordBlComp>>();
      const record = { id: 123 };
      jest.spyOn(recordFormService, 'getRecordBlComp').mockReturnValue({ id: null });
      jest.spyOn(recordService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ record: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: record }));
      saveSubject.complete();

      // THEN
      expect(recordFormService.getRecordBlComp).toHaveBeenCalled();
      expect(recordService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRecordBlComp>>();
      const record = { id: 123 };
      jest.spyOn(recordService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ record });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(recordService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
