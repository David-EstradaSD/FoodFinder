export class Service {

  id : number;
  serviceName : string;
  description : string;
  serviceLocations : object[] | null;

  constructor(
    id : number = 0,
    serviceName : string = '',
    description : string = '',
    serviceLocations : object[] | null = []
  ) {
    this.id = id;
    this.serviceName = serviceName;
    this.description = description;
    this.serviceLocations = serviceLocations;
  }

}
