# [Food Finder](http://18.119.82.56:8080/FoodFinder/#/home)
## Skill Distillery Final Project

## Team Members
* [David Estrada](https://dave-estrada.com/), Repository Owner
* Mick LaGassey
* Shaun Reass, Database Administrator
* Brian Ramirez, SCRUM Master

## Overview

This application provides a platform on which donors and volunteers can populate service locations where recipients in need can receive food and various services throughout the Denver area.  

[Food Finder](http://18.119.82.56:8080/FoodFinder/#/home)

## Technologies Used
* Coding Languages: Java, SQL, JavaScript, HTML5, TypeScript, and CSS
* Object-Oriented Design
* Looping
* Try-Catch Statements
* Exceptions
* Spring Data JPA
* Spring Boot
* Model View Controller (MVC) Pattern
* Gradle
* MySQL Workbench
* Spring Data JPA Repositories
* Angular
* Agile, Iterative, and Pair Programming Development
* Zoom




## How to Run

The Food Finder application is a multi-page website. Upon reaching the landing page, a user sees the background image of several hands forming a heart, inside of which is a prompt asking which type of account they would like to create: 1) donor or 2) recipient.  A navigation bar is at the top of the page with login fields for established users.  There is also a footer at the bottom of the page with a map link displaying donation sites where recipients can receive food and services, links to application creator sites, and resources associated with the site including a newsletter, community partner links, a mission statement, and an about page.  All links in the footer can be accessed without establishing an account.  Lastly, when the user scrolls down on the page they will see an image carousel containing associated donation sites.  

When a user chooses to create a donor account, they are first required to register by providing a username, email, password, first name, last name, address, city, state, and zip code.  After registering their account, the donor is directed to their donor portal page, where they see a background image of a hand reaching for another, and a list of scrollable cards on the right side of the page, each containing information on existing donation sites.  Within the cards are the name and description of the donation sites, an additional details button containing contact information, hours of operation, and additional services at each site, such as shower, shelter, counseling, medical, employment, and lingual services.  Also on each card, is a link to the map location of the associated service site.  A navigation bar and footer is also on the donor portal page which contains everything as the landing page's, minus the login fields in the navigation bar.  

When a user registers a recipient account, the do so by choosing the "recipient" button on the landing page and completing the same information fields as a donor. On the recipient portal, users are able to the same functionality as donors, but they are unable to request establishing service location sites.  

On this version of the application, donor are the only users able to establish service location sites after verification and approval of program administrators.  

## Challenges

During the course of application development, our team was exposed to many new technologies for the first time.  While we were able to ultimately overcome the challenges, it took many hours of coordination and troubleshooting.  The most notable of the challenges was implementing the Google Map to our site.  Despite the several difficulties it presented during development, it was vital to our team to solve due to the benefits it would provide to our clients.  Enabling a user to simply click a map link which displays established locations seemed much easier to us than trying to navigate several pages to find an address for a service location site.  This would be especially difficult for someone in need whose native language is not English.  

In addition to implementing map functionality, our schema design was more complex than any we have encountered thus far.  Specifically, the use of composite keys added additional challenges, but once we were able to successfully implement them, it became clear why they enable easier implementation of complex schemas and site designs.  

## Lessons Learned

During the course of this project, our team was able to overcome hurdles that provided us with good insight going forward as junior developers.  First, we were able to demonstrate how a team of four developers were able to complete a useful, functional website that can help improve the lives of several people in need.  The fact that we were able to do so with team members located in three different countries and three different time zones was a fascinating part of the experience.  

Additionally, while we are proud of the final product we have made, we understand there is much room for improving the overall functionality for the site.  Throughout the course of our application development - and perhaps mostly during the product testing phase - we were able to make a list of additional functionality we would like to implement in future versions of the application.  Integrating transportation services of food and people, disease prevention practices and alerts, and integrated communication between donors, volunteers, and recipients are at the top of that list.  
