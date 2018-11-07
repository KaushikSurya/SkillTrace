import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Login } from '../models/login';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  employeeId: number;
  employeeName: string;
  baseUrl : string;
  token: string;
  // TOKEN_KEY: string = 'AuthToken';

  constructor(private http: Http) {
    this.token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWVzdCIsInNjb3BlcyI6IlJPTEVfR1VFU1QiLCJpYXQiOjE1NDEyMzc1MzMsImV4cCI6MTU0MTI1NTUzM30.pH8LjDyOEgQBIaTU1xlHSomiAkAlhSXP-0Pr4iSLk7A';

    this.baseUrl = 'http://localhost:3030/employeelogin';
   }
   
  setEmployeeId(employeeId : number) : void {
    this.employeeId = employeeId;
  }

  getEmployeeId(): number {
    return this.employeeId;
  }

  getEmployeeName(): string {
    return this.employeeName;
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Bearer ' + this.token);
    return new RequestOptions({ headers: headers });
  }

  addUser(login : Login): Observable<Login> {
    return this.http.post(this.baseUrl, JSON.stringify(login),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }

}
