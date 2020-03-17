import { Component, OnInit } from '@angular/core';
import { LoginDto } from '../dto/login.dto';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDto : LoginDto;

  constructor(private authentication: AuthService, private router: Router,private snackBar: MatSnackBar, private jwtHelper: JwtHelperService) {
    this.loginDto = new LoginDto('', '');
   }

  ngOnInit(): void {
  }

  login() {
    this.authentication.login(this.loginDto).subscribe(result => {
      this.authentication.setToken(result.token);
      console.log(this.jwtHelper.decodeToken(this.authentication.getToken()));
      //this.router.navigate(['/']);  
      this.snackBar.open('Authentication ok.');
    },
    error => {
        this.snackBar.open('Authentication failed.');
    });
  }

}
