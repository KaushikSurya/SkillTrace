import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { SkillRequest } from './../models/skill-request';

@Injectable({
  providedIn: 'root'
})
export class SkillRequestService {

  baseUrl: string;
  token: string;
  TOKEN_KEY: string = 'AuthToken';

  constructor(private http: Http) {
    this.token = sessionStorage.getItem(this.TOKEN_KEY);
    this.baseUrl = 'http://localhost:3030/skillRequests';
  }

  getBaseUrlById(skillRequestRequestId: number): string {
    return this.baseUrl + '/' + skillRequestRequestId;
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


  getAllSkillRequests(): Observable<SkillRequest[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.baseUrl, {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  searchSkillRequests(field: string, value: string): Observable<SkillRequest[]> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getSearchUrl(field, value), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  getSkillRequestById(skillRequestId: number): Observable<SkillRequest> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.get(this.getBaseUrlById(skillRequestId), {headers : theaders}).pipe(
      map(data => data.json())
    );
  }

  deleteSkillRequestById(skillRequestId: number): Observable<Response> {
    const theaders = new Headers({'Authorization': 'Bearer ' + this.token});
    return this.http.delete(this.getBaseUrlById(skillRequestId), {headers : theaders});
  }

  addSkillRequest(skillRequest: SkillRequest): Observable<SkillRequest> {
    return this.http.post(this.baseUrl, JSON.stringify(skillRequest),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }
  updateSkillRequest(skillRequest: SkillRequest): Observable<SkillRequest> {
    return this.http.put(this.baseUrl, JSON.stringify(skillRequest),
      this.getJsonContentTypeHeader()).pipe(
        map(data => data.json())
      );
  }
}
