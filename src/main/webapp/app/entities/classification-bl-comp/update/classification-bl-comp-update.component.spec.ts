import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ClassificationBlCompFormService } from './classification-bl-comp-form.service';
import { ClassificationBlCompService } from '../service/classification-bl-comp.service';
import { IClassificationBlComp } from '../classification-bl-comp.model';

import { ClassificationBlCompUpdateComponent } from './classification-bl-comp-update.component';

describe('ClassificationBlComp Management Update Component', () => {
  let comp: ClassificationBlCompUpdateComponent;
  let fixture: ComponentFixture<ClassificationBlCompUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let classificationFormService: ClassificationBlCompFormService;
  let classificationService: ClassificationBlCompService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ClassificationBlCompUpdateComponent],
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
      .overrideTemplate(ClassificationBlCompUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ClassificationBlCompUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    classificationFormService = TestBed.inject(ClassificationBlCompFormService);
    classificationService = TestBed.inject(ClassificationBlCompService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const classification: IClassificationBlComp = { id: 456 };

      activatedRoute.data = of({ classification });
      comp.ngOnInit();

      expect(comp.classification).toEqual(classification);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClassificationBlComp>>();
      const classification = { id: 123 };
      jest.spyOn(classificationFormService, 'getClassificationBlComp').mockReturnValue(classification);
      jest.spyOn(classificationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ classification });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: classification }));
      saveSubject.complete();

      // THEN
      expect(classificationFormService.getClassificationBlComp).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(classificationService.update).toHaveBeenCalledWith(expect.objectContaining(classification));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClassificationBlComp>>();
      const classification = { id: 123 };
      jest.spyOn(classificationFormService, 'getClassificationBlComp').mockReturnValue({ id: null });
      jest.spyOn(classificationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ classification: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: classification }));
      saveSubject.complete();

      // THEN
      expect(classificationFormService.getClassificationBlComp).toHaveBeenCalled();
      expect(classificationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClassificationBlComp>>();
      const classification = { id: 123 };
      jest.spyOn(classificationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ classification });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(classificationService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
