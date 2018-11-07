import { Component, OnInit } from '@angular/core';
import { LoginService } from './../../../home/services/login.service';
import { SkillService } from './../../services/skill.service';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeSkillsService } from './../../services/employee-skills.service';
import { Skill } from './../../models/skill';
import { SkillRequestService } from './../../services/skill-request.service';
import { SkillRequest } from './../../models/skill-request';

@Component({
  selector: 'app-request-skill',
  templateUrl: './request-skill.component.html',
  styleUrls: ['./request-skill.component.css']
})
export class RequestSkillComponent implements OnInit {

  skill: SkillRequest;
  field: string;
  srchValue: string;
  employeeId: number;
  employeeName: string;
  skillList: Skill[];
  status: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private employeeSkillsService: EmployeeSkillsService,
    private skillRequestService: SkillRequestService,
    private loginService: LoginService,
    private skillService: SkillService,
    private router: Router
  ) {
    this.field = 'employeeId';
    this.srchValue = '' + loginService.getEmployeeId();
    this.employeeId = loginService.getEmployeeId();
    this.employeeName = loginService.getEmployeeName();
    this.status = 'Pending';
    this.skill = new SkillRequest();
  }

  ngOnInit() {

    this.skillService.getAllSkills().subscribe(
      (data) => this.skillList = data,
      (err) => this.skillList = undefined
    );
    console.log(this.skillList);
    this.skill.employeeId = this.employeeId;
    this.skill.employeeName = this.employeeName;
    this.skill.status = this.status;
  }
  save() {

      this.skillRequestService.addSkillRequest(this.skill).subscribe(
        (data) => {

          this.router.navigateByUrl('/');
        },
        (error) => { alert('Some problem occured'); }
      );
    }

}
