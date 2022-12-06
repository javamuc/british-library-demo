import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INameBlComp } from '../name-bl-comp.model';

@Component({
  selector: 'jhi-name-bl-comp-detail',
  templateUrl: './name-bl-comp-detail.component.html',
})
export class NameBlCompDetailComponent implements OnInit {
  name: INameBlComp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ name }) => {
      this.name = name;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
