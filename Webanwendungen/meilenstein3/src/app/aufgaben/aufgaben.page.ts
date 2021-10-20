import { Component, OnInit } from '@angular/core';
import { AufgabenStorageService } from '../aufgaben-storage/aufgaben-storage.service';
import { AddAufgabePage } from '../add-aufgabe/add-aufgabe.page';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-aufgaben',
  templateUrl: './aufgaben.page.html',
  styleUrls: ['./aufgaben.page.scss'],
})
export class AufgabenPage implements OnInit {

  constructor(public aufgabenService: AufgabenStorageService, public modalController: ModalController) {

  }

  async presentModal() {
    const modal = await this.modalController.create({
      component: AddAufgabePage
    });
    return await modal.present();
  }

  ngOnInit() {
  }

}
