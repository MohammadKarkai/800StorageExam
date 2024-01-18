import {Currency} from '../shared/currency'

  export class Product {
    public _id:number
    public imageUrl: string;
    public title: string;
    public price: number;
    public user:number
  
    constructor();
    constructor(id:number,itle: string, price: number, imageUrl: string,user:number);
    constructor(id?:number,title?: string, price?: number, imageUrl?: string,user?:number) {
      if (imageUrl && title && price && id ) {
        this._id=id
        this.title=title
        this.price = price;
        this.imageUrl = imageUrl;
        this.user=user

      } else {
        this._id=id
        this.imageUrl = '';
        this.title = '';
        this.price = 0;
        this.user=0; 
         // Assign a default currency here if needed
      }
    }
  }
  