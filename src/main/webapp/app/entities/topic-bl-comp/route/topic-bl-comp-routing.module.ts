import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TopicBlCompComponent } from '../list/topic-bl-comp.component';
import { TopicBlCompDetailComponent } from '../detail/topic-bl-comp-detail.component';
import { TopicBlCompUpdateComponent } from '../update/topic-bl-comp-update.component';
import { TopicBlCompRoutingResolveService } from './topic-bl-comp-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const topicRoute: Routes = [
  {
    path: '',
    component: TopicBlCompComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TopicBlCompDetailComponent,
    resolve: {
      topic: TopicBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TopicBlCompUpdateComponent,
    resolve: {
      topic: TopicBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TopicBlCompUpdateComponent,
    resolve: {
      topic: TopicBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(topicRoute)],
  exports: [RouterModule],
})
export class TopicBlCompRoutingModule {}
