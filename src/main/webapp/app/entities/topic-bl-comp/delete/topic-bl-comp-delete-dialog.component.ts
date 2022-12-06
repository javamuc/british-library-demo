import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITopicBlComp } from '../topic-bl-comp.model';
import { TopicBlCompService } from '../service/topic-bl-comp.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './topic-bl-comp-delete-dialog.component.html',
})
export class TopicBlCompDeleteDialogComponent {
  topic?: ITopicBlComp;

  constructor(protected topicService: TopicBlCompService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.topicService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
