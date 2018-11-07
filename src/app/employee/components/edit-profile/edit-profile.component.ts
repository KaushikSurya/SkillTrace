import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './../../services/employee.service';
import { Employee } from './../../models/employee';
import { LoginService } from './../../../home/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  employee: Employee;

  constructor(
    private employeeService: EmployeeService,
    private loginService: LoginService,
    private router: Router
  ) {
   }

  ngOnInit() {

    this.employeeService.getEmployeeById(this.loginService.getEmployeeId()).subscribe(
      (data) => this.employee = data,
      (err) => this.employee = undefined
    );

    console.log(this.employee);

  }

  save() {
    this.employeeService.updateEmployee(this.employee).subscribe(
      (data) => {
        this.router.navigateByUrl('/');
      },
      (err) =>  { alert('Some problem occured'); }
    );
  }
}
