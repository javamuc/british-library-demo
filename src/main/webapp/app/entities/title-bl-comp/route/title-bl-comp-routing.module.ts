import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TitleBlCompComponent } from '../list/title-bl-comp.component';
import { TitleBlCompDetailComponent } from '../detail/title-bl-comp-detail.component';
import { TitleBlCompUpdateComponent } from '../update/title-bl-comp-update.component';
import { TitleBlCompRoutingResolveService } from './title-bl-comp-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const titleRoute: Routes = [
  {
    path: '',
    component: TitleBlCompComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TitleBlCompDetailComponent,
    resolve: {
      title: TitleBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TitleBlCompUpdateComponent,
    resolve: {
      title: TitleBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TitleBlCompUpdateComponent,
    resolve: {
      title: TitleBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(titleRoute)],
  exports: [RouterModule],
})
export class TitleBlCompRoutingModule {}
