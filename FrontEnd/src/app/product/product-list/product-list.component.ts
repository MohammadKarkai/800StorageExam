import { Component, OnDestroy, OnInit } from '@angular/core';
import { Product } from '../product.model';
import { ProductService } from '../ProductService';
import { CashServive } from 'src/app/shared/cashService';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit, OnDestroy {

  pageSizeOptions: number[] = [4, 8, 12]; // Define page size options
  pageSize: number = 4; // Initial page size
  currentPage: number = 1; // Current page
  totalProducts: number = 9; // Total number of products
  products: Product[] = [];
  data:Product[]=[]; //
  isLoading:boolean

  constructor(private productService:ProductService, private cashService:CashServive){}
  ngOnDestroy(): void {
    this.cashService.clear()
  }
 
  ngOnInit(): void {
    this.getProducts()
  }
  getProducts(){
     let localStorage=this.cashService.get(`ProductsPage=${this.currentPage}`)
     if(localStorage){
      this.products = this.cashService.get(`ProductsPage=${this.currentPage}`).products
      this.totalProducts=this.cashService.get(`ProductsPage=${this.currentPage}`).totalItems
    console.log(this.products)
     }

    else{
      this.isLoading=true
    this.productService.getProductByPage(this.currentPage,this.pageSize).subscribe(
      result=>{
        this.isLoading=false 
        this.cashService.set(`ProductsPage=${this.currentPage}`,result)
         this.products=result.products
         this.totalProducts=result.totalItems
      }
    )
    }
  }
  onPageChange(event: any) {
    
    this.pageSize = event.pageSize; 
    this.currentPage = event.pageIndex + 1;
    
    this.getProducts();
  }

}
