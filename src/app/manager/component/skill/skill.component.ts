import { Component, OnInit } from '@angular/core';
import { Skill } from '../../model/skill';
import { SkillTraceService } from '../../service/skill-trace.service';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent implements OnInit {

  skills:Skill[];
  id: number;
  constructor(private skillService:SkillTraceService) {
   }

  ngOnInit() {

    this.skillService.getAllSkills().subscribe(
      (data) => this.skills = data
    );
  }

}
