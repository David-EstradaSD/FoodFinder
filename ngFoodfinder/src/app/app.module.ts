import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { FoodFinderComponent } from './components/food-finder/food-finder.component';
import { HttpClientModule} from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { AuthService } from './services/auth.service';
import { DonorComponent } from './components/donor/donor.component';
import { RecipientComponent } from './components/recipient/recipient.component';
import { AdminComponent } from './components/admin/admin.component';

import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MapComponent } from './components/map/map.component';
import { AgmCoreModule } from '@agm/core';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MissionComponent } from './components/mission/mission.component';
import { AboutComponent } from './components/about/about.component';
import { NewsletterComponent } from './components/newsletter/newsletter.component';
import { LocationDetailsModalComponent } from './components/location-details-modal/location-details-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    FoodFinderComponent,
    DonorComponent,
    RecipientComponent,
    AdminComponent,
    NavbarComponent,
    FooterComponent,
    NewsletterComponent,
    MapComponent,
    NotFoundComponent,
    MissionComponent,
    AboutComponent,
    LocationDetailsModalComponent

  ],
  imports: [
    AgmCoreModule.forRoot({apiKey:'AIzaSyBf2VmxfBNxs1HkJpnNGHwYL36EM3V9R_U'}),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
  ],
  providers: [
    DatePipe,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
