import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITopicBlComp } from '../topic-bl-comp.model';
import { TopicBlCompService } from '../service/topic-bl-comp.service';

@Injectable({ providedIn: 'root' })
export class TopicBlCompRoutingResolveService implements Resolve<ITopicBlComp | null> {
  constructor(protected service: TopicBlCompService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITopicBlComp | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((topic: HttpResponse<ITopicBlComp>) => {
          if (topic.body) {
            return of(topic.body);
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
