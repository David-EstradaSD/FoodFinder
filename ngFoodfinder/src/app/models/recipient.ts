export class Recipient {

id: number;
user: number;
address: number;
comment: object [] | null;
serviceLocation: object [] | null;
rating: object [] | null;

constructor (
  id: number = 0,
  user: number = 0,
  address: number = 0,
  comment: object[] | null = [],
  serviceLocation: object[] | null = [],
  rating: object[] | null = []
) {

  this.id = id;
  this.user = user;
  this.address = address;
  this.comment = comment;
  this.serviceLocation = serviceLocation;
  this.rating = rating;
}
}
