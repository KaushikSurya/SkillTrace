import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SkillTraceService } from '../../service/skill-trace.service';
import { Employee } from '../../model/employee';
import { CourseEmployee } from '../../model/course-employee';

@Component({
  selector: 'app-emp-course',
  templateUrl: './emp-course.component.html',
  styleUrls: ['./emp-course.component.css']
})

export class EmpCourseComponent implements OnInit {

  employeeId: number;
  employee: Employee;
  courseEmp: CourseEmployee;
  id: number;
  employeeName: string;

  constructor(private activatedRoute: ActivatedRoute, private skillService: SkillTraceService, private router: Router) { 

  }

  ngOnInit() {
    this.courseEmp = new CourseEmployee();
    this.activatedRoute.params.subscribe(
      (params) => {
        this.employeeId = params['id'];
        this.employeeName = params['name'];
        this.courseEmp.employeeId = this.employeeId;
        this.courseEmp.employeeName = this.employeeName.trim();
        console.log(this.courseEmp.employeeName);
          /*this.skillService.getEmployeeById(this.employeeId).subscribe(
            (data) => this.employee = data
          );*/
        }
    );
   }

  save(){
  /*  this.courseEmp.employeeId = this.employeeId;
    this.courseEmp.employeeName = this.employeeName;
*/
    this.skillService.addCourseEmployee(this.courseEmp).subscribe(
      (data) => {
        this.router.navigateByUrl('/recommendations');
        //this.router.navigateByUrl("/Success?opt=a&id="+data.contactId);
      },
      (error) => {alert('There is some isssue with your request');
    }
    );
  }

}
