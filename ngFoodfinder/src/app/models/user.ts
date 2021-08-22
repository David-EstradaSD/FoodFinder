export class User {

  id: number; // Required
  firstName: string; // Required
  lastName: string; // Required
  phone: string;
  image: string;
  enabled: boolean;
  serviceLocation: object;
  recipients: object[] | null;
  donors: object[] | null;
  email: string; // Required
  username: string; // Required
  password: string; // Required
  role: string;

  constructor(
    id: number = 0,
    firstName: string = '',
    lastName: string = '',
    phone: string = '',
    image: string = '',
    enabled: boolean = false,
    serviceLocation: object = [],
    recipients: object[] | null = [],
    donors: object[] | null = [],
    email: string = '',
    username: string = '',
    password: string = '',
    role: string = ''
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.image = image;
    this.enabled = enabled;
    this.serviceLocation = serviceLocation;
    this.recipients = recipients;
    this.donors = donors;
    this.email = email;
    this.username = username;
    this.password = password;
    this.role = role;
  }

}
