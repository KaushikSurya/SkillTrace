import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

const TOKEN_KEY = 'AuthToken';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
    
  }

  logout() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    this.router.navigateByUrl('');
  }

}
