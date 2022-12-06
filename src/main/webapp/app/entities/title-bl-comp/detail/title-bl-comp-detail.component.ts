import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITitleBlComp } from '../title-bl-comp.model';

@Component({
  selector: 'jhi-title-bl-comp-detail',
  templateUrl: './title-bl-comp-detail.component.html',
})
export class TitleBlCompDetailComponent implements OnInit {
  title: ITitleBlComp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ title }) => {
      this.title = title;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
