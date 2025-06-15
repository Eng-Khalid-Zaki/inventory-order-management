import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { ProductListComponent } from './app/components/product-list/product-list.component';
import { ProductFormComponent } from './app/components/product-form/product-form.component';
import { BrandListComponent } from './app/components/brand-list/brand-list.component';
import { CategoryListComponent } from './app/components/category-list/category-list.component';
import { StockListComponent } from './app/components/stock-list/stock-list.component';
import { StoreListComponent } from './app/components/store-list/store-list.component';
import { StoreFormComponent } from './app/components/store-form/store-form.component';
import { StockFormComponent } from './app/components/stock-form/stock-form.component';
import { CustomerListComponent } from './app/components/customer-list/customer-list.component';
import { CustomerFormComponent } from './app/components/customer-form/customer-form.component';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter([
      { path: 'products', component: ProductListComponent },
      { path: 'edit-product/:productId', component: ProductFormComponent },
      { path: 'add-product', component: ProductFormComponent },
      { path: 'products/brand/:brandId', component: ProductListComponent },
      { path: 'products/category/:categoryId', component: ProductListComponent },
      { path: 'brands', component: BrandListComponent },
      { path: 'categories', component: CategoryListComponent },
      { path: 'stocks', component: StockListComponent },
      { path: 'stocks/stores/:storeId', component: StockListComponent },
      { path: 'stores', component: StoreListComponent },
      { path: 'edit-store/:storeId', component: StoreFormComponent },
      { path: 'add-store', component: StoreFormComponent },
      { path: 'edit-stock/:productId/:storeId', component: StockFormComponent },
      { path: 'add-stock', component: StockFormComponent },
      { path: 'customers', component: CustomerListComponent },
      { path: 'edit-customer/:customerId', component: CustomerFormComponent },
      { path: 'add-customer', component: CustomerFormComponent },
    ]),
  ],
}).catch((err) => console.error(err));