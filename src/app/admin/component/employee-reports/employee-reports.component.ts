import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeeSkillService } from '../../services/employee-skill.service';
import { EmployeeSkill } from '../../model/employee-skill';
import { LoginService } from 'src/app/home/services/login.service';

@Component({
  selector: 'app-employee-reports',
  templateUrl: './employee-reports.component.html',
  styleUrls: ['./employee-reports.component.css']
})
export class EmployeeReportsComponent implements OnInit {

  employeeSkills: EmployeeSkill[];
  empId: number;
  levels: number[] = [];
  skillNames: string[] = [];
  size: number;
  size1: number;
  size2: number;
  public chartType: string = 'bar';
  public chartDatasets: Array<any>;
  public chartLabels: Array<any>;
  public chartColors: Array<any>;
  public chartOptions: any;

  role : string;
  ROLE : string = 'ROLE';

  constructor(private empSkillService: EmployeeSkillService,
    private activatedRoute: ActivatedRoute,
    private loginService : LoginService) { }
    

  ngOnInit() {
    this.role = sessionStorage.getItem(this.ROLE);
    if(this.role=='EMPLOYEE' || this.role=='ADMIN'){
      this.activatedRoute.params.subscribe(
        (params) => this.empId = params['employeeId']
      );
      //this.empId = 233351;
      if(this.role=='EMPLOYEE'){
        this.empId = this.loginService.getEmployeeId();
      }
      console.log("Employee Id " + this.empId);
      this.empSkillService.getEmployeeSkillsById(this.empId).subscribe(
        (data) => {
          this.employeeSkills = data;
          this.size = this.employeeSkills.length;
          console.log(this.employeeSkills.length);
          for (let i = 0; i < this.employeeSkills.length; i++) {
            let empSkill: EmployeeSkill = this.employeeSkills[i];
            this.levels.push(empSkill.level);
            this.skillNames.push(empSkill.skillName);
            console.log(this.levels);
          }
          this.size1 = this.levels.length;
          this.chartDatasets = [
            { data: this.levels, label: 'My First dataset' }
            //{ data: [28, 48, 40, 19, 86, 27, 90], label: 'My Second dataset' }
          ];
  
          this.chartLabels = this.skillNames;
  
          this.chartColors = [
            {
              backgroundColor: '#B0E8F8',
              borderColor: '#00789A',
              borderWidth: 2,
              pointBackgroundColor: 'rgba(220,220,220,1)',
              pointBorderColor: '#fff',
              pointHoverBackgroundColor: '#fff',
              pointHoverBorderColor: 'rgba(220,220,220,1)'
            },
            /*{
              backgroundColor: 'rgba(151,187,205,0.2)',
              borderColor: 'rgba(151,187,205,1)',
              borderWidth: 2,
              pointBackgroundColor: 'rgba(151,187,205,1)',
              pointBorderColor: '#fff',
              pointHoverBackgroundColor: '#fff',
              pointHoverBorderColor: 'rgba(151,187,205,1)'
            }*/
          ];
  
          this.chartOptions = {
            responsive: true
          };
        }
      );
    }
  }
  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }

}