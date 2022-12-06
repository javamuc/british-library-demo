import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITitleBlComp, NewTitleBlComp } from '../title-bl-comp.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITitleBlComp for edit and NewTitleBlCompFormGroupInput for create.
 */
type TitleBlCompFormGroupInput = ITitleBlComp | PartialWithRequiredKeyOf<NewTitleBlComp>;

type TitleBlCompFormDefaults = Pick<NewTitleBlComp, 'id'>;

type TitleBlCompFormGroupContent = {
  id: FormControl<ITitleBlComp['id'] | NewTitleBlComp['id']>;
  title: FormControl<ITitleBlComp['title']>;
  otherTitles: FormControl<ITitleBlComp['otherTitles']>;
  blRecordId: FormControl<ITitleBlComp['blRecordId']>;
  typeOfResource: FormControl<ITitleBlComp['typeOfResource']>;
  contentType: FormControl<ITitleBlComp['contentType']>;
  materialType: FormControl<ITitleBlComp['materialType']>;
  bnbNumber: FormControl<ITitleBlComp['bnbNumber']>;
  archivalResourceKey: FormControl<ITitleBlComp['archivalResourceKey']>;
  isbn: FormControl<ITitleBlComp['isbn']>;
  name: FormControl<ITitleBlComp['name']>;
  datesAssociatedWithName: FormControl<ITitleBlComp['datesAssociatedWithName']>;
  typeOfName: FormControl<ITitleBlComp['typeOfName']>;
  role: FormControl<ITitleBlComp['role']>;
  allNames: FormControl<ITitleBlComp['allNames']>;
  seriesTitle: FormControl<ITitleBlComp['seriesTitle']>;
  numberWithinSeries: FormControl<ITitleBlComp['numberWithinSeries']>;
  countryOfPublication: FormControl<ITitleBlComp['countryOfPublication']>;
  placeOfPublication: FormControl<ITitleBlComp['placeOfPublication']>;
  publisher: FormControl<ITitleBlComp['publisher']>;
  dateOfPublication: FormControl<ITitleBlComp['dateOfPublication']>;
  edition: FormControl<ITitleBlComp['edition']>;
  physicalDescription: FormControl<ITitleBlComp['physicalDescription']>;
  deweyClassification: FormControl<ITitleBlComp['deweyClassification']>;
  blShelfmark: FormControl<ITitleBlComp['blShelfmark']>;
  topics: FormControl<ITitleBlComp['topics']>;
  genre: FormControl<ITitleBlComp['genre']>;
  languages: FormControl<ITitleBlComp['languages']>;
  notes: FormControl<ITitleBlComp['notes']>;
  provenance: FormControl<ITitleBlComp['provenance']>;
};

export type TitleBlCompFormGroup = FormGroup<TitleBlCompFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TitleBlCompFormService {
  createTitleBlCompFormGroup(title: TitleBlCompFormGroupInput = { id: null }): TitleBlCompFormGroup {
    const titleRawValue = {
      ...this.getFormDefaults(),
      ...title,
    };
    return new FormGroup<TitleBlCompFormGroupContent>({
      id: new FormControl(
        { value: titleRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      title: new FormControl(titleRawValue.title),
      otherTitles: new FormControl(titleRawValue.otherTitles),
      blRecordId: new FormControl(titleRawValue.blRecordId),
      typeOfResource: new FormControl(titleRawValue.typeOfResource),
      contentType: new FormControl(titleRawValue.contentType),
      materialType: new FormControl(titleRawValue.materialType),
      bnbNumber: new FormControl(titleRawValue.bnbNumber),
      archivalResourceKey: new FormControl(titleRawValue.archivalResourceKey),
      isbn: new FormControl(titleRawValue.isbn),
      name: new FormControl(titleRawValue.name),
      datesAssociatedWithName: new FormControl(titleRawValue.datesAssociatedWithName),
      typeOfName: new FormControl(titleRawValue.typeOfName),
      role: new FormControl(titleRawValue.role),
      allNames: new FormControl(titleRawValue.allNames),
      seriesTitle: new FormControl(titleRawValue.seriesTitle),
      numberWithinSeries: new FormControl(titleRawValue.numberWithinSeries),
      countryOfPublication: new FormControl(titleRawValue.countryOfPublication),
      placeOfPublication: new FormControl(titleRawValue.placeOfPublication),
      publisher: new FormControl(titleRawValue.publisher),
      dateOfPublication: new FormControl(titleRawValue.dateOfPublication),
      edition: new FormControl(titleRawValue.edition),
      physicalDescription: new FormControl(titleRawValue.physicalDescription),
      deweyClassification: new FormControl(titleRawValue.deweyClassification),
      blShelfmark: new FormControl(titleRawValue.blShelfmark),
      topics: new FormControl(titleRawValue.topics),
      genre: new FormControl(titleRawValue.genre),
      languages: new FormControl(titleRawValue.languages),
      notes: new FormControl(titleRawValue.notes),
      provenance: new FormControl(titleRawValue.provenance),
    });
  }

  getTitleBlComp(form: TitleBlCompFormGroup): ITitleBlComp | NewTitleBlComp {
    return form.getRawValue() as ITitleBlComp | NewTitleBlComp;
  }

  resetForm(form: TitleBlCompFormGroup, title: TitleBlCompFormGroupInput): void {
    const titleRawValue = { ...this.getFormDefaults(), ...title };
    form.reset(
      {
        ...titleRawValue,
        id: { value: titleRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TitleBlCompFormDefaults {
    return {
      id: null,
    };
  }
}
