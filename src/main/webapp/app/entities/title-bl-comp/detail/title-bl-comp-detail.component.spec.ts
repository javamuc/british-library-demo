import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TitleBlCompDetailComponent } from './title-bl-comp-detail.component';

describe('TitleBlComp Management Detail Component', () => {
  let comp: TitleBlCompDetailComponent;
  let fixture: ComponentFixture<TitleBlCompDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TitleBlCompDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ title: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TitleBlCompDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TitleBlCompDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load title on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.title).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
