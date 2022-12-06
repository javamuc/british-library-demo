import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { INameBlComp } from '../name-bl-comp.model';
import { NameBlCompService } from '../service/name-bl-comp.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './name-bl-comp-delete-dialog.component.html',
})
export class NameBlCompDeleteDialogComponent {
  name?: INameBlComp;

  constructor(protected nameService: NameBlCompService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nameService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
