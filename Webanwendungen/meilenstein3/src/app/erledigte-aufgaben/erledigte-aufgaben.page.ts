import { Component, OnInit } from '@angular/core';
import { AufgabenStorageService } from '../aufgaben-storage/aufgaben-storage.service';

@Component({
  selector: 'app-erledigte-aufgaben',
  templateUrl: './erledigte-aufgaben.page.html',
  styleUrls: ['./erledigte-aufgaben.page.scss'],
})
export class ErledigteAufgabenPage implements OnInit {

  constructor(public aufgabenService: AufgabenStorageService) { }

  ngOnInit() {
  }

}
