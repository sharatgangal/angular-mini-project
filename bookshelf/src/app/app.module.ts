import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule, ToastrService } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms'
import {HttpClientModule } from '@angular/common/http';
import { SearchService } from './services/search.service'
import { BookService } from './services/book.service';
import { SearchComponent } from './search/search.component';
import { RecommendedComponent } from './recommended/recommended.component';
import { DashboardComponent } from './dashboard/dashboard.component'
import { UserService } from '../app/services/user.service'


import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UpdateUserComponent } from './update-user/update-user.component';
@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    RecommendedComponent,
    DashboardComponent,
    HeaderComponent,
    FooterComponent,
    IndexComponent,
    LoginComponent,
    RegisterComponent,
    UpdateUserComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot({
       progressBar: true,
      timeOut: 1000,
      positionClass: 'toast-top-center',
      preventDuplicates: false,
      }),
    
  
  ],
  providers: [SearchService,BookService,ToastrService,UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
