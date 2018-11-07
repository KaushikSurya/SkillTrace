import { Component, OnInit } from '@angular/core';
import { EmployeeSkills } from '../../models/employee-skills';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeSkillsService } from './../../services/employee-skills.service';
import { LoginService } from './../../../home/services/login.service';
import { EmployeeService } from './../../services/employee.service';
import { Employee } from './../../models/employee';
import { SkillRequest } from './../../models/skill-request';
import { SkillRequestService } from './../../services/skill-request.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  empSkills: EmployeeSkills[];
  employeeSkills: EmployeeSkills[];
  field: string;
  srchValue: string;
  length: number;
  employee: Employee;
  skillRequests: SkillRequest[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private employeeSkillsService: EmployeeSkillsService,
    private skillRequestService: SkillRequestService,
    private loginService: LoginService,
    private employeeService: EmployeeService,
    private router: Router
  ) {
    this.field = 'employeeId';
    this.srchValue = '' + loginService.getEmployeeId();
  }

  ngOnInit() {

    this.employeeService.getEmployeeById(this.loginService.getEmployeeId()).subscribe(
      (data) => this.employee = data,
      (err) => this.employee = undefined
    );

    /*this.employeeSkillsService.getEmployeeSkillsById(this.loginService.getEmployeeId()).subscribe(
      (data) => this.empSkills = data,
      (err) => this.empSkills = undefined
    );*/

    this.skillRequestService.searchSkillRequests(this.field, this.srchValue).subscribe(
      (data) => this.skillRequests = data,
      (err) => this.skillRequests = undefined
    );

    //console.log(this.employee);

    this.activatedRoute.queryParams.subscribe(
      (params) => {

        console.log(this.field + ':' + this.srchValue);

        if (this.field && this.srchValue) {
          this.employeeSkillsService.searchEmployeeSkills(this.field, this.srchValue).subscribe(
            (data) => this.employeeSkills = data,
            (err) => this.employeeSkills = undefined
          );
        } else {
          this.employeeSkillsService.searchEmployeeSkills(this.field, this.srchValue).subscribe(
            (data) => this.employeeSkills = data,
            (err) => this.employeeSkills = undefined
          );
        }
      },
      (err) => this.employeeSkills = undefined
    );
  }

  doDelete(requestId: number) {
    this.skillRequestService.deleteSkillRequestById(requestId).subscribe(
      (resp) => {
        if (resp.ok) {
          this.router.navigateByUrl('requestSkill');
        }
      }
    );
  }

}
