import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { INameBlComp } from '../name-bl-comp.model';
import { NameBlCompService } from '../service/name-bl-comp.service';

@Injectable({ providedIn: 'root' })
export class NameBlCompRoutingResolveService implements Resolve<INameBlComp | null> {
  constructor(protected service: NameBlCompService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INameBlComp | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((name: HttpResponse<INameBlComp>) => {
          if (name.body) {
            return of(name.body);
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
