export class Address {

  id : number;
  streetAddress : string;
  city : string;
  state : string;
  zip : string;

  constructor(
    id : number = 0,
    streetAddress : string = '',
    city : string = '',
    state : string = '',
    zip : string = ''
  ) {
    this.id = id;
    this.streetAddress = streetAddress;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

}
