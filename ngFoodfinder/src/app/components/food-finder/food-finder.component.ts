import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ServiceLocation } from 'src/app/models/service-location';
import { ServiceLocationService } from 'src/app/services/service-location.service';
import { LocationDetailsModalComponent } from '../location-details-modal/location-details-modal.component';

@Component({
  selector: 'app-food-finder',
  templateUrl: './food-finder.component.html',
  styleUrls: ['./food-finder.component.css']
})
export class FoodFinderComponent implements OnInit {
  serviceLocations: ServiceLocation[] = []; // An empty array that will end up holding 'ground truth' data from service
  selected : ServiceLocation | null = null;

  constructor(
    private serviceLocationService: ServiceLocationService,
    private datePipe: DatePipe,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.reload(); // We want to ensure we start with the latest copy of our data

  }
  reload() {
    this.serviceLocationService.index().subscribe(
      (data) => (this.serviceLocations = data),
      (err) => console.error('Failed: ' + err)
    );
    // this.newTodo = new Todo();
  }

  showDetails(id : number) {
    this.serviceLocationService.show(id).subscribe(
      (data) => {
        this.selected = data;
        const detailsModal = this.modalService.open(LocationDetailsModalComponent);
        detailsModal.componentInstance.serviceLocation = data;
      },
      (err) => console.error('Failed: ' + err)
    );
  }

  showMap() {

  }

}
