import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

latitude: number = 39.7;
longitude: number = -104.7;


  constructor() {
  }

  ngOnInit(): void {

  }

}
