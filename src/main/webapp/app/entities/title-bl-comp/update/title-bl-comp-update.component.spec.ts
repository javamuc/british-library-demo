import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TitleBlCompFormService } from './title-bl-comp-form.service';
import { TitleBlCompService } from '../service/title-bl-comp.service';
import { ITitleBlComp } from '../title-bl-comp.model';

import { TitleBlCompUpdateComponent } from './title-bl-comp-update.component';

describe('TitleBlComp Management Update Component', () => {
  let comp: TitleBlCompUpdateComponent;
  let fixture: ComponentFixture<TitleBlCompUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let titleFormService: TitleBlCompFormService;
  let titleService: TitleBlCompService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TitleBlCompUpdateComponent],
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
      .overrideTemplate(TitleBlCompUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TitleBlCompUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    titleFormService = TestBed.inject(TitleBlCompFormService);
    titleService = TestBed.inject(TitleBlCompService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const title: ITitleBlComp = { id: 456 };

      activatedRoute.data = of({ title });
      comp.ngOnInit();

      expect(comp.title).toEqual(title);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITitleBlComp>>();
      const title = { id: 123 };
      jest.spyOn(titleFormService, 'getTitleBlComp').mockReturnValue(title);
      jest.spyOn(titleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ title });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: title }));
      saveSubject.complete();

      // THEN
      expect(titleFormService.getTitleBlComp).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(titleService.update).toHaveBeenCalledWith(expect.objectContaining(title));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITitleBlComp>>();
      const title = { id: 123 };
      jest.spyOn(titleFormService, 'getTitleBlComp').mockReturnValue({ id: null });
      jest.spyOn(titleService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ title: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: title }));
      saveSubject.complete();

      // THEN
      expect(titleFormService.getTitleBlComp).toHaveBeenCalled();
      expect(titleService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITitleBlComp>>();
      const title = { id: 123 };
      jest.spyOn(titleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ title });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(titleService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
