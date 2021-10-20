import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ErledigteAufgabenPage } from './erledigte-aufgaben.page';

const routes: Routes = [
  {
    path: '',
    component: ErledigteAufgabenPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ErledigteAufgabenPageRoutingModule {}
