import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceLocation } from 'src/app/models/service-location';
import { ServiceLocationService } from 'src/app/services/service-location.service';

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
    private router: Router
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
    this.serviceLocationService.show(this.selected.id).subscribe(
      (data) => (this.selected = data),
      (err) => console.error('Failed: ' + err)
    );
  }

}
