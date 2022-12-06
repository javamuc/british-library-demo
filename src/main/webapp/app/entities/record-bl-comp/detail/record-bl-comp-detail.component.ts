import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRecordBlComp } from '../record-bl-comp.model';

@Component({
  selector: 'jhi-record-bl-comp-detail',
  templateUrl: './record-bl-comp-detail.component.html',
})
export class RecordBlCompDetailComponent implements OnInit {
  record: IRecordBlComp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ record }) => {
      this.record = record;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
