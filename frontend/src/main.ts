import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { ProductListComponent } from './app/components/product-list/product-list.component';
import { ProductFormComponent } from './app/components/product-form/product-form.component';
import { BrandListComponent } from './app/components/brand-list/brand-list.component';
import { CategoryListComponent } from './app/components/category-list/category-list.component';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter([
      { path: 'products', component: ProductListComponent },
      // { path: 'products/:id', component: productDetailsComponent},
      { path: 'edit-product/:id', component: ProductFormComponent },
      { path: 'add-product', component: ProductFormComponent },
      { path: 'products/brand/:brandId', component: ProductListComponent },
      { path: 'products/category/:categoryId', component: ProductListComponent },
      { path: 'brands', component: BrandListComponent },
      { path: 'categories', component: CategoryListComponent },
    ]),
  ],
}).catch((err) => console.error(err));