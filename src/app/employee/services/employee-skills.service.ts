import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { EmployeeSkills } from './../models/employee-skills';
import { LoginService } from '../../home/services/login.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeSkillsService {

  baseUrl: string;
  token: string;
  TOKEN_KEY: string = 'AuthToken';

  constructor(private http: Http,
  private loginService: LoginService
  ) {
    this.token = sessionStorage.getItem(this.TOKEN_KEY);
    this.baseUrl = 'http://localhost:3030/empSkills';
  }

  getBaseUrlById(employeeId: number): string {
    return this.baseUrl + '/' + employeeId;
  }
  getSearchUrl(field: string, value: string): string {
    return this.baseUrl + '/' + field + '/' + value;
  }

  getSearchUrlBySkillName(field: string, value: string): string {
    return this.baseUrl + '/' + this.loginService.getEmployeeId() + '/' + field + '/' + value;
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Bearer ' + this.token);
    return new RequestOptions({ headers: headers });
  }


  getAllEmployeeSkills(): Observable<EmployeeSkills[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl, {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  searchEmployeeSkills(field: string, value: string): Observable<EmployeeSkills[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getSearchUrl(field, value), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }
  searchEmployeeSkillsByName(field: string, value: string): Observable<EmployeeSkills[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getSearchUrlBySkillName(field, value), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getEmployeeSkillsById(employeeId: number): Observable<EmployeeSkills[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrlById(employeeId), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  deleteEmployeeSkillsById(employeeSkillsId: number): Observable<Response> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.delete(this.getBaseUrlById(employeeSkillsId), {headers : theaders});
  }

  addEmployeeSkills(employeeSkill: EmployeeSkills): Observable<EmployeeSkills> {
    return this.http.post(this.baseUrl, JSON.stringify(employeeSkill),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }
  updateEmployeeSkills(employeeSkill: EmployeeSkills): Observable<EmployeeSkills> {
    return this.http.put(this.baseUrl, JSON.stringify(employeeSkill),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }
}
