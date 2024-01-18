import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ProductComponent } from './product/product.component';
import { CartListComponent } from './cart/cart-list/cart-list.component';
import { AuthComponent } from './auth/auth.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  { 
    path: 'products',
    canActivate:[AuthGuard],
    component: ProductComponent,
    children: [
      {path:'',component:ProductListComponent},
      { path: ':id', component: ProductDetailComponent }
    ]
  },
  {path:'cart',component:CartListComponent,canActivate:[AuthGuard]},
  {path:'login',component:AuthComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


