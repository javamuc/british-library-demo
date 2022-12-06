import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ClassificationBlCompComponent } from './list/classification-bl-comp.component';
import { ClassificationBlCompDetailComponent } from './detail/classification-bl-comp-detail.component';
import { ClassificationBlCompUpdateComponent } from './update/classification-bl-comp-update.component';
import { ClassificationBlCompDeleteDialogComponent } from './delete/classification-bl-comp-delete-dialog.component';
import { ClassificationBlCompRoutingModule } from './route/classification-bl-comp-routing.module';

@NgModule({
  imports: [SharedModule, ClassificationBlCompRoutingModule],
  declarations: [
    ClassificationBlCompComponent,
    ClassificationBlCompDetailComponent,
    ClassificationBlCompUpdateComponent,
    ClassificationBlCompDeleteDialogComponent,
  ],
})
export class ClassificationBlCompModule {}
