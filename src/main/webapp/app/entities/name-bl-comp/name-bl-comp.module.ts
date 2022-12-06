import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { NameBlCompComponent } from './list/name-bl-comp.component';
import { NameBlCompDetailComponent } from './detail/name-bl-comp-detail.component';
import { NameBlCompUpdateComponent } from './update/name-bl-comp-update.component';
import { NameBlCompDeleteDialogComponent } from './delete/name-bl-comp-delete-dialog.component';
import { NameBlCompRoutingModule } from './route/name-bl-comp-routing.module';

@NgModule({
  imports: [SharedModule, NameBlCompRoutingModule],
  declarations: [NameBlCompComponent, NameBlCompDetailComponent, NameBlCompUpdateComponent, NameBlCompDeleteDialogComponent],
})
export class NameBlCompModule {}
