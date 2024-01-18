import { Component, OnInit } from '@angular/core';
import { CartService } from '../cartService';
import { cartModel } from '../cartModel';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit{

  Cart:cartModel 

  constructor(private cartSerivce:CartService){}

  ngOnInit(): void {
    
  }

}
