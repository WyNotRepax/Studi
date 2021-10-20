import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddAufgabePageRoutingModule } from './add-aufgabe-routing.module';

import { AddAufgabePage } from './add-aufgabe.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddAufgabePageRoutingModule
  ],
  declarations: [AddAufgabePage]
})
export class AddAufgabePageModule {}
