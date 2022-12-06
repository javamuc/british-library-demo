import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INameBlComp, NewNameBlComp } from '../name-bl-comp.model';

export type PartialUpdateNameBlComp = Partial<INameBlComp> & Pick<INameBlComp, 'id'>;

export type EntityResponseType = HttpResponse<INameBlComp>;
export type EntityArrayResponseType = HttpResponse<INameBlComp[]>;

@Injectable({ providedIn: 'root' })
export class NameBlCompService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/names');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(name: NewNameBlComp): Observable<EntityResponseType> {
    return this.http.post<INameBlComp>(this.resourceUrl, name, { observe: 'response' });
  }

  update(name: INameBlComp): Observable<EntityResponseType> {
    return this.http.put<INameBlComp>(`${this.resourceUrl}/${this.getNameBlCompIdentifier(name)}`, name, { observe: 'response' });
  }

  partialUpdate(name: PartialUpdateNameBlComp): Observable<EntityResponseType> {
    return this.http.patch<INameBlComp>(`${this.resourceUrl}/${this.getNameBlCompIdentifier(name)}`, name, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INameBlComp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INameBlComp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getNameBlCompIdentifier(name: Pick<INameBlComp, 'id'>): number {
    return name.id;
  }

  compareNameBlComp(o1: Pick<INameBlComp, 'id'> | null, o2: Pick<INameBlComp, 'id'> | null): boolean {
    return o1 && o2 ? this.getNameBlCompIdentifier(o1) === this.getNameBlCompIdentifier(o2) : o1 === o2;
  }

  addNameBlCompToCollectionIfMissing<Type extends Pick<INameBlComp, 'id'>>(
    nameCollection: Type[],
    ...namesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const names: Type[] = namesToCheck.filter(isPresent);
    if (names.length > 0) {
      const nameCollectionIdentifiers = nameCollection.map(nameItem => this.getNameBlCompIdentifier(nameItem)!);
      const namesToAdd = names.filter(nameItem => {
        const nameIdentifier = this.getNameBlCompIdentifier(nameItem);
        if (nameCollectionIdentifiers.includes(nameIdentifier)) {
          return false;
        }
        nameCollectionIdentifiers.push(nameIdentifier);
        return true;
      });
      return [...namesToAdd, ...nameCollection];
    }
    return nameCollection;
  }
}
