import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { INameBlComp, NewNameBlComp } from '../name-bl-comp.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts INameBlComp for edit and NewNameBlCompFormGroupInput for create.
 */
type NameBlCompFormGroupInput = INameBlComp | PartialWithRequiredKeyOf<NewNameBlComp>;

type NameBlCompFormDefaults = Pick<NewNameBlComp, 'id'>;

type NameBlCompFormGroupContent = {
  id: FormControl<INameBlComp['id'] | NewNameBlComp['id']>;
  name: FormControl<INameBlComp['name']>;
  datesAssociatedWithName: FormControl<INameBlComp['datesAssociatedWithName']>;
  typeOfName: FormControl<INameBlComp['typeOfName']>;
  role: FormControl<INameBlComp['role']>;
  otherNames: FormControl<INameBlComp['otherNames']>;
  blRecordId: FormControl<INameBlComp['blRecordId']>;
  typeOfResource: FormControl<INameBlComp['typeOfResource']>;
  contentType: FormControl<INameBlComp['contentType']>;
  materialType: FormControl<INameBlComp['materialType']>;
  bnbNumber: FormControl<INameBlComp['bnbNumber']>;
  archivalResourceKey: FormControl<INameBlComp['archivalResourceKey']>;
  isbn: FormControl<INameBlComp['isbn']>;
  title: FormControl<INameBlComp['title']>;
  variantTitles: FormControl<INameBlComp['variantTitles']>;
  seriesTitle: FormControl<INameBlComp['seriesTitle']>;
  numberWithinSeries: FormControl<INameBlComp['numberWithinSeries']>;
  countryOfPublication: FormControl<INameBlComp['countryOfPublication']>;
  placeOfPublication: FormControl<INameBlComp['placeOfPublication']>;
  publisher: FormControl<INameBlComp['publisher']>;
  dateOfPublication: FormControl<INameBlComp['dateOfPublication']>;
  edition: FormControl<INameBlComp['edition']>;
  physicalDescription: FormControl<INameBlComp['physicalDescription']>;
  deweyClassification: FormControl<INameBlComp['deweyClassification']>;
  blShelfmark: FormControl<INameBlComp['blShelfmark']>;
  topics: FormControl<INameBlComp['topics']>;
  genre: FormControl<INameBlComp['genre']>;
  languages: FormControl<INameBlComp['languages']>;
  notes: FormControl<INameBlComp['notes']>;
  provenance: FormControl<INameBlComp['provenance']>;
};

export type NameBlCompFormGroup = FormGroup<NameBlCompFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class NameBlCompFormService {
  createNameBlCompFormGroup(name: NameBlCompFormGroupInput = { id: null }): NameBlCompFormGroup {
    const nameRawValue = {
      ...this.getFormDefaults(),
      ...name,
    };
    return new FormGroup<NameBlCompFormGroupContent>({
      id: new FormControl(
        { value: nameRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(nameRawValue.name),
      datesAssociatedWithName: new FormControl(nameRawValue.datesAssociatedWithName),
      typeOfName: new FormControl(nameRawValue.typeOfName),
      role: new FormControl(nameRawValue.role),
      otherNames: new FormControl(nameRawValue.otherNames),
      blRecordId: new FormControl(nameRawValue.blRecordId),
      typeOfResource: new FormControl(nameRawValue.typeOfResource),
      contentType: new FormControl(nameRawValue.contentType),
      materialType: new FormControl(nameRawValue.materialType),
      bnbNumber: new FormControl(nameRawValue.bnbNumber),
      archivalResourceKey: new FormControl(nameRawValue.archivalResourceKey),
      isbn: new FormControl(nameRawValue.isbn),
      title: new FormControl(nameRawValue.title),
      variantTitles: new FormControl(nameRawValue.variantTitles),
      seriesTitle: new FormControl(nameRawValue.seriesTitle),
      numberWithinSeries: new FormControl(nameRawValue.numberWithinSeries),
      countryOfPublication: new FormControl(nameRawValue.countryOfPublication),
      placeOfPublication: new FormControl(nameRawValue.placeOfPublication),
      publisher: new FormControl(nameRawValue.publisher),
      dateOfPublication: new FormControl(nameRawValue.dateOfPublication),
      edition: new FormControl(nameRawValue.edition),
      physicalDescription: new FormControl(nameRawValue.physicalDescription),
      deweyClassification: new FormControl(nameRawValue.deweyClassification),
      blShelfmark: new FormControl(nameRawValue.blShelfmark),
      topics: new FormControl(nameRawValue.topics),
      genre: new FormControl(nameRawValue.genre),
      languages: new FormControl(nameRawValue.languages),
      notes: new FormControl(nameRawValue.notes),
      provenance: new FormControl(nameRawValue.provenance),
    });
  }

  getNameBlComp(form: NameBlCompFormGroup): INameBlComp | NewNameBlComp {
    return form.getRawValue() as INameBlComp | NewNameBlComp;
  }

  resetForm(form: NameBlCompFormGroup, name: NameBlCompFormGroupInput): void {
    const nameRawValue = { ...this.getFormDefaults(), ...name };
    form.reset(
      {
        ...nameRawValue,
        id: { value: nameRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): NameBlCompFormDefaults {
    return {
      id: null,
    };
  }
}
