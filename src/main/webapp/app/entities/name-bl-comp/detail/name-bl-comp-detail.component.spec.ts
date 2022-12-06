import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NameBlCompDetailComponent } from './name-bl-comp-detail.component';

describe('NameBlComp Management Detail Component', () => {
  let comp: NameBlCompDetailComponent;
  let fixture: ComponentFixture<NameBlCompDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NameBlCompDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ name: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(NameBlCompDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(NameBlCompDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load name on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.name).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
