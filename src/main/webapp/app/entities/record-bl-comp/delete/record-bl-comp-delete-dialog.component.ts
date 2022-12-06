import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRecordBlComp } from '../record-bl-comp.model';
import { RecordBlCompService } from '../service/record-bl-comp.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './record-bl-comp-delete-dialog.component.html',
})
export class RecordBlCompDeleteDialogComponent {
  record?: IRecordBlComp;

  constructor(protected recordService: RecordBlCompService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.recordService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
