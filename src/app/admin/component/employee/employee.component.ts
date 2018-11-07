import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { ActivatedRoute } from '@angular/router';
import { Report } from '../../model/report';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  reports : Report[];

  constructor(private reportService : ReportService,
  private activatedRoute : ActivatedRoute) { }

  ngOnInit() {
    this.reportService.getAllReports().subscribe(
      (data) => this.reports=data
      // (err)=>this.employees=undefined
    );
    console.log(this.reports);
  }

}
