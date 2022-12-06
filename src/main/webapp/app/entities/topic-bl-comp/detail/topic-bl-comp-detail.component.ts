import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITopicBlComp } from '../topic-bl-comp.model';

@Component({
  selector: 'jhi-topic-bl-comp-detail',
  templateUrl: './topic-bl-comp-detail.component.html',
})
export class TopicBlCompDetailComponent implements OnInit {
  topic: ITopicBlComp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ topic }) => {
      this.topic = topic;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
