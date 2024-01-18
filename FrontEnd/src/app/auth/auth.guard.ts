import {
    CanActivate,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Router,
    UrlTree
  } from '@angular/router';
  import { Injectable } from '@angular/core';
  import { Observable, pipe } from 'rxjs';
  import { map, tap, take, exhaustMap } from 'rxjs/operators';
import { AuthService } from './authService';
  
 
  
  @Injectable({ providedIn: 'root' })
  export class AuthGuard implements CanActivate {
    constructor(private authService: AuthService, private router: Router) {}
  
    canActivate(
      route: ActivatedRouteSnapshot,
      router: RouterStateSnapshot
    ):
      | boolean
      | UrlTree
      | Promise<boolean | UrlTree>
      | Observable<boolean | UrlTree> {
      return this.authService.user.pipe(take(1),map(user=>{
           const isAuth= !! user

            if(user){
                return true
            }
            return this.router.createUrlTree(['/login']);
      })
      );
      
    }
  }
  