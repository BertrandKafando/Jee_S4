import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthentificationService} from "../Services/auth/authentification.service";

@Injectable({
  providedIn: 'root'
})
export class AdminGauardGuard implements CanActivate {

  constructor( private securityService:AuthentificationService, private router:Router){
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    let yesRno = this.securityService.user?.roles.find( e=>e.roleName=="ADMIN")!=undefined;

    if (!yesRno) {
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }

}
