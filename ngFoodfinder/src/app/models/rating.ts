export class Rating {

  id : object;
  rating : number;
  recipient : object;
  serviceLocation : object;

  constructor(
    id : object = {},
    rating : number = 0,
    recipient : object = {},
    serviceLocation : object = {}
  ) {
    this.id = id;
    this.rating = rating;
    this.recipient = recipient;
    this.serviceLocation = serviceLocation;
  }

}
