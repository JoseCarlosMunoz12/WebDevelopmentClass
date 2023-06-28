import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  login(user:User):Observable<User>{
    return this.http.post<User>("http://localhost:7777/login",user, {withCredentials:true});
  }
  constructor(private http:HttpClient, private router:Router) {}
  
  logout(){
    sessionStorage.clear();    
    this.http.get("http://localhost:7777/logout", {withCredentials:true});
    this.router.navigate(['/login']);
  }
  loggedIn():Observable<boolean>{
    return this.http.get<boolean>("http://localhost:7777/loggedin", {withCredentials:true});
    
  }
}
