import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AufgabenPage } from './aufgaben.page';

const routes: Routes = [
  {
    path: '',
    component: AufgabenPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AufgabenPageRoutingModule { }
