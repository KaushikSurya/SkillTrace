import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Skill } from '../model/skill';
import { Observable  } from 'rxjs';
import { map } from 'rxjs/operators';
import { SkillRequest } from '../model/skill-request';
import { Employee } from '../model/employee';
import { EmployeeSkill } from '../model/employee-skill';
import { CourseEmployee } from '../model/course-employee';
import { ManagerRequest } from '../model/manager-request';

@Injectable({
  providedIn: 'root'
})
export class SkillTraceService {

  baseUrl: string;
  baseUrl1: string;
  baseUrl2: string;
  baseUrl3: string;
  baseUrl4: string;
  baseUrl5: string;
  baseUrl6: string;
  token: string;
  TOKEN_KEY: string = 'AuthToken';

  constructor(private http: Http) { 
    this.token = sessionStorage.getItem(this.TOKEN_KEY);
    this.baseUrl = "http://localhost:3030/skill";
    this.baseUrl1 = "http://localhost:3030/EmployeeRequest";
    this.baseUrl2 = "http://localhost:3030/EmployeeRequestDel";
    this.baseUrl3 = "http://localhost:3030/EmployeeData";
    this.baseUrl4 = "http://localhost:3030/EmployeeSkill";
    this.baseUrl5 = "http://localhost:3030/CourseEmployee";
    this.baseUrl6 = "http://localhost:3030/ManagerRequest"
  }

  getBaseUrl1ById(id: number): string {
    return this.baseUrl1 + '/' + id;
  }

  getBaseUrl2ById(id: number): string {
    return this.baseUrl2 + '/' + id;
  }

  getBaseUrl3ById(id: number): string {
    return this.baseUrl3 + '/' + id;
  }

  getBaseUrl4ById(id: string): string {
    return this.baseUrl4 + '/' + id;
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Bearer ' + this.token);
    return new RequestOptions({ headers: headers });
  }


  getAllSkills(): Observable<Skill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl, {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getAllEmloyees(): Observable<Employee[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl3, {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getAllRequests(): Observable<SkillRequest[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl1, {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getAllEmployeeSkills(): Observable<EmployeeSkill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl4, {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getRequestById(id: number): Observable<SkillRequest> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrl1ById(id), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getEmployeeSkillById(id: string): Observable<EmployeeSkill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrl4ById(id), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  deleteContactById(id: number): Observable<Response> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.delete(this.getBaseUrl2ById(id), {headers : theaders});
  }


  getEmployeeById(id: number): Observable<Employee> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrl3ById(id), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  addCourseEmployee(courseEmp: CourseEmployee): Observable<CourseEmployee> {
    return this.http.post(this.baseUrl5, JSON.stringify(courseEmp), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }

  addManagerRequest(managerReq: ManagerRequest): Observable<ManagerRequest> {
    return this.http.post(this.baseUrl6, JSON.stringify(managerReq), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }
}
