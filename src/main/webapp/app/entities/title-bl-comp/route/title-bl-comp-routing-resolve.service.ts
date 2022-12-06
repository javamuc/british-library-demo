import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITitleBlComp } from '../title-bl-comp.model';
import { TitleBlCompService } from '../service/title-bl-comp.service';

@Injectable({ providedIn: 'root' })
export class TitleBlCompRoutingResolveService implements Resolve<ITitleBlComp | null> {
  constructor(protected service: TitleBlCompService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITitleBlComp | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((title: HttpResponse<ITitleBlComp>) => {
          if (title.body) {
            return of(title.body);
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
