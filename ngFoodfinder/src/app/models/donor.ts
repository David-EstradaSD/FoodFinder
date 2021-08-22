export class Donor {

id: number;
category: string;
user: object | null;
address: object | null;
serviceLocation: object[] | null;

constructor(
  id: number = 0,
  category: string = '',
  user: object | null = {},
  address: object | null = {},
  serviceLocation: object[] | null = []
) {

  this.id = id;
  this.category = category;
  this.user = user;
  this.address = address;
  this.serviceLocation = serviceLocation;
}
}

