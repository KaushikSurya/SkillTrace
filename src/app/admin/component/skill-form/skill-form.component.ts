import { Component, OnInit } from '@angular/core';
import { Skill } from '../../model/skill';
import { SkillService } from '../../services/skill.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-skill-form',
  templateUrl: './skill-form.component.html',
  styleUrls: ['./skill-form.component.css']
})
export class SkillFormComponent implements OnInit {

  skill : Skill;
  isEdit : boolean;

  constructor(private skillService : SkillService,
    private router : Router,
    private activatedRoute : ActivatedRoute) { }

  ngOnInit() {
    this.skill = new Skill();
    this.isEdit = false;

    this.activatedRoute.params.subscribe(
      (params) => {
        let id = params ['skillId'];
        if(id){
          this.isEdit = true;
          this.skillService.getSkillById(id).subscribe(
            (data) => this.skill = data
          )
        }
      }
    )
  }

  saveSkill(){
    if(this.isEdit){
      this.skillService.updateSkill(this.skill).subscribe(
        (data) => { this.router.navigateByUrl("/admin/skillset")}
      )
      
    }
    else {
      this.skillService.addSkill(this.skill).subscribe(
        (data) => { this.router.navigateByUrl("/admin/skillset")}
      )
    }
  }

}
