import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { NgForm } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private userService: UserService, private route:Router){}
  user:User = new User();
  loginFailure: Boolean = false;
  ngOnInit(): void {
    
  }
  onSubmit(form: NgForm):void{
    this.userService.login(this.user).subscribe( data => {
      this.user = data;
      // store the username
      sessionStorage.setItem("user", this.user.username);
      this.route.navigate(["/studentlist"]);
    },
    error => {
      if(error.status = '401'){
        this.loginFailure = true;
        form.resetForm();
      } else {

      }

    });
  }
}
