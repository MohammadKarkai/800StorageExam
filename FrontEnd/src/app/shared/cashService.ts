


import { Injectable } from "@angular/core";

@Injectable({providedIn:'root'})

export class CashServive{

    set(key:any,data:any){
       localStorage.setItem(key,JSON.stringify(data))
    }

    get(key:any){
        return this.getCacheLocalStorage(key)
    }
  

    getCacheLocalStorage(key:any){
        let cashData=localStorage.getItem(key)
        return cashData ? JSON.parse(cashData) : false
    }

    clear(){
        localStorage.clear();
    }
}