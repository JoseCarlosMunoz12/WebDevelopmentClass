import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class LoggedInGuard implements CanActivate {

  constructor(private router: Router, private userService: UserService) { }


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      if ("user" in sessionStorage) {
        if (this.userService.loggedIn()) {
          return true;
        } else {
          sessionStorage.clear();
          //user isn't set, so go to login
          this.router.navigate(["/login"]);
          return false;
        }        
      } else {
        //user isn't set, so go to login
        this.router.navigate(["/login"]);
        return false;
      }



  }
  
}
