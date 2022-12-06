import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ClassificationBlCompComponent } from '../list/classification-bl-comp.component';
import { ClassificationBlCompDetailComponent } from '../detail/classification-bl-comp-detail.component';
import { ClassificationBlCompUpdateComponent } from '../update/classification-bl-comp-update.component';
import { ClassificationBlCompRoutingResolveService } from './classification-bl-comp-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const classificationRoute: Routes = [
  {
    path: '',
    component: ClassificationBlCompComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClassificationBlCompDetailComponent,
    resolve: {
      classification: ClassificationBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClassificationBlCompUpdateComponent,
    resolve: {
      classification: ClassificationBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClassificationBlCompUpdateComponent,
    resolve: {
      classification: ClassificationBlCompRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(classificationRoute)],
  exports: [RouterModule],
})
export class ClassificationBlCompRoutingModule {}
