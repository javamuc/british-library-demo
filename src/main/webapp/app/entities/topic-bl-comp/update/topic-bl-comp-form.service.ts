import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITopicBlComp, NewTopicBlComp } from '../topic-bl-comp.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITopicBlComp for edit and NewTopicBlCompFormGroupInput for create.
 */
type TopicBlCompFormGroupInput = ITopicBlComp | PartialWithRequiredKeyOf<NewTopicBlComp>;

type TopicBlCompFormDefaults = Pick<NewTopicBlComp, 'id'>;

type TopicBlCompFormGroupContent = {
  id: FormControl<ITopicBlComp['id'] | NewTopicBlComp['id']>;
  topic: FormControl<ITopicBlComp['topic']>;
  typeOfTopic: FormControl<ITopicBlComp['typeOfTopic']>;
  blRecordId: FormControl<ITopicBlComp['blRecordId']>;
  typeOfResource: FormControl<ITopicBlComp['typeOfResource']>;
  contentType: FormControl<ITopicBlComp['contentType']>;
  materialType: FormControl<ITopicBlComp['materialType']>;
  bnbNumber: FormControl<ITopicBlComp['bnbNumber']>;
  archivalResourceKey: FormControl<ITopicBlComp['archivalResourceKey']>;
  isbn: FormControl<ITopicBlComp['isbn']>;
  name: FormControl<ITopicBlComp['name']>;
  datesAssociatedWithName: FormControl<ITopicBlComp['datesAssociatedWithName']>;
  typeOfName: FormControl<ITopicBlComp['typeOfName']>;
  role: FormControl<ITopicBlComp['role']>;
  allNames: FormControl<ITopicBlComp['allNames']>;
  title: FormControl<ITopicBlComp['title']>;
  variantTitles: FormControl<ITopicBlComp['variantTitles']>;
  seriesTitle: FormControl<ITopicBlComp['seriesTitle']>;
  numberWithinSeries: FormControl<ITopicBlComp['numberWithinSeries']>;
  countryOfPublication: FormControl<ITopicBlComp['countryOfPublication']>;
  placeOfPublication: FormControl<ITopicBlComp['placeOfPublication']>;
  publisher: FormControl<ITopicBlComp['publisher']>;
  dateOfPublication: FormControl<ITopicBlComp['dateOfPublication']>;
  edition: FormControl<ITopicBlComp['edition']>;
  physicalDescription: FormControl<ITopicBlComp['physicalDescription']>;
  deweyClassification: FormControl<ITopicBlComp['deweyClassification']>;
  blShelfmark: FormControl<ITopicBlComp['blShelfmark']>;
  genre: FormControl<ITopicBlComp['genre']>;
  languages: FormControl<ITopicBlComp['languages']>;
  notes: FormControl<ITopicBlComp['notes']>;
  provenance: FormControl<ITopicBlComp['provenance']>;
};

export type TopicBlCompFormGroup = FormGroup<TopicBlCompFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TopicBlCompFormService {
  createTopicBlCompFormGroup(topic: TopicBlCompFormGroupInput = { id: null }): TopicBlCompFormGroup {
    const topicRawValue = {
      ...this.getFormDefaults(),
      ...topic,
    };
    return new FormGroup<TopicBlCompFormGroupContent>({
      id: new FormControl(
        { value: topicRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      topic: new FormControl(topicRawValue.topic),
      typeOfTopic: new FormControl(topicRawValue.typeOfTopic),
      blRecordId: new FormControl(topicRawValue.blRecordId),
      typeOfResource: new FormControl(topicRawValue.typeOfResource),
      contentType: new FormControl(topicRawValue.contentType),
      materialType: new FormControl(topicRawValue.materialType),
      bnbNumber: new FormControl(topicRawValue.bnbNumber),
      archivalResourceKey: new FormControl(topicRawValue.archivalResourceKey),
      isbn: new FormControl(topicRawValue.isbn),
      name: new FormControl(topicRawValue.name),
      datesAssociatedWithName: new FormControl(topicRawValue.datesAssociatedWithName),
      typeOfName: new FormControl(topicRawValue.typeOfName),
      role: new FormControl(topicRawValue.role),
      allNames: new FormControl(topicRawValue.allNames),
      title: new FormControl(topicRawValue.title),
      variantTitles: new FormControl(topicRawValue.variantTitles),
      seriesTitle: new FormControl(topicRawValue.seriesTitle),
      numberWithinSeries: new FormControl(topicRawValue.numberWithinSeries),
      countryOfPublication: new FormControl(topicRawValue.countryOfPublication),
      placeOfPublication: new FormControl(topicRawValue.placeOfPublication),
      publisher: new FormControl(topicRawValue.publisher),
      dateOfPublication: new FormControl(topicRawValue.dateOfPublication),
      edition: new FormControl(topicRawValue.edition),
      physicalDescription: new FormControl(topicRawValue.physicalDescription),
      deweyClassification: new FormControl(topicRawValue.deweyClassification),
      blShelfmark: new FormControl(topicRawValue.blShelfmark),
      genre: new FormControl(topicRawValue.genre),
      languages: new FormControl(topicRawValue.languages),
      notes: new FormControl(topicRawValue.notes),
      provenance: new FormControl(topicRawValue.provenance),
    });
  }

  getTopicBlComp(form: TopicBlCompFormGroup): ITopicBlComp | NewTopicBlComp {
    return form.getRawValue() as ITopicBlComp | NewTopicBlComp;
  }

  resetForm(form: TopicBlCompFormGroup, topic: TopicBlCompFormGroupInput): void {
    const topicRawValue = { ...this.getFormDefaults(), ...topic };
    form.reset(
      {
        ...topicRawValue,
        id: { value: topicRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TopicBlCompFormDefaults {
    return {
      id: null,
    };
  }
}
