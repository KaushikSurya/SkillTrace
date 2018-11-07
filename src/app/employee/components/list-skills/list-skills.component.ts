import { Component, OnInit } from '@angular/core';
import { EmployeeSkills } from '../../models/employee-skills';
import { ActivatedRoute } from '@angular/router';
import { EmployeeSkillsService } from './../../services/employee-skills.service';
import { LoginService } from './../../../home/services/login.service';

@Component({
  selector: 'app-list-skills',
  templateUrl: './list-skills.component.html',
  styleUrls: ['./list-skills.component.css']
})
export class ListSkillsComponent implements OnInit {

  skills: EmployeeSkills[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private employeeSkillsService: EmployeeSkillsService,
    private loginService: LoginService
  ) {

  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(
      (params) => {
        const field = params['field'];
        const srchValue = params['srchValue'];
        console.log(field + ':' + srchValue);

        if (field && srchValue) {

          this.employeeSkillsService.searchEmployeeSkillsByName(field, srchValue).subscribe(
            (data) => this.skills = data,
            (err) => this.skills = undefined
          );
        } else {
          this.employeeSkillsService.searchEmployeeSkills('employeeId', '' + this.loginService.getEmployeeId()).subscribe(
            (data) => this.skills = data,
            (err) => this.skills = undefined
          );
        }
      },
      (err) => this.skills = undefined
    );

  }


}
