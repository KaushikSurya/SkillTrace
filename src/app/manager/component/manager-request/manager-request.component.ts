import { Component, OnInit } from '@angular/core';
import { SkillTraceService } from '../../service/skill-trace.service';
import { ManagerRequest } from '../../model/manager-request';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manager-request',
  templateUrl: './manager-request.component.html',
  styleUrls: ['./manager-request.component.css']
})
export class ManagerRequestComponent implements OnInit {

  magReq: ManagerRequest;
  constructor(private employeeService: SkillTraceService, private router: Router ) { }

  ngOnInit() {
  }

  save(){
    this.employeeService.addManagerRequest(this.magReq).subscribe(
      (data) => {
        this.router.navigateByUrl('/recommendations');
        //this.router.navigateByUrl("/Success?opt=a&id="+data.contactId);
      },
      (error) => {alert('There is some isssue with your request');
    }
    );
  }

}
