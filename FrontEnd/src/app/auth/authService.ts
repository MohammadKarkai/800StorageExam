import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, tap, throwError } from "rxjs";
import { User } from "./user.model";
import { Router } from "@angular/router";

export interface AuthResponseData{
 token:string
 email:string
 expiresIn:string
}


@Injectable({providedIn:'root'})

export class AuthService{
 
    user= new BehaviorSubject<User>(null); 
    private tokenExpirationTime:any

    constructor(private http:HttpClient,private router:Router){}

    handleAuth(email:string,token:string,expirationIn:number){
      const expirationDate=new Date(new Date().getTime() + expirationIn )
      const user=new User(email,token,expirationDate)
      this.user.next(user)
      this.autoLogout(expirationIn)
      localStorage.setItem('user',JSON.stringify(this.user))
    }

    autoLogout(expirationDate:number){
      this.tokenExpirationTime=setTimeout(()=>{
        this.logout()
      },expirationDate)
    }

    logout(){
      this.user.next(null)
      this.router.navigate(['/login'])
      localStorage.clear
      if(this.tokenExpirationTime){
      clearTimeout(this.tokenExpirationTime)
      }
      this.tokenExpirationTime=null ; 
      
    }


    login(data: any): Observable<AuthResponseData> {
     return this.http.post<AuthResponseData>('http://localhost:3000/auth/login',data).pipe(catchError(this.handleError),
     tap(
      data=>{
        console.log(data.token)
        const email=data.email
        const token=data.token
        const expirationDate= +data.expiresIn 
        this.handleAuth(email,token,expirationDate)
      }
     )
     )

    }

    autoLogin(){

      const userData:{
          email:string,
          token:string,
          expirationDate:Date
      }=JSON.parse(localStorage.getItem('user'))

      if(!userData){
        return;
      }

      const loadedUser=new User(userData.email,userData.token,new Date(userData.expirationDate))
      if(loadedUser.getToken()){
        this.user.next(loadedUser)
        const expirationDate=new Date(userData.expirationDate).getTime() - new Date().getTime()
        this.autoLogout(expirationDate)

      }
 
    }

    handleError(errorRes: HttpErrorResponse){
      
      switch (errorRes.error.message) {
        case "Email al-ready exists": 
         return throwError("email al-ready exists")
        case "invalid Password" :
         return throwError("Ivalid Password")

        default : 
        return throwError("Ivalid submit")  

      }
      
      
    }

    signUp(data:any) : Observable<any>{
      return this.http.post<any>('http://localhost:3000/auth/signUp',data).pipe(catchError(this.handleError),
       tap(user=>{
        this.handleAuth(user.email,user.token,+user.expirationDate)
       })
      )

    }

}














