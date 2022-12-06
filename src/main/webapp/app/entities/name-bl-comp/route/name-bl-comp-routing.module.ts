import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { NameBlCompComponent } from '../list/name-bl-comp.component';
import { NameBlCompDetailComponent } from '../detail/name-bl-comp-detail.component';
import { NameBlCompUpdateComponent } from '../update/name-bl-comp-update.component';
import { NameBlCompRoutingResolveService } from './name-bl-comp-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const nameRoute: Routes = [
  {
    path: '',
    component: NameBlCompComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NameBlCompDetailComponent,
    resolve: {
      name: NameBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NameBlCompUpdateComponent,
    resolve: {
      name: NameBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NameBlCompUpdateComponent,
    resolve: {
      name: NameBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(nameRoute)],
  exports: [RouterModule],
})
export class NameBlCompRoutingModule {}
