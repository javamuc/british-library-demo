import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { NameBlCompFormService } from './name-bl-comp-form.service';
import { NameBlCompService } from '../service/name-bl-comp.service';
import { INameBlComp } from '../name-bl-comp.model';

import { NameBlCompUpdateComponent } from './name-bl-comp-update.component';

describe('NameBlComp Management Update Component', () => {
  let comp: NameBlCompUpdateComponent;
  let fixture: ComponentFixture<NameBlCompUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let nameFormService: NameBlCompFormService;
  let nameService: NameBlCompService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [NameBlCompUpdateComponent],
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
      .overrideTemplate(NameBlCompUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NameBlCompUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    nameFormService = TestBed.inject(NameBlCompFormService);
    nameService = TestBed.inject(NameBlCompService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const name: INameBlComp = { id: 456 };

      activatedRoute.data = of({ name });
      comp.ngOnInit();

      expect(comp.name).toEqual(name);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INameBlComp>>();
      const name = { id: 123 };
      jest.spyOn(nameFormService, 'getNameBlComp').mockReturnValue(name);
      jest.spyOn(nameService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ name });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: name }));
      saveSubject.complete();

      // THEN
      expect(nameFormService.getNameBlComp).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(nameService.update).toHaveBeenCalledWith(expect.objectContaining(name));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INameBlComp>>();
      const name = { id: 123 };
      jest.spyOn(nameFormService, 'getNameBlComp').mockReturnValue({ id: null });
      jest.spyOn(nameService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ name: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: name }));
      saveSubject.complete();

      // THEN
      expect(nameFormService.getNameBlComp).toHaveBeenCalled();
      expect(nameService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INameBlComp>>();
      const name = { id: 123 };
      jest.spyOn(nameService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ name });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(nameService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
