import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Employee } from './../models/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  baseUrl: string;
  guestToken : string;
  token: string;
  TOKEN_KEY: string = 'AuthToken';

  constructor(private http: Http) {
    this.guestToken = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWVzdCIsInNjb3BlcyI6IlJPTEVfR1VFU1QiLCJpYXQiOjE1NDEyMzc1MzMsImV4cCI6MTU0MTI1NTUzM30.pH8LjDyOEgQBIaTU1xlHSomiAkAlhSXP-0Pr4iSLk7A';
    
    this.token = sessionStorage.getItem(this.TOKEN_KEY);
    this.baseUrl = 'http://localhost:3030/employees';
  }

  getBaseUrlById(employeeId: number): string {
    return this.baseUrl + '/' + employeeId;
  }
  getSearchUrl(field: string, value: string): string {
    return this.baseUrl + '/' + field + '/' + value;
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Bearer ' + this.token);
    return new RequestOptions({ headers: headers });
  }

  getEmployeeByEmail(emailId: string): Observable<Employee[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getSearchUrl('emailId', emailId),{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getEmployeeById(employeeId: number): Observable<Employee> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrlById(employeeId),{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getAllEmployees(): Observable<Employee[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl,{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getAllEmployeesGuest(): Observable<Employee[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.guestToken});
    return this.http.get(this.baseUrl+"/guest",{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  searchEmployees(field: string, value: string): Observable<Employee[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getSearchUrl(field, value),{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  deleteEmployeeById(employeeId: number): Observable<Response> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.delete(this.getBaseUrlById(employeeId),{headers : theaders});
  }

  addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post(this.baseUrl, JSON.stringify(employee),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }

  updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put(this.baseUrl, JSON.stringify(employee),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }
  
}
