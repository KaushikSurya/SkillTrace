import { Injectable } from '@angular/core';
import { Skill } from '../model/skill';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { RequestOptions, Http, Headers, Response } from '@angular/http';
import { headersToString } from 'selenium-webdriver/http';

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  baseUrl: string;
  token: string;
  TOKEN_KEY: string = 'AuthToken';

  constructor(private http: Http) {
    this.token = sessionStorage.getItem(this.TOKEN_KEY);
    this.baseUrl = "http://localhost:3030/skills";
  }

  getBaseUrlById(skillId: number): string {
    return this.baseUrl + "/" + skillId;
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

  getAllSkills(): Observable<Skill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl,{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getSkillById(skillId: number): Observable<Skill> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrlById(skillId),{headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  deleteSkillById(skillId: number): Observable<Response> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.delete(this.getBaseUrlById(skillId),{headers : theaders});
  }

  addSkill(skill: Skill): Observable<Skill> {
    //const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.post(this.baseUrl, JSON.stringify(skill), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }

  updateSkill(skill: Skill): Observable<Skill> {
    //const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.put(this.baseUrl, JSON.stringify(skill), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }
}
