import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RecordBlCompDetailComponent } from './record-bl-comp-detail.component';

describe('RecordBlComp Management Detail Component', () => {
  let comp: RecordBlCompDetailComponent;
  let fixture: ComponentFixture<RecordBlCompDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RecordBlCompDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ record: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(RecordBlCompDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RecordBlCompDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load record on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.record).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
