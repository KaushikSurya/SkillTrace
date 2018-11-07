import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { LoginService } from '../services/login.service';

const TOKEN_KEY = 'AuthToken';
const ROLE = 'ROLE';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName: string;
  password: string;

  constructor(private router: Router,
    private authService: AuthService,
    private loginService : LoginService) { }

  ngOnInit() {
  }

  login() : void {
    this.authService.attemptAuth(this.userName, this.password).subscribe(
      data => {
        window.sessionStorage.removeItem(TOKEN_KEY);
        window.sessionStorage.setItem(TOKEN_KEY, data.token);
        window.sessionStorage.setItem(ROLE, data.role);
        if(data.role=='ADMIN') {
          this.router.navigate(['admin']);
        }
        else if(data.role=='EMPLOYEE'){
          this.loginService.setEmployeeId(data.userId);
          this.router.navigate(['employee']);
        }
        else if(data.role=='MANAGER'){
          this.router.navigate(['manager']);
        }
        else {
          alert("401 Unauthorized!!")
        }
      }
    );
  }

}
