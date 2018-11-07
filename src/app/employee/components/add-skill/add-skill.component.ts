import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeSkillsService } from '../../services/employee-skills.service';
import { EmployeeSkills } from './../../models/employee-skills';
import { LoginService } from './../../../home/services/login.service';
import { Skill } from './../../models/skill';
import { SkillService } from './../../services/skill.service';

@Component({
  selector: 'app-add-skill',
  templateUrl: './add-skill.component.html',
  styleUrls: ['./add-skill.component.css']
})
export class AddSkillComponent implements OnInit {

  skill: EmployeeSkills;
  field: string;
  srchValue: string;
  skills: EmployeeSkills[];
  idSkill: Skill[];
  employeeId: number;
  employeeName: string;
  skillList: Skill[];

  constructor(
    private activatedRoute: ActivatedRoute,
    private employeeSkillsService: EmployeeSkillsService,
    private loginService: LoginService,
    private skillService: SkillService,
    private router: Router
  ) {
    this.field = 'employeeId';
    this.srchValue = '' + loginService.getEmployeeId();
    this.employeeId = loginService.getEmployeeId();
    this.employeeName = loginService.getEmployeeName();
    this.skill = new EmployeeSkills();
    this.idSkill = [];
  }

  ngOnInit() {

    this.skillService.getAllSkills().subscribe(
      (data) => this.skillList = data,
      (err) => this.skillList = undefined
    );
    console.log(this.skillList);
    this.skill.employeeId = this.employeeId;
    this.skill.employeeName = this.employeeName;
  }
  save() {
    // const name = document.getElementById('skname').innerText;
    // this.skill.skillName = name;
      this.employeeSkillsService.addEmployeeSkills(this.skill).subscribe(
        (data) => {
          // this.router.navigateByUrl('/?opt=a&id=' + data.employeeSkillsId);
          this.router.navigateByUrl('/listSkills');
        },
        (error) => { alert('Some problem occured'); }
      );
    }
}
