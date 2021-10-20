import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AufgabenPageRoutingModule } from './aufgaben-routing.module';

import { AufgabenPage } from './aufgaben.page';
import { AufgabenStorageService } from '../aufgaben-storage/aufgaben-storage.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AufgabenPageRoutingModule
  ],
  declarations: [AufgabenPage]
})
export class AufgabenPageModule {
}
