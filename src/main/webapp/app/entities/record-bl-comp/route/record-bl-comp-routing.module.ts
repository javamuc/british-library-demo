import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RecordBlCompComponent } from '../list/record-bl-comp.component';
import { RecordBlCompDetailComponent } from '../detail/record-bl-comp-detail.component';
import { RecordBlCompUpdateComponent } from '../update/record-bl-comp-update.component';
import { RecordBlCompRoutingResolveService } from './record-bl-comp-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const recordRoute: Routes = [
  {
    path: '',
    component: RecordBlCompComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RecordBlCompDetailComponent,
    resolve: {
      record: RecordBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RecordBlCompUpdateComponent,
    resolve: {
      record: RecordBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RecordBlCompUpdateComponent,
    resolve: {
      record: RecordBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(recordRoute)],
  exports: [RouterModule],
})
export class RecordBlCompRoutingModule {}
