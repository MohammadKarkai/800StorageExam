export class User{


    constructor(public email:string,private token:string,private expirationDate:Date){}
    
    getToken(){
        if(!this.token ||  new Date() > this.expirationDate){
            return null
        }
        else { 
            return this.token
        }
    }


}