import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-newsletter',
  templateUrl: './newsletter.component.html',
  styleUrls: ['./newsletter.component.css']
})
export class NewsletterComponent implements OnInit {

contentImage: string = '../../assets/newspaper.jpeg';

  constructor() { }

  ngOnInit(): void {
  }

  imageShow(imageUrl: string) {
    // document.getElementById('image').style.display = "block";
    this.contentImage = imageUrl;
    }

}


