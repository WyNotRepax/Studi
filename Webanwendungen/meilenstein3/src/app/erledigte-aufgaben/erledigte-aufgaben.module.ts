import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ErledigteAufgabenPageRoutingModule } from './erledigte-aufgaben-routing.module';

import { ErledigteAufgabenPage } from './erledigte-aufgaben.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ErledigteAufgabenPageRoutingModule
  ],
  declarations: [ErledigteAufgabenPage]
})
export class ErledigteAufgabenPageModule {}
