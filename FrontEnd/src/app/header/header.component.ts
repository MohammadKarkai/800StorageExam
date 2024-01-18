import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Product } from '../product/product.model';
import { ProductService } from '../product/ProductService';
import { Router } from '@angular/router';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { Subject } from 'rxjs';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
    
    searchQuery:string='';
    searchResults:Product
    display=false
    isloading:boolean
    unUsedProduct=new Product()
    
    @ViewChild('searchInput') searchInput: ElementRef | undefined;
  @ViewChild('searchDropdown') searchDropdown: ElementRef | undefined;


    private searchSubject:Subject<string>= new Subject<string>()

    constructor(private productService:ProductService,private router:Router){}

    ngOnInit(): void {
       
      this.display=false
      this.searchSubject.pipe(
        debounceTime(3000),

      ).subscribe(searchResult=>{
        this.productService.filterProduct(searchResult).subscribe(
          result=>{
          
            this.searchResults=result
            this.isloading=false
            
            console.log(result)
          },error=>{
            console.log(error)
          }
        )
      })
      
    }

    searchUser(){
      
      if(this.searchQuery.trim() != ''){
        this.searchResults=this.unUsedProduct
        this.display=true
        this.loadingTime()
        this.searchSubject.next(this.searchQuery)
        
      }
    }

    onItemClicked() {
      // Logic when an item is clicked
      this.display = false; // Close the dropdown when an item is clicked
    }

    handleClick(event: Event) {
      if (
        !this.searchInput?.nativeElement.contains(event.target) &&
        !this.searchDropdown?.nativeElement.contains(event.target)
      ) {
        this.display = false; // Close the dropdown when clicked outside
      }
    }

    loadingTime(){
      this.isloading=true;
      setTimeout(()=>{
        this.isloading=false;
      },3000)
    }

}
