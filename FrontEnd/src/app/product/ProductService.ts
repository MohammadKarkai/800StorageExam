import { Injectable } from "@angular/core";
import { Product } from "./product.model";
import { Subject } from "rxjs";
import { Currency } from "../shared/currency";
import { HttpClient, HttpClientModule } from "@angular/common/http";

export interface Products{
    products:Product[],
    totalItems:number;
}

@Injectable({providedIn:'root'})

export class ProductService{


    constructor(private http:HttpClient){}


     productSubject=new Subject<Product[]>();
   /*  products:Product[]=[
        
        {name:'asd',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},
        {name:'backPack',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},
        {name:'backPack',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},
        {name:'backPack',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},
        {name:'backPack',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},
        {name:'backPack',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},
        {name:'backPack',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},
        {name:'backPack',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},
        {name:'backPack',price:239,currency:Currency.USD,imageUrl:'https://media.gucci.com/style/DarkGray_Center_0_0_2400x2400/1452204006/308364_A7M0G_2754_001_080_0034_Light-Soho-leather-disco-bag.jpg'},

    ]*/
    
  /*  getProducts(){
        return this.products.slice();
    }


    setProducts(products:Product[]){
        this.products=products;
        this.productSubject.next(this.products.slice())
    }

    getProduct(index:number){
        return this.products[index]
    }

    updateProducts(product:Product,index:number){
        this.products[index]=product;
        this.productSubject.next(this.products.slice());
    }

    addProduct(product:Product){
        this.products.push(product)
        this.productSubject.next(this.products.slice());
    }

    deleteProduct(index:number){
        this.products.splice(index,1)
        this.productSubject.next(this.products.slice());
    }*/

    getProductByPage(page:number,pageSize:number){
        return this.http.get<Products>(`http://localhost:3000/feed/products?page=${page}&pagsize=${pageSize}`)
    }

    getProduct(productId:number){
        return this.http.get<Product>(`http://localhost:3000/feed/product/${productId}`)
    }

    filterProduct(productId:string){
        return this.http.get<Product>(`http://localhost:3000/feed/search?search=${productId}`)
    }
     
    
}