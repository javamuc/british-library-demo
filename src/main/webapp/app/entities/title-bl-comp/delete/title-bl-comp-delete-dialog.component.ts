import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITitleBlComp } from '../title-bl-comp.model';
import { TitleBlCompService } from '../service/title-bl-comp.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './title-bl-comp-delete-dialog.component.html',
})
export class TitleBlCompDeleteDialogComponent {
  title?: ITitleBlComp;

  constructor(protected titleService: TitleBlCompService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.titleService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
