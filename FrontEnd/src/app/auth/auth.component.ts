import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from './authService';
import { Router } from '@angular/router';
import { Subscriber, Subscription } from 'rxjs';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit, OnDestroy{

  authForm:FormGroup
  isLogin:boolean=true; 
  private loginSubscription:Subscription
  private logOutSubscription:Subscription

  constructor(private formBuilder:FormBuilder,private authService:AuthService, private router:Router){}
  ngOnDestroy(): void {

    if (this.loginSubscription){
      this.loginSubscription.unsubscribe()
    }

    if(this.logOutSubscription){
      this.logOutSubscription.unsubscribe()
    }

  }

  ngOnInit(): void {
    this.authForm=this.formBuilder.group({
      'email':['',[Validators.required,Validators.email]],
      'password':['',[Validators.required,Validators.minLength(6)]],
      'name':['',[Validators.required]]
    })
  }
 
  submitForm(){
    console.log("submit")
    if(this.authForm.valid){
      console.log("valid")
      const email = this.authForm.get('email').value 
      const password=this.authForm.get('password').value
      const name=this.authForm.get('name').value
      const data={email:email,password:password, name:name}
    if (this.isLogin){
      console.log("login")
      this.loginSubscription =  this.authService.login(data).subscribe(result=>{
          console.log('result here: ', result)
          this.router.navigate(['products'])
        },error=>{
          console.log(error)
        })
    }
    else{
      console.log("signup")
       this.logOutSubscription =  this.authService.signUp(data).subscribe(result=>{
          this.router.navigate(['products'])
         },errors=>{
          console.log(errors)
         })
    }
  }
  }

  toggleMode(){
   this.isLogin=!this.isLogin
   this.authForm.reset();
  }

}
