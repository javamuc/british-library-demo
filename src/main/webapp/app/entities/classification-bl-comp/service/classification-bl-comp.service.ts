import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IClassificationBlComp, NewClassificationBlComp } from '../classification-bl-comp.model';

export type PartialUpdateClassificationBlComp = Partial<IClassificationBlComp> & Pick<IClassificationBlComp, 'id'>;

export type EntityResponseType = HttpResponse<IClassificationBlComp>;
export type EntityArrayResponseType = HttpResponse<IClassificationBlComp[]>;

@Injectable({ providedIn: 'root' })
export class ClassificationBlCompService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/classifications');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(classification: NewClassificationBlComp): Observable<EntityResponseType> {
    return this.http.post<IClassificationBlComp>(this.resourceUrl, classification, { observe: 'response' });
  }

  update(classification: IClassificationBlComp): Observable<EntityResponseType> {
    return this.http.put<IClassificationBlComp>(
      `${this.resourceUrl}/${this.getClassificationBlCompIdentifier(classification)}`,
      classification,
      { observe: 'response' }
    );
  }

  partialUpdate(classification: PartialUpdateClassificationBlComp): Observable<EntityResponseType> {
    return this.http.patch<IClassificationBlComp>(
      `${this.resourceUrl}/${this.getClassificationBlCompIdentifier(classification)}`,
      classification,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClassificationBlComp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClassificationBlComp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getClassificationBlCompIdentifier(classification: Pick<IClassificationBlComp, 'id'>): number {
    return classification.id;
  }

  compareClassificationBlComp(o1: Pick<IClassificationBlComp, 'id'> | null, o2: Pick<IClassificationBlComp, 'id'> | null): boolean {
    return o1 && o2 ? this.getClassificationBlCompIdentifier(o1) === this.getClassificationBlCompIdentifier(o2) : o1 === o2;
  }

  addClassificationBlCompToCollectionIfMissing<Type extends Pick<IClassificationBlComp, 'id'>>(
    classificationCollection: Type[],
    ...classificationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const classifications: Type[] = classificationsToCheck.filter(isPresent);
    if (classifications.length > 0) {
      const classificationCollectionIdentifiers = classificationCollection.map(
        classificationItem => this.getClassificationBlCompIdentifier(classificationItem)!
      );
      const classificationsToAdd = classifications.filter(classificationItem => {
        const classificationIdentifier = this.getClassificationBlCompIdentifier(classificationItem);
        if (classificationCollectionIdentifiers.includes(classificationIdentifier)) {
          return false;
        }
        classificationCollectionIdentifiers.push(classificationIdentifier);
        return true;
      });
      return [...classificationsToAdd, ...classificationCollection];
    }
    return classificationCollection;
  }
}
