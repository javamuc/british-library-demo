import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITitleBlComp, NewTitleBlComp } from '../title-bl-comp.model';

export type PartialUpdateTitleBlComp = Partial<ITitleBlComp> & Pick<ITitleBlComp, 'id'>;

export type EntityResponseType = HttpResponse<ITitleBlComp>;
export type EntityArrayResponseType = HttpResponse<ITitleBlComp[]>;

@Injectable({ providedIn: 'root' })
export class TitleBlCompService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/titles');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(title: NewTitleBlComp): Observable<EntityResponseType> {
    return this.http.post<ITitleBlComp>(this.resourceUrl, title, { observe: 'response' });
  }

  update(title: ITitleBlComp): Observable<EntityResponseType> {
    return this.http.put<ITitleBlComp>(`${this.resourceUrl}/${this.getTitleBlCompIdentifier(title)}`, title, { observe: 'response' });
  }

  partialUpdate(title: PartialUpdateTitleBlComp): Observable<EntityResponseType> {
    return this.http.patch<ITitleBlComp>(`${this.resourceUrl}/${this.getTitleBlCompIdentifier(title)}`, title, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITitleBlComp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITitleBlComp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTitleBlCompIdentifier(title: Pick<ITitleBlComp, 'id'>): number {
    return title.id;
  }

  compareTitleBlComp(o1: Pick<ITitleBlComp, 'id'> | null, o2: Pick<ITitleBlComp, 'id'> | null): boolean {
    return o1 && o2 ? this.getTitleBlCompIdentifier(o1) === this.getTitleBlCompIdentifier(o2) : o1 === o2;
  }

  addTitleBlCompToCollectionIfMissing<Type extends Pick<ITitleBlComp, 'id'>>(
    titleCollection: Type[],
    ...titlesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const titles: Type[] = titlesToCheck.filter(isPresent);
    if (titles.length > 0) {
      const titleCollectionIdentifiers = titleCollection.map(titleItem => this.getTitleBlCompIdentifier(titleItem)!);
      const titlesToAdd = titles.filter(titleItem => {
        const titleIdentifier = this.getTitleBlCompIdentifier(titleItem);
        if (titleCollectionIdentifiers.includes(titleIdentifier)) {
          return false;
        }
        titleCollectionIdentifiers.push(titleIdentifier);
        return true;
      });
      return [...titlesToAdd, ...titleCollection];
    }
    return titleCollection;
  }
}
