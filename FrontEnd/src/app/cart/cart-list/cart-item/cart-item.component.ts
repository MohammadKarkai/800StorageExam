import { Component, Input } from '@angular/core';
import { cartItem } from '../../cart-item';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent {

@Input() cartItem:cartItem;

}
