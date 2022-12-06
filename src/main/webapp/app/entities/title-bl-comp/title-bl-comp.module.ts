import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TitleBlCompComponent } from './list/title-bl-comp.component';
import { TitleBlCompDetailComponent } from './detail/title-bl-comp-detail.component';
import { TitleBlCompUpdateComponent } from './update/title-bl-comp-update.component';
import { TitleBlCompDeleteDialogComponent } from './delete/title-bl-comp-delete-dialog.component';
import { TitleBlCompRoutingModule } from './route/title-bl-comp-routing.module';

@NgModule({
  imports: [SharedModule, TitleBlCompRoutingModule],
  declarations: [TitleBlCompComponent, TitleBlCompDetailComponent, TitleBlCompUpdateComponent, TitleBlCompDeleteDialogComponent],
})
export class TitleBlCompModule {}
