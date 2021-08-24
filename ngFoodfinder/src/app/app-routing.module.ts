import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { FoodFinderComponent } from './components/food-finder/food-finder.component';
import { NewsletterComponent } from './components/newsletter/newsletter.component';
import { MapComponent } from './components/map/map.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MissionComponent } from './components/mission/mission.component';
import { AboutComponent } from './components/about/about.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'foodfinder', component: FoodFinderComponent },
  { path: 'newsletter', component: NewsletterComponent },
  { path: 'map', component: MapComponent},
  { path: 'missionStatement', component: MissionComponent },
  { path: 'about', component: AboutComponent },
  { path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
})
export class AppRoutingModule {}
