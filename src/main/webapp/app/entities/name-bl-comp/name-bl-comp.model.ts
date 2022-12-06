export interface INameBlComp {
  id: number;
  name?: string | null;
  datesAssociatedWithName?: string | null;
  typeOfName?: string | null;
  role?: string | null;
  otherNames?: string | null;
  blRecordId?: string | null;
  typeOfResource?: string | null;
  contentType?: string | null;
  materialType?: string | null;
  bnbNumber?: string | null;
  archivalResourceKey?: string | null;
  isbn?: string | null;
  title?: string | null;
  variantTitles?: string | null;
  seriesTitle?: string | null;
  numberWithinSeries?: string | null;
  countryOfPublication?: string | null;
  placeOfPublication?: string | null;
  publisher?: string | null;
  dateOfPublication?: string | null;
  edition?: string | null;
  physicalDescription?: string | null;
  deweyClassification?: string | null;
  blShelfmark?: string | null;
  topics?: string | null;
  genre?: string | null;
  languages?: string | null;
  notes?: string | null;
  provenance?: string | null;
}

export type NewNameBlComp = Omit<INameBlComp, 'id'> & { id: null };
