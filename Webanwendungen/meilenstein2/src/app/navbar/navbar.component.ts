import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {


  constructor(public location: Location) {
  }

  isMenuCollapsed = true;

  ngOnInit(): void {

  }

  getLocation(){
    return this.location.path().length === 0 ? 'home' : this.location.path().substring(1);
  }

}
