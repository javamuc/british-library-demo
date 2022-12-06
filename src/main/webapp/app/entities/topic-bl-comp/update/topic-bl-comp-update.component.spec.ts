import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TopicBlCompFormService } from './topic-bl-comp-form.service';
import { TopicBlCompService } from '../service/topic-bl-comp.service';
import { ITopicBlComp } from '../topic-bl-comp.model';

import { TopicBlCompUpdateComponent } from './topic-bl-comp-update.component';

describe('TopicBlComp Management Update Component', () => {
  let comp: TopicBlCompUpdateComponent;
  let fixture: ComponentFixture<TopicBlCompUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let topicFormService: TopicBlCompFormService;
  let topicService: TopicBlCompService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TopicBlCompUpdateComponent],
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
      .overrideTemplate(TopicBlCompUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TopicBlCompUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    topicFormService = TestBed.inject(TopicBlCompFormService);
    topicService = TestBed.inject(TopicBlCompService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const topic: ITopicBlComp = { id: 456 };

      activatedRoute.data = of({ topic });
      comp.ngOnInit();

      expect(comp.topic).toEqual(topic);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITopicBlComp>>();
      const topic = { id: 123 };
      jest.spyOn(topicFormService, 'getTopicBlComp').mockReturnValue(topic);
      jest.spyOn(topicService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ topic });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: topic }));
      saveSubject.complete();

      // THEN
      expect(topicFormService.getTopicBlComp).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(topicService.update).toHaveBeenCalledWith(expect.objectContaining(topic));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITopicBlComp>>();
      const topic = { id: 123 };
      jest.spyOn(topicFormService, 'getTopicBlComp').mockReturnValue({ id: null });
      jest.spyOn(topicService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ topic: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: topic }));
      saveSubject.complete();

      // THEN
      expect(topicFormService.getTopicBlComp).toHaveBeenCalled();
      expect(topicService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITopicBlComp>>();
      const topic = { id: 123 };
      jest.spyOn(topicService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ topic });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(topicService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
