import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClassificationBlComp } from '../classification-bl-comp.model';

@Component({
  selector: 'jhi-classification-bl-comp-detail',
  templateUrl: './classification-bl-comp-detail.component.html',
})
export class ClassificationBlCompDetailComponent implements OnInit {
  classification: IClassificationBlComp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ classification }) => {
      this.classification = classification;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
