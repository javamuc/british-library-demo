import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TopicBlCompComponent } from './list/topic-bl-comp.component';
import { TopicBlCompDetailComponent } from './detail/topic-bl-comp-detail.component';
import { TopicBlCompUpdateComponent } from './update/topic-bl-comp-update.component';
import { TopicBlCompDeleteDialogComponent } from './delete/topic-bl-comp-delete-dialog.component';
import { TopicBlCompRoutingModule } from './route/topic-bl-comp-routing.module';

@NgModule({
  imports: [SharedModule, TopicBlCompRoutingModule],
  declarations: [TopicBlCompComponent, TopicBlCompDetailComponent, TopicBlCompUpdateComponent, TopicBlCompDeleteDialogComponent],
})
export class TopicBlCompModule {}
