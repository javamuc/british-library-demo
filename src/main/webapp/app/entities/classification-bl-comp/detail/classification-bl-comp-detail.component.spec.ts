import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ClassificationBlCompDetailComponent } from './classification-bl-comp-detail.component';

describe('ClassificationBlComp Management Detail Component', () => {
  let comp: ClassificationBlCompDetailComponent;
  let fixture: ComponentFixture<ClassificationBlCompDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClassificationBlCompDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ classification: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ClassificationBlCompDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ClassificationBlCompDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load classification on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.classification).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
