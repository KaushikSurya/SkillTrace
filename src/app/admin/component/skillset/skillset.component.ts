import { Component, OnInit } from '@angular/core';
import { Skill } from '../../model/skill';
import { SkillService } from '../../services/skill.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-skillset',
  templateUrl: './skillset.component.html',
  styleUrls: ['./skillset.component.css']
})
export class SkillsetComponent implements OnInit {

  skills: Skill[];
  skill: Skill;
  selectAll: boolean;
  isDesc : boolean;

  constructor(private skillService: SkillService,
    private router: Router) { }

  ngOnInit() {
    this.skillService.getAllSkills().subscribe(
      (data) => {
        this.skills = data;
        for (let i = 0; i < this.skills.length; i++) {
          this.skills[i].selected = false;
        }
      }
      // (err)=>this.employees=undefined
    );
    console.log(this.skills);
  }

  doEdit(skillId: number) {
    this.router.navigateByUrl("/admin/editSkill/" + skillId);
  }

  doDelete(skillId: number) {
    if (confirm("Do you want to delete employee : " + skillId + " ?")) {
      this.skillService.deleteSkillById(skillId).subscribe(
        (resp) => {
          if (resp.ok) {
            this.router.navigateByUrl("/admin/skillSet");
          }
        }
      )
    }
  }

  doDeleteSelected() {
    if (confirm("Do you want to delete all employees ?")) {
      for (let i = 0; i < this.skills.length; i++) {
        if (this.skills[i].selected == true) {
          this.skillService.deleteSkillById(this.skills[i].skillId).subscribe(
            (resp) => {
              if (resp.ok) {
                this.router.navigate(['/admin/skillSet']);
              }
            }
          );
        }
      }
    }
    console.log(status);
  }

  changeAll() {
    if (this.selectAll) {
      for (let i = 0; i < this.skills.length; i++) {
        this.skills[i].selected = true;
      }
    }
    else {
      for (let i = 0; i < this.skills.length; i++) {
        this.skills[i].selected = false;
      }
    }
  }

  order() {
    if(this.isDesc)
      this.isDesc=false;
    else
      this.isDesc=true;
  }

}


