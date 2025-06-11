
import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { ProductListComponent } from './app/components/product-list/product-list.component';
import { ProductFormComponent } from './app/components/product-form/product-form.component';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter([
      { path: 'products', component: ProductListComponent },
      { path: 'add', component: ProductFormComponent },
    ]),
  ],
}).catch((err) => console.error(err));