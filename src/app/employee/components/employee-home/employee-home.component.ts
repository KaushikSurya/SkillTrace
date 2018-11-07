import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
  styleUrls: ['./employee-home.component.css']
})
export class EmployeeHomeComponent {

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
    }
  }

}
