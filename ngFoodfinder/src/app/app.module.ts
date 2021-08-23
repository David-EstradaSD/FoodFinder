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
import { NavbarFooterComponent } from './components/navbar-footer/navbar-footer.component';



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
    NavbarFooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    DatePipe,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
