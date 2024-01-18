import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpParams,
  HttpEvent
} from '@angular/common/http';
import { take, exhaustMap } from 'rxjs/operators';


import { Observable } from 'rxjs';
import { AuthService } from './authService';

@Injectable()

export class authInterceptorService implements HttpInterceptor {
    constructor(private authService:AuthService){} 
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return this.authService.user.pipe(
            take(1),
            exhaustMap(user=>{
                console.log(req)
                if(!user){
                    return next.handle(req)
                }
                const modifiedReq = req.clone({
                    setHeaders: {
                      Authorization: `Bearer ${user.getToken()}`
                    }
                  }); 
                  console.log(modifiedReq)

                  return next.handle(modifiedReq)
            })
        )
    }
}