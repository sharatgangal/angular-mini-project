import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecommendedComponent } from './recommended/recommended.component';
import { SearchComponent } from './search/search.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { RoutingService } from './services/routing.service';
import { UpdateUserComponent } from './update-user/update-user.component';
const routes: Routes = [
  {
    canActivate:[RoutingService], path:"display" ,component:RecommendedComponent
  },
  {
    path:"search" , component:SearchComponent
  },
  {
    canActivate:[RoutingService], path:"dashboard" , component:DashboardComponent
  },
  {
    path:"home" , component:IndexComponent
  },
  {
    path:"login" , component:LoginComponent
  },
  {
    path:"", component:LoginComponent
  },
  {
    path:"register", component:RegisterComponent
  },
  {
    canActivate:[RoutingService], path:"account",component:UpdateUserComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
