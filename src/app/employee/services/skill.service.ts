import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Skill } from './../models/skill';

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  baseUrl: string;
  token: string;
  TOKEN_KEY: string = 'AuthToken';

  constructor(private http: Http) {
    this.token = sessionStorage.getItem(this.TOKEN_KEY);
    this.baseUrl = 'http://localhost:3030/skills';
  }

  getBaseUrlById(skillId: number): string {
    return this.baseUrl + '/' + skillId;
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


  getAllSkills(): Observable<Skill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl, {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  searchSkills(field: string, value: string): Observable<Skill[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getSearchUrl(field, value), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getSkillById(skillId: number): Observable<Skill> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrlById(skillId), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  deleteSkillById(skillId: number): Observable<Response> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.delete(this.getBaseUrlById(skillId), {headers : theaders});
  }

  addSkill(skill: Skill): Observable<Skill> {
    return this.http.post(this.baseUrl, JSON.stringify(skill),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }
  updateSkill(skill: Skill): Observable<Skill> {
    return this.http.put(this.baseUrl, JSON.stringify(skill),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }
}
