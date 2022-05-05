import { Component, OnInit } from '@angular/core';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { Product } from 'src/app/Model/product.model';
import { AppDataState, DataStateEnum } from 'src/app/product.state';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  ///products:Product[]|null=null;
  //products$:Observable<Product[]>|null=null;
  readonly DataStateEnum=DataStateEnum;
  products$:Observable<AppDataState<Product[]>>|null=null;
  constructor(private productsService:ProductsService) { }


  ngOnInit(): void {
  
  }

  onGetAllProducts() {
   this.products$=this.productsService.getAllProducts()
   .pipe(
     map((data)=>({
       dataState:DataStateEnum.LOADED,  data:data })),
     startWith( {  dataState:DataStateEnum.LOADING}),
     catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))

   );
  }

  onGetSelectedProducts(){
    this.products$=this.productsService.getSelectedProducts()
    .pipe(
      map((data)=>({
        dataState:DataStateEnum.LOADED,  data:data })),
      startWith( {  dataState:DataStateEnum.LOADING}),
      catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))

    );
  }

  onGetAvailableProducts(){
    this.products$=this.productsService.getAvailableProducts()
    .pipe(
      map((data)=>({
        dataState:DataStateEnum.LOADED,  data:data })),
      startWith( {  dataState:DataStateEnum.LOADING}),
      catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))
    );
  }

   
  onSearchProduct(value:any){
    this.products$=this.productsService.onSearch(value.keyword)
    .pipe(
      map((data)=>({
        dataState:DataStateEnum.LOADED,  data:data })),
      startWith( {  dataState:DataStateEnum.LOADING}),
      catchError(err=>of({dataState:DataStateEnum.ERROR,errorMessage:err.message}))
    );
  }

  onSelect(p:Product){
    this.productsService.select(p)
    .subscribe(
      data=>{
        p.selected=data.selected
      }
    )
  }

  onDelete(p:Product){
    let v=confirm("Etes vous sur ?");
    if(v==true)
    this.productsService.delete(p)
    .subscribe(
      data=>{
        this.onGetAllProducts();
      }
    )
  }



}
