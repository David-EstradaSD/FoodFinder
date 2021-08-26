import { Component, Input, OnInit } from '@angular/core';
import { Service } from 'src/app/models/service';
import { ServiceLocation } from 'src/app/models/service-location';

@Component({
  selector: 'app-location-details-modal',
  templateUrl: './location-details-modal.component.html',
  styleUrls: ['./location-details-modal.component.css']
})
export class LocationDetailsModalComponent implements OnInit {

  @Input() serviceLocation: ServiceLocation
  @Input() service: Service

  constructor() { }

  ngOnInit(): void {
  }

}
