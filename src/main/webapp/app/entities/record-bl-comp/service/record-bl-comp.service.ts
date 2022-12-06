import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRecordBlComp, NewRecordBlComp } from '../record-bl-comp.model';

export type PartialUpdateRecordBlComp = Partial<IRecordBlComp> & Pick<IRecordBlComp, 'id'>;

export type EntityResponseType = HttpResponse<IRecordBlComp>;
export type EntityArrayResponseType = HttpResponse<IRecordBlComp[]>;

@Injectable({ providedIn: 'root' })
export class RecordBlCompService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/records');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(record: NewRecordBlComp): Observable<EntityResponseType> {
    return this.http.post<IRecordBlComp>(this.resourceUrl, record, { observe: 'response' });
  }

  update(record: IRecordBlComp): Observable<EntityResponseType> {
    return this.http.put<IRecordBlComp>(`${this.resourceUrl}/${this.getRecordBlCompIdentifier(record)}`, record, { observe: 'response' });
  }

  partialUpdate(record: PartialUpdateRecordBlComp): Observable<EntityResponseType> {
    return this.http.patch<IRecordBlComp>(`${this.resourceUrl}/${this.getRecordBlCompIdentifier(record)}`, record, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRecordBlComp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRecordBlComp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRecordBlCompIdentifier(record: Pick<IRecordBlComp, 'id'>): number {
    return record.id;
  }

  compareRecordBlComp(o1: Pick<IRecordBlComp, 'id'> | null, o2: Pick<IRecordBlComp, 'id'> | null): boolean {
    return o1 && o2 ? this.getRecordBlCompIdentifier(o1) === this.getRecordBlCompIdentifier(o2) : o1 === o2;
  }

  addRecordBlCompToCollectionIfMissing<Type extends Pick<IRecordBlComp, 'id'>>(
    recordCollection: Type[],
    ...recordsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const records: Type[] = recordsToCheck.filter(isPresent);
    if (records.length > 0) {
      const recordCollectionIdentifiers = recordCollection.map(recordItem => this.getRecordBlCompIdentifier(recordItem)!);
      const recordsToAdd = records.filter(recordItem => {
        const recordIdentifier = this.getRecordBlCompIdentifier(recordItem);
        if (recordCollectionIdentifiers.includes(recordIdentifier)) {
          return false;
        }
        recordCollectionIdentifiers.push(recordIdentifier);
        return true;
      });
      return [...recordsToAdd, ...recordCollection];
    }
    return recordCollection;
  }
}
