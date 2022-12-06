import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { RecordBlCompFormService, RecordBlCompFormGroup } from './record-bl-comp-form.service';
import { IRecordBlComp } from '../record-bl-comp.model';
import { RecordBlCompService } from '../service/record-bl-comp.service';

@Component({
  selector: 'jhi-record-bl-comp-update',
  templateUrl: './record-bl-comp-update.component.html',
})
export class RecordBlCompUpdateComponent implements OnInit {
  isSaving = false;
  record: IRecordBlComp | null = null;

  editForm: RecordBlCompFormGroup = this.recordFormService.createRecordBlCompFormGroup();

  constructor(
    protected recordService: RecordBlCompService,
    protected recordFormService: RecordBlCompFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ record }) => {
      this.record = record;
      if (record) {
        this.updateForm(record);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const record = this.recordFormService.getRecordBlComp(this.editForm);
    if (record.id !== null) {
      this.subscribeToSaveResponse(this.recordService.update(record));
    } else {
      this.subscribeToSaveResponse(this.recordService.create(record));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecordBlComp>>): void {
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

  protected updateForm(record: IRecordBlComp): void {
    this.record = record;
    this.recordFormService.resetForm(this.editForm, record);
  }
}
