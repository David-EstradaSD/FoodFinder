import { Service } from "./service";

export class ServiceLocation {

  id: number; // Required
  locationName: string;
  locationPhone: string;
  hours: string;
  description: string;
  createdDateTime: string;
  imageUrl: string;
  user: object;
  address: object; // Required
  ratings: object[];
  comments: object[];
  services: Service[];
  donors: object[];
  recipients: object[];

  constructor(
    id: number = 0,
    locationName: string = '',
    hours: string = '',
    locationPhone: string = '',
    createdDateTime: string = '',
    description: string = '',
    user: object = {},
    imageUrl: string = '',
    ratings: object[] = [],
    address: object = {},
    services: Service[] = [],
    comments: object[] = [],
    recipients: object[] = [],
    donors: object[] = [],

  ) {
    this.id = id;
    this.createdDateTime = createdDateTime;
    this.locationPhone = locationPhone;
    this.description = description;
    this.user = user;
    this.imageUrl = imageUrl;
    this.ratings = ratings;
    this.address = address;
    this.services = services;
    this.comments = comments;
    this.recipients = recipients;
    this.donors = donors;
    this.locationName = locationName;
    this.hours = hours;
  }

}
