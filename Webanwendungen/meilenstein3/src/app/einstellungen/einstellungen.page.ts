import { Component, OnInit } from '@angular/core';
import { AufgabenStorageService } from '../aufgaben-storage/aufgaben-storage.service';

@Component({
  selector: 'app-einstellungen',
  templateUrl: './einstellungen.page.html',
  styleUrls: ['./einstellungen.page.scss'],
})
export class EinstellungenPage implements OnInit {

  constructor(public aufgabenService: AufgabenStorageService) {

  }

  ngOnInit() {
  }

}
