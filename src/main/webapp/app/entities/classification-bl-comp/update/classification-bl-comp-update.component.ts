import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ClassificationBlCompFormService, ClassificationBlCompFormGroup } from './classification-bl-comp-form.service';
import { IClassificationBlComp } from '../classification-bl-comp.model';
import { ClassificationBlCompService } from '../service/classification-bl-comp.service';

@Component({
  selector: 'jhi-classification-bl-comp-update',
  templateUrl: './classification-bl-comp-update.component.html',
})
export class ClassificationBlCompUpdateComponent implements OnInit {
  isSaving = false;
  classification: IClassificationBlComp | null = null;

  editForm: ClassificationBlCompFormGroup = this.classificationFormService.createClassificationBlCompFormGroup();

  constructor(
    protected classificationService: ClassificationBlCompService,
    protected classificationFormService: ClassificationBlCompFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ classification }) => {
      this.classification = classification;
      if (classification) {
        this.updateForm(classification);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const classification = this.classificationFormService.getClassificationBlComp(this.editForm);
    if (classification.id !== null) {
      this.subscribeToSaveResponse(this.classificationService.update(classification));
    } else {
      this.subscribeToSaveResponse(this.classificationService.create(classification));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClassificationBlComp>>): void {
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

  protected updateForm(classification: IClassificationBlComp): void {
    this.classification = classification;
    this.classificationFormService.resetForm(this.editForm, classification);
  }
}
