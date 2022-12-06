import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITopicBlComp, NewTopicBlComp } from '../topic-bl-comp.model';

export type PartialUpdateTopicBlComp = Partial<ITopicBlComp> & Pick<ITopicBlComp, 'id'>;

export type EntityResponseType = HttpResponse<ITopicBlComp>;
export type EntityArrayResponseType = HttpResponse<ITopicBlComp[]>;

@Injectable({ providedIn: 'root' })
export class TopicBlCompService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/topics');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(topic: NewTopicBlComp): Observable<EntityResponseType> {
    return this.http.post<ITopicBlComp>(this.resourceUrl, topic, { observe: 'response' });
  }

  update(topic: ITopicBlComp): Observable<EntityResponseType> {
    return this.http.put<ITopicBlComp>(`${this.resourceUrl}/${this.getTopicBlCompIdentifier(topic)}`, topic, { observe: 'response' });
  }

  partialUpdate(topic: PartialUpdateTopicBlComp): Observable<EntityResponseType> {
    return this.http.patch<ITopicBlComp>(`${this.resourceUrl}/${this.getTopicBlCompIdentifier(topic)}`, topic, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITopicBlComp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITopicBlComp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTopicBlCompIdentifier(topic: Pick<ITopicBlComp, 'id'>): number {
    return topic.id;
  }

  compareTopicBlComp(o1: Pick<ITopicBlComp, 'id'> | null, o2: Pick<ITopicBlComp, 'id'> | null): boolean {
    return o1 && o2 ? this.getTopicBlCompIdentifier(o1) === this.getTopicBlCompIdentifier(o2) : o1 === o2;
  }

  addTopicBlCompToCollectionIfMissing<Type extends Pick<ITopicBlComp, 'id'>>(
    topicCollection: Type[],
    ...topicsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const topics: Type[] = topicsToCheck.filter(isPresent);
    if (topics.length > 0) {
      const topicCollectionIdentifiers = topicCollection.map(topicItem => this.getTopicBlCompIdentifier(topicItem)!);
      const topicsToAdd = topics.filter(topicItem => {
        const topicIdentifier = this.getTopicBlCompIdentifier(topicItem);
        if (topicCollectionIdentifiers.includes(topicIdentifier)) {
          return false;
        }
        topicCollectionIdentifiers.push(topicIdentifier);
        return true;
      });
      return [...topicsToAdd, ...topicCollection];
    }
    return topicCollection;
  }
}
