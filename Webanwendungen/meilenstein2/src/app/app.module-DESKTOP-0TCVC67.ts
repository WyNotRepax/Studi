import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

import { NavbarComponent } from './navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AllBooksComponent } from './all-books/all-books.component';
import { HomeComponent } from './home/home.component';
import { MyBooksComponent } from './my-books/my-books.component';
import { ProfileComponent } from './profile/profile.component';
import { BookTableComponent } from './book-table/book-table.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AllBooksComponent,
    HomeComponent,
    MyBooksComponent,
    ProfileComponent,
    BookTableComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
