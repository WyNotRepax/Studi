import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddAufgabePage } from './add-aufgabe.page';

const routes: Routes = [
  {
    path: '',
    component: AddAufgabePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddAufgabePageRoutingModule {}
