import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllBooksComponent } from './all-books/all-books.component'
import { HomeComponent } from './home/home.component'
import { MyBooksComponent } from './my-books/my-books.component'
import { ProfileComponent } from './profile/profile.component'

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'allbooks',
    component: AllBooksComponent
  },
  {
    path: 'mybooks',
    component: MyBooksComponent
  },
  {
    path: 'profile',
    component: ProfileComponent
  }
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
