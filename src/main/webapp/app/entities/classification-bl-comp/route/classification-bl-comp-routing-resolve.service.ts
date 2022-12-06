import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IClassificationBlComp } from '../classification-bl-comp.model';
import { ClassificationBlCompService } from '../service/classification-bl-comp.service';

@Injectable({ providedIn: 'root' })
export class ClassificationBlCompRoutingResolveService implements Resolve<IClassificationBlComp | null> {
  constructor(protected service: ClassificationBlCompService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClassificationBlComp | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((classification: HttpResponse<IClassificationBlComp>) => {
          if (classification.body) {
            return of(classification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
