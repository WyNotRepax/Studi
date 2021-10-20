import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { AufgabenStorageService } from '../aufgaben-storage/aufgaben-storage.service';

@Component({
  selector: 'app-add-aufgabe',
  templateUrl: './add-aufgabe.page.html',
  styleUrls: ['./add-aufgabe.page.scss'],
})
export class AddAufgabePage implements OnInit {
  constructor(private aufgabeStorageService: AufgabenStorageService, private modalController: ModalController) { }

  public dismiss() {
    this.modalController.dismiss();
  }

  public submit(aufgabe: string) {
    this.aufgabeStorageService.addAufgabe(aufgabe);
    this.dismiss();
  }

  ngOnInit() {
  }

}
