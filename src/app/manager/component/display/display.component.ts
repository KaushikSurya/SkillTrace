import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SkillTraceService } from '../../service/skill-trace.service';
import { EmployeeSkill } from '../../model/employee-skill';
import { SkillRequest } from '../../model/skill-request';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {


  skills: EmployeeSkill[];

  constructor(private activatedRoute: ActivatedRoute, private displayService: SkillTraceService) { }

  ngOnInit() {

    this.activatedRoute.params.subscribe(
      (params) => {
        const skillName = params['id'];
          this.displayService.getEmployeeSkillById(skillName).subscribe(
            (data) => this.skills = data
          );
      }
    );
  }

}
