import { Component, OnInit } from '@angular/core';
import { Product } from '../product.model';
import { ProductService } from '../ProductService';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from 'src/app/cart/cartService';
import { Location } from '@angular/common';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit{
product:Product
index:number;
quantity:number=1;

constructor(private productService:ProductService,private route:ActivatedRoute,private cartService:CartService,private router:Router, private location:Location){}

ngOnInit(): void {
  this.route.params.subscribe(params=>{
    this.index=params['id'];
    this.productService.getProduct(this.index).subscribe(
      result=>{
        this.product=result
      }
    )
  })

}


addToCart(){
  
   this.cartService.addToCart(this.index,this.quantity).subscribe(
    result=>{
      this.router.navigate(['/cart'])
      console.log(result)
    },err=>{
      console.log(err)
    }
   )
   
}

Back(){
  this.location.back()
}




}
