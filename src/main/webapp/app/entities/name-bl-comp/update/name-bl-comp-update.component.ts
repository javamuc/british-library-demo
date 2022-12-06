import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { NameBlCompFormService, NameBlCompFormGroup } from './name-bl-comp-form.service';
import { INameBlComp } from '../name-bl-comp.model';
import { NameBlCompService } from '../service/name-bl-comp.service';

@Component({
  selector: 'jhi-name-bl-comp-update',
  templateUrl: './name-bl-comp-update.component.html',
})
export class NameBlCompUpdateComponent implements OnInit {
  isSaving = false;
  name: INameBlComp | null = null;

  editForm: NameBlCompFormGroup = this.nameFormService.createNameBlCompFormGroup();

  constructor(
    protected nameService: NameBlCompService,
    protected nameFormService: NameBlCompFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ name }) => {
      this.name = name;
      if (name) {
        this.updateForm(name);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const name = this.nameFormService.getNameBlComp(this.editForm);
    if (name.id !== null) {
      this.subscribeToSaveResponse(this.nameService.update(name));
    } else {
      this.subscribeToSaveResponse(this.nameService.create(name));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INameBlComp>>): void {
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

  protected updateForm(name: INameBlComp): void {
    this.name = name;
    this.nameFormService.resetForm(this.editForm, name);
  }
}
