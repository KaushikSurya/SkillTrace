import { Component, OnInit } from '@angular/core';
import { SkillRequest } from '../../model/skill-request';
import { SkillTraceService } from '../../service/skill-trace.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css']
})
export class ApprovalComponent implements OnInit {

  request: SkillRequest;
  reqLogo: string;

  constructor(private requestService:SkillTraceService,private activatedRoute: ActivatedRoute, private router: Router) { 
    this.reqLogo = "assets/images/request.png"
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params) => {
        const requestId = params['id'];
          this.requestService.getRequestById(requestId).subscribe(
            (data) => this.request = data
          );
        }
    );
  }

  perform() :void
  {
    this.activatedRoute.params.subscribe(
      (params) => {
        const requestId = params['id'];
          this.requestService.deleteContactById(requestId).subscribe(
            (data) => {
              this.router.navigateByUrl('/employeeRequest');
              //this.router.navigateByUrl("/Success?opt=a&id="+data.contactId);
            },
            (error) => {alert('There is some isssue with your request');}
          );
        }
    );
  }


  

}
