import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { TopicBlCompFormService, TopicBlCompFormGroup } from './topic-bl-comp-form.service';
import { ITopicBlComp } from '../topic-bl-comp.model';
import { TopicBlCompService } from '../service/topic-bl-comp.service';

@Component({
  selector: 'jhi-topic-bl-comp-update',
  templateUrl: './topic-bl-comp-update.component.html',
})
export class TopicBlCompUpdateComponent implements OnInit {
  isSaving = false;
  topic: ITopicBlComp | null = null;

  editForm: TopicBlCompFormGroup = this.topicFormService.createTopicBlCompFormGroup();

  constructor(
    protected topicService: TopicBlCompService,
    protected topicFormService: TopicBlCompFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ topic }) => {
      this.topic = topic;
      if (topic) {
        this.updateForm(topic);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const topic = this.topicFormService.getTopicBlComp(this.editForm);
    if (topic.id !== null) {
      this.subscribeToSaveResponse(this.topicService.update(topic));
    } else {
      this.subscribeToSaveResponse(this.topicService.create(topic));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITopicBlComp>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(topic: ITopicBlComp): void {
    this.topic = topic;
    this.topicFormService.resetForm(this.editForm, topic);
  }
}
