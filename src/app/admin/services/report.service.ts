import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response  } from '@angular/http';
import { Observable } from 'rxjs';
import { Report } from '../model/report';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  baseUrl: string;
  token: string;
  TOKEN_KEY: string = 'AuthToken';
  //USER_NAME: string = 'userName';
  //USER_ID: string = 'UserId';

  constructor(private http: Http) {
    this.token = sessionStorage.getItem(this.TOKEN_KEY);
    this.baseUrl = "http://localhost:3030/reports";
   }

   getBaseUrlById(employeeId: number): string {
    return this.baseUrl + "/" + employeeId;
  }

  getSearchUrl(field: string, value: string): string {
    return this.baseUrl + "/" + field + "/" + value;
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Bearer ' + this.token);
    return new RequestOptions({ headers: headers });
  }

  getAllReports(): Observable<Report[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl,{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  searchReports(field: string, value: string): Observable<Report[]> {
    return this.http.get(this.getSearchUrl(field,value)).pipe(
      map(data => data.json())
    );
  }

  getReportById(employeeId: number): Observable<Report> {
    return this.http.get(this.getBaseUrlById(employeeId)).pipe(
      map(data => data.json())
    );
  }

  /*deleteReportById(pTitle: string): Observable<Response> {
    return this.http.delete(this.getBaseUrlById(pTitle));
  }

  addReport(plan: Report): Observable<Report> {
    return this.http.post(this.baseUrl, JSON.stringify(plan), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }

  updateReport(plan: Report): Observable<Report> {
    return this.http.put(this.baseUrl, JSON.stringify(plan), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }*/
}
