import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manager-header',
  templateUrl: './manager-header.component.html',
  styleUrls: ['./manager-header.component.css']
})
export class ManagerHeaderComponent implements OnInit {

  logoUrl: string;

  constructor() {
    //this.logoUrl = "../assets/images/skill.png";
    this.logoUrl = ""
   }

  ngOnInit() {
  }

}
