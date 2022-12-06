import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRecordBlComp } from '../record-bl-comp.model';
import { RecordBlCompService } from '../service/record-bl-comp.service';

@Injectable({ providedIn: 'root' })
export class RecordBlCompRoutingResolveService implements Resolve<IRecordBlComp | null> {
  constructor(protected service: RecordBlCompService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRecordBlComp | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((record: HttpResponse<IRecordBlComp>) => {
          if (record.body) {
            return of(record.body);
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
