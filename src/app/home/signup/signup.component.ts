import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { Employee } from 'src/app/employee/models/employee';
import { Login } from '../models/login';
import { EmployeeService } from 'src/app/employee/services/employee.service';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  employees: Employee[];
  login: Login;
  empId: number;
  userName: string;
  password: string;
  confirmPassword: string;

  constructor(private employeeService: EmployeeService,
    private loginService: LoginService,
    private router: Router) {
    this.login = new Login();
    console.log(this.password);
  }

  ngOnInit() {
    this.employeeService.getAllEmployeesGuest().subscribe(
      (data) => {
        this.employees = data;
        //console.log(this.employees[1].employeeId);
      }
    );
  }

  saveUser() {
    console.log(this.password)
    console.log(this.confirmPassword)
    if (this.password == this.confirmPassword) {
      this.login.password = this.password;
      console.log(this.login.userId);
      console.log(this.login.userName);
      console.log(this.login.password);
      this.loginService.addUser(this.login).subscribe(
        (resp) => this.router.navigateByUrl(''),
        (err) => alert('Cannot Add')
      );
    }

  }

}
