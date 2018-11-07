import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

const TOKEN_KEY = 'AuthToken';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  first: string;
  second: string;
  logoUrl: string;
  field: string;
  srchValue: string;

  constructor(
    private router: Router
  ) {
    this.first = 'Skill';
    this.second = 'Trace';
    this.logoUrl = '/assets/images/ski.png';
    this.field = 'skillName';
    this.srchValue = '';
  }

  doSearch() {
    this.router.navigate(['/listSkills'], { queryParams: { field: this.field, srchValue: this.srchValue } });
  }

  doChangeField(field, ele) {
    this.field = field;
    this.srchValue = '';
    switch (field) {
      case 'skillName': ele.placeholder = 'Skill Name'; ele.type = 'text'; break;
      // case 'chargePerMonth': ele.placeholder = 'Charge Per Month'; ele.type = 'number'; break;
      // case 'maxUsage': ele.placeholder = 'Maximum Usage'; ele.type = 'number'; break;
    }
  }

  logout() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    this.router.navigateByUrl('');
  }


}
