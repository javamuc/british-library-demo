import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { TitleBlCompFormService, TitleBlCompFormGroup } from './title-bl-comp-form.service';
import { ITitleBlComp } from '../title-bl-comp.model';
import { TitleBlCompService } from '../service/title-bl-comp.service';

@Component({
  selector: 'jhi-title-bl-comp-update',
  templateUrl: './title-bl-comp-update.component.html',
})
export class TitleBlCompUpdateComponent implements OnInit {
  isSaving = false;
  title: ITitleBlComp | null = null;

  editForm: TitleBlCompFormGroup = this.titleFormService.createTitleBlCompFormGroup();

  constructor(
    protected titleService: TitleBlCompService,
    protected titleFormService: TitleBlCompFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ title }) => {
      this.title = title;
      if (title) {
        this.updateForm(title);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const title = this.titleFormService.getTitleBlComp(this.editForm);
    if (title.id !== null) {
      this.subscribeToSaveResponse(this.titleService.update(title));
    } else {
      this.subscribeToSaveResponse(this.titleService.create(title));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITitleBlComp>>): void {
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

  protected updateForm(title: ITitleBlComp): void {
    this.title = title;
    this.titleFormService.resetForm(this.editForm, title);
  }
}
