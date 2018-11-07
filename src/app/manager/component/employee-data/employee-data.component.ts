import { Component, OnInit } from '@angular/core';
import { Employee } from '../../model/employee';
import { SkillTraceService } from '../../service/skill-trace.service';

@Component({
  selector: 'app-employee-data',
  templateUrl: './employee-data.component.html',
  styleUrls: ['./employee-data.component.css']
})
export class EmployeeDataComponent implements OnInit {

  employees: Employee[];
  constructor(private employeeService: SkillTraceService) { }

  ngOnInit() {
    this.employeeService.getAllEmloyees().subscribe(
      (data) => this.employees = data
    );
  }

}
