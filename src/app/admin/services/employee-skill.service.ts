import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers  } from '@angular/http';
import { Observable } from 'rxjs';
import { EmployeeSkill } from '../model/employee-skill';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmployeeSkillService {
  
  baseUrl: string;
  token: string;
  TOKEN_KEY: string = 'AuthToken';

  constructor(private http: Http) {
    this.token = sessionStorage.getItem(this.TOKEN_KEY);
    this.baseUrl = "http://localhost:3030/employeeskills";
   }

   getBaseUrlById(employeeId : number): string {
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

  getAllEmployeeSkills(): Observable<EmployeeSkill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl,{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  /*searchEmployeeSkills(field: string, value: string): Observable<EmployeeSkill[]> {
    return this.http.get(this.getSearchUrl(field,value)).pipe(
      map(data => data.json())
    );
  }*/

 
  getEmployeeSkillsById(employeeId: number): Observable<EmployeeSkill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrlById(employeeId),{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  searchPlans(field: string, value: string): Observable<EmployeeSkill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getSearchUrl(field,value),{headers:theaders}).pipe(
      map(data => data.json())
    );
  }

}
