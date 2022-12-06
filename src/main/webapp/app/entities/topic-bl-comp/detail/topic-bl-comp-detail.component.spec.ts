import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TopicBlCompDetailComponent } from './topic-bl-comp-detail.component';

describe('TopicBlComp Management Detail Component', () => {
  let comp: TopicBlCompDetailComponent;
  let fixture: ComponentFixture<TopicBlCompDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TopicBlCompDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ topic: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TopicBlCompDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TopicBlCompDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load topic on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.topic).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
