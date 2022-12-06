import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IClassificationBlComp, NewClassificationBlComp } from '../classification-bl-comp.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IClassificationBlComp for edit and NewClassificationBlCompFormGroupInput for create.
 */
type ClassificationBlCompFormGroupInput = IClassificationBlComp | PartialWithRequiredKeyOf<NewClassificationBlComp>;

type ClassificationBlCompFormDefaults = Pick<NewClassificationBlComp, 'id'>;

type ClassificationBlCompFormGroupContent = {
  id: FormControl<IClassificationBlComp['id'] | NewClassificationBlComp['id']>;
  deweyClassification: FormControl<IClassificationBlComp['deweyClassification']>;
  blRecordId: FormControl<IClassificationBlComp['blRecordId']>;
  typeOfResource: FormControl<IClassificationBlComp['typeOfResource']>;
  contentType: FormControl<IClassificationBlComp['contentType']>;
  materialType: FormControl<IClassificationBlComp['materialType']>;
  bnbNumber: FormControl<IClassificationBlComp['bnbNumber']>;
  archivalResourceKey: FormControl<IClassificationBlComp['archivalResourceKey']>;
  isbn: FormControl<IClassificationBlComp['isbn']>;
  name: FormControl<IClassificationBlComp['name']>;
  datesAssociatedWithName: FormControl<IClassificationBlComp['datesAssociatedWithName']>;
  typeOfName: FormControl<IClassificationBlComp['typeOfName']>;
  role: FormControl<IClassificationBlComp['role']>;
  allNames: FormControl<IClassificationBlComp['allNames']>;
  title: FormControl<IClassificationBlComp['title']>;
  variantTitles: FormControl<IClassificationBlComp['variantTitles']>;
  seriesTitle: FormControl<IClassificationBlComp['seriesTitle']>;
  numberWithinSeries: FormControl<IClassificationBlComp['numberWithinSeries']>;
  countryOfPublication: FormControl<IClassificationBlComp['countryOfPublication']>;
  placeOfPublication: FormControl<IClassificationBlComp['placeOfPublication']>;
  publisher: FormControl<IClassificationBlComp['publisher']>;
  dateOfPublication: FormControl<IClassificationBlComp['dateOfPublication']>;
  edition: FormControl<IClassificationBlComp['edition']>;
  physicalDescription: FormControl<IClassificationBlComp['physicalDescription']>;
  blShelfmark: FormControl<IClassificationBlComp['blShelfmark']>;
  topics: FormControl<IClassificationBlComp['topics']>;
  genre: FormControl<IClassificationBlComp['genre']>;
  languages: FormControl<IClassificationBlComp['languages']>;
  notes: FormControl<IClassificationBlComp['notes']>;
  provenance: FormControl<IClassificationBlComp['provenance']>;
};

export type ClassificationBlCompFormGroup = FormGroup<ClassificationBlCompFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ClassificationBlCompFormService {
  createClassificationBlCompFormGroup(classification: ClassificationBlCompFormGroupInput = { id: null }): ClassificationBlCompFormGroup {
    const classificationRawValue = {
      ...this.getFormDefaults(),
      ...classification,
    };
    return new FormGroup<ClassificationBlCompFormGroupContent>({
      id: new FormControl(
        { value: classificationRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deweyClassification: new FormControl(classificationRawValue.deweyClassification),
      blRecordId: new FormControl(classificationRawValue.blRecordId),
      typeOfResource: new FormControl(classificationRawValue.typeOfResource),
      contentType: new FormControl(classificationRawValue.contentType),
      materialType: new FormControl(classificationRawValue.materialType),
      bnbNumber: new FormControl(classificationRawValue.bnbNumber),
      archivalResourceKey: new FormControl(classificationRawValue.archivalResourceKey),
      isbn: new FormControl(classificationRawValue.isbn),
      name: new FormControl(classificationRawValue.name),
      datesAssociatedWithName: new FormControl(classificationRawValue.datesAssociatedWithName),
      typeOfName: new FormControl(classificationRawValue.typeOfName),
      role: new FormControl(classificationRawValue.role),
      allNames: new FormControl(classificationRawValue.allNames),
      title: new FormControl(classificationRawValue.title),
      variantTitles: new FormControl(classificationRawValue.variantTitles),
      seriesTitle: new FormControl(classificationRawValue.seriesTitle),
      numberWithinSeries: new FormControl(classificationRawValue.numberWithinSeries),
      countryOfPublication: new FormControl(classificationRawValue.countryOfPublication),
      placeOfPublication: new FormControl(classificationRawValue.placeOfPublication),
      publisher: new FormControl(classificationRawValue.publisher),
      dateOfPublication: new FormControl(classificationRawValue.dateOfPublication),
      edition: new FormControl(classificationRawValue.edition),
      physicalDescription: new FormControl(classificationRawValue.physicalDescription),
      blShelfmark: new FormControl(classificationRawValue.blShelfmark),
      topics: new FormControl(classificationRawValue.topics),
      genre: new FormControl(classificationRawValue.genre),
      languages: new FormControl(classificationRawValue.languages),
      notes: new FormControl(classificationRawValue.notes),
      provenance: new FormControl(classificationRawValue.provenance),
    });
  }

  getClassificationBlComp(form: ClassificationBlCompFormGroup): IClassificationBlComp | NewClassificationBlComp {
    return form.getRawValue() as IClassificationBlComp | NewClassificationBlComp;
  }

  resetForm(form: ClassificationBlCompFormGroup, classification: ClassificationBlCompFormGroupInput): void {
    const classificationRawValue = { ...this.getFormDefaults(), ...classification };
    form.reset(
      {
        ...classificationRawValue,
        id: { value: classificationRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ClassificationBlCompFormDefaults {
    return {
      id: null,
    };
  }
}
