import { User } from "./user";
import { ServiceLocation } from "./service-location";

export class Comment {
  id: number;
  comment : string;
  privateComment : boolean;
  createDate : string | null;
  serviceLocation : object;
  recipient : object;

  constructor(
    id : number = 0,
    comment : string = '',
    privateComment : boolean = false,
    createDate : string = '',
    serviceLocation : object = {},
    recipient : object = {}
  ) {
    this.id = id;
    this.comment = comment;
    this.privateComment = privateComment;
    this.createDate = createDate;
    this.serviceLocation = serviceLocation;
    this.recipient =recipient;
  }

}

