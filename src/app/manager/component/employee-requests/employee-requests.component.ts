import { Component, OnInit } from '@angular/core';
import { SkillRequest } from '../../model/skill-request';
import { SkillTraceService } from '../../service/skill-trace.service';

@Component({
  selector: 'app-employee-requests',
  templateUrl: './employee-requests.component.html',
  styleUrls: ['./employee-requests.component.css']
})
export class EmployeeRequestsComponent implements OnInit {

  request: SkillRequest[];
  constructor(private requestService:SkillTraceService) { }

  ngOnInit() {
    this.requestService.getAllRequests().subscribe(
      (data) => this.request = data
    );
    }

}