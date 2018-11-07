import { Component, OnInit } from '@angular/core';
import { Employee } from '../../model/employee';
import { SkillTraceService } from '../../service/skill-trace.service';
import { ManagerRequest } from '../../model/manager-request';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css']
})
export class RecommendationsComponent implements OnInit {

  emp: boolean;
  course: boolean;
  employees: Employee[];
  requestMan: ManagerRequest;

  constructor(private employeeService: SkillTraceService, private router: Router) {
    this.emp = false;
    this.course = false;
    this.requestMan = new ManagerRequest();
  }

  ngOnInit() {
    this.employeeService.getAllEmloyees().subscribe(
      (data) => this.employees = data
    );
  }

  employee(): void {
       this.emp = true;
       this.course = false;
  }

  Add(): void {
    this.course = true;
    this.emp = false;
  }

  save() {
      this.employeeService.addManagerRequest(this.requestMan).subscribe(
        (data) => {
          this.router.navigateByUrl('/recommendations');
        },
        (error) => {alert('There is some isssue with your request');
      }
      );
      this.course = false;
      this.requestMan = new ManagerRequest();
    }
}
