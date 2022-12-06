import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IClassificationBlComp } from '../classification-bl-comp.model';
import { ClassificationBlCompService } from '../service/classification-bl-comp.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './classification-bl-comp-delete-dialog.component.html',
})
export class ClassificationBlCompDeleteDialogComponent {
  classification?: IClassificationBlComp;

  constructor(protected classificationService: ClassificationBlCompService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.classificationService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
