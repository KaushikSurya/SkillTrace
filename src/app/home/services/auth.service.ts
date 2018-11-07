import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: 'http://localhost:3030';

  constructor(private http: HttpClient) { }

  attemptAuth(userName: string, password: string): Observable<any> {
    const credentials = {userName: userName, password: password};
    console.log('attempAuth ::');
    return this.http.post('http://localhost:3030/token/generate-token', credentials);
  }

}
