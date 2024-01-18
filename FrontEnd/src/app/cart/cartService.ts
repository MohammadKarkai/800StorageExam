
import { Injectable } from '@angular/core';
import { cartModel } from './cartModel';
import { cartItem } from './cart-item';
import { ProductService } from '../product/ProductService';
import { Subject } from 'rxjs';
import { Product } from '../product/product.model';
import { HttpClient } from '@angular/common/http';


@Injectable({providedIn:'root'})
export class CartService{

    constructor(private productService:ProductService,private http:HttpClient){}
    cartSubject= new Subject<cartModel>();

   getCart(){
    return this.http.get<any>('http://localhost:3000/feed/getCart')
   }

}