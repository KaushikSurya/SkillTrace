import { Component, OnInit } from '@angular/core';
import { SkillTraceService } from '../../service/skill-trace.service';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../../model/employee';
import { EmployeeSkill } from '../../model/employee-skill';

@Component({
  selector: 'app-employee-skill',
  templateUrl: './employee-skill.component.html',
  styleUrls: ['./employee-skill.component.css']
})
export class EmployeeSkillComponent implements OnInit {

  employee: Employee;
  employeeSkill: EmployeeSkill[];
  skills: string[];
  employeeId: number;
  empLogo: string;

  constructor(private skillService:SkillTraceService,private activatedRoute:ActivatedRoute) { 
    this.empLogo = "assets/images/employee.png"
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        this.employeeId = params['id'];
          this.skillService.getEmployeeById(this.employeeId).subscribe(
            (data) => this.employee = data
          );
        }
    );
    this.skillService.getAllEmployeeSkills().subscribe(
      (data) => this.employeeSkill = data
    );
    /*
    for(let i=0; i<this.employeeSkill.length; i++)
    {
      console.log("hello")
      if(this.employee.employeeId == this.employeeSkill[i].employeeId)
      {
        console.log("hello");
        this.skills.push(this.employeeSkill[i].skillName);
      }
    }

  }
  /*
  this.planService.getCustomerById(this.customid).subscribe(
    (data) => {
      this.custo = data;
    }
  );
  */

}
}