import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IRecordBlComp, NewRecordBlComp } from '../record-bl-comp.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRecordBlComp for edit and NewRecordBlCompFormGroupInput for create.
 */
type RecordBlCompFormGroupInput = IRecordBlComp | PartialWithRequiredKeyOf<NewRecordBlComp>;

type RecordBlCompFormDefaults = Pick<NewRecordBlComp, 'id'>;

type RecordBlCompFormGroupContent = {
  id: FormControl<IRecordBlComp['id'] | NewRecordBlComp['id']>;
  blRecordId: FormControl<IRecordBlComp['blRecordId']>;
  typeOfResource: FormControl<IRecordBlComp['typeOfResource']>;
  contentType: FormControl<IRecordBlComp['contentType']>;
  materialType: FormControl<IRecordBlComp['materialType']>;
  bnbNumber: FormControl<IRecordBlComp['bnbNumber']>;
  archivalResourceKey: FormControl<IRecordBlComp['archivalResourceKey']>;
  isbn: FormControl<IRecordBlComp['isbn']>;
  name: FormControl<IRecordBlComp['name']>;
  datesAssociatedWithName: FormControl<IRecordBlComp['datesAssociatedWithName']>;
  typeOfName: FormControl<IRecordBlComp['typeOfName']>;
  role: FormControl<IRecordBlComp['role']>;
  allNames: FormControl<IRecordBlComp['allNames']>;
  title: FormControl<IRecordBlComp['title']>;
  variantTitles: FormControl<IRecordBlComp['variantTitles']>;
  seriesTitle: FormControl<IRecordBlComp['seriesTitle']>;
  numberWithinSeries: FormControl<IRecordBlComp['numberWithinSeries']>;
  countryOfPublication: FormControl<IRecordBlComp['countryOfPublication']>;
  placeOfPublication: FormControl<IRecordBlComp['placeOfPublication']>;
  publisher: FormControl<IRecordBlComp['publisher']>;
  dateOfPublication: FormControl<IRecordBlComp['dateOfPublication']>;
  edition: FormControl<IRecordBlComp['edition']>;
  physicalDescription: FormControl<IRecordBlComp['physicalDescription']>;
  deweyClassification: FormControl<IRecordBlComp['deweyClassification']>;
  blShelfmark: FormControl<IRecordBlComp['blShelfmark']>;
  topics: FormControl<IRecordBlComp['topics']>;
  genre: FormControl<IRecordBlComp['genre']>;
  languages: FormControl<IRecordBlComp['languages']>;
  notes: FormControl<IRecordBlComp['notes']>;
  provenance: FormControl<IRecordBlComp['provenance']>;
};

export type RecordBlCompFormGroup = FormGroup<RecordBlCompFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RecordBlCompFormService {
  createRecordBlCompFormGroup(record: RecordBlCompFormGroupInput = { id: null }): RecordBlCompFormGroup {
    const recordRawValue = {
      ...this.getFormDefaults(),
      ...record,
    };
    return new FormGroup<RecordBlCompFormGroupContent>({
      id: new FormControl(
        { value: recordRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      blRecordId: new FormControl(recordRawValue.blRecordId),
      typeOfResource: new FormControl(recordRawValue.typeOfResource),
      contentType: new FormControl(recordRawValue.contentType),
      materialType: new FormControl(recordRawValue.materialType),
      bnbNumber: new FormControl(recordRawValue.bnbNumber),
      archivalResourceKey: new FormControl(recordRawValue.archivalResourceKey),
      isbn: new FormControl(recordRawValue.isbn),
      name: new FormControl(recordRawValue.name),
      datesAssociatedWithName: new FormControl(recordRawValue.datesAssociatedWithName),
      typeOfName: new FormControl(recordRawValue.typeOfName),
      role: new FormControl(recordRawValue.role),
      allNames: new FormControl(recordRawValue.allNames),
      title: new FormControl(recordRawValue.title),
      variantTitles: new FormControl(recordRawValue.variantTitles),
      seriesTitle: new FormControl(recordRawValue.seriesTitle),
      numberWithinSeries: new FormControl(recordRawValue.numberWithinSeries),
      countryOfPublication: new FormControl(recordRawValue.countryOfPublication),
      placeOfPublication: new FormControl(recordRawValue.placeOfPublication),
      publisher: new FormControl(recordRawValue.publisher),
      dateOfPublication: new FormControl(recordRawValue.dateOfPublication),
      edition: new FormControl(recordRawValue.edition),
      physicalDescription: new FormControl(recordRawValue.physicalDescription),
      deweyClassification: new FormControl(recordRawValue.deweyClassification),
      blShelfmark: new FormControl(recordRawValue.blShelfmark),
      topics: new FormControl(recordRawValue.topics),
      genre: new FormControl(recordRawValue.genre),
      languages: new FormControl(recordRawValue.languages),
      notes: new FormControl(recordRawValue.notes),
      provenance: new FormControl(recordRawValue.provenance),
    });
  }

  getRecordBlComp(form: RecordBlCompFormGroup): IRecordBlComp | NewRecordBlComp {
    return form.getRawValue() as IRecordBlComp | NewRecordBlComp;
  }

  resetForm(form: RecordBlCompFormGroup, record: RecordBlCompFormGroupInput): void {
    const recordRawValue = { ...this.getFormDefaults(), ...record };
    form.reset(
      {
        ...recordRawValue,
        id: { value: recordRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RecordBlCompFormDefaults {
    return {
      id: null,
    };
  }
}
