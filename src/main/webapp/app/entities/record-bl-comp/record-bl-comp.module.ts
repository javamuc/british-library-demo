import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RecordBlCompComponent } from './list/record-bl-comp.component';
import { RecordBlCompDetailComponent } from './detail/record-bl-comp-detail.component';
import { RecordBlCompUpdateComponent } from './update/record-bl-comp-update.component';
import { RecordBlCompDeleteDialogComponent } from './delete/record-bl-comp-delete-dialog.component';
import { RecordBlCompRoutingModule } from './route/record-bl-comp-routing.module';

@NgModule({
  imports: [SharedModule, RecordBlCompRoutingModule],
  declarations: [RecordBlCompComponent, RecordBlCompDetailComponent, RecordBlCompUpdateComponent, RecordBlCompDeleteDialogComponent],
})
export class RecordBlCompModule {}
