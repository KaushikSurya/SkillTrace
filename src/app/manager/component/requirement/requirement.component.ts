import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SkillTraceService } from '../../service/skill-trace.service';
import { SkillRequest } from '../../model/skill-request';

@Component({
  selector: 'app-requirement',
  templateUrl: './requirement.component.html',
  styleUrls: ['./requirement.component.css']
})
export class RequirementComponent implements OnInit {


  skill: string;

  constructor(private activatedRoute: ActivatedRoute, private requireService: SkillTraceService, private router: Router) {
   }

  ngOnInit() {
}




}
