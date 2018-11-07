import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { EmployeeSkillService } from '../../services/employee-skill.service';


@Component({
  selector: 'app-manager-analysis',
  templateUrl: './manager-analysis.component.html',
  styleUrls: ['./manager-analysis.component.css']
})
export class ManagerAnalysisComponent implements OnInit {

  srchValue : string;
  field : string;
  role : string;
  ROLE : string = 'ROLE';

  constructor(private router : Router,
    private empSkillService : EmployeeSkillService) { }

  ngOnInit() {
  }

  doSearch(field : string) 
  {
    this.role = sessionStorage.getItem(this.ROLE);
    //alert(this.srchValue);
    this.field=field+"Name";
    if(this.role=='ADMIN'){
      this.router.navigate(["/admin/skillName"],{queryParams:{field:this.field,srchValue:this.srchValue}});
    }
    else if(this.role=='MANAGER') {
      this.router.navigate(["../manager/skillName"],{queryParams:{field:this.field,srchValue:this.srchValue}});
    }
    else {
      alert('401 Unauthorized!');
    }
  }

}
