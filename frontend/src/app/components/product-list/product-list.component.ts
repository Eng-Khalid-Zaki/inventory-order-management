import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { ProductService } from '../../services/product.service';

interface Product {
  productName: string;
  modelYear: number;
  listPrice: number;
}

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent {
  products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  private loadProducts(): void {
    this.productService
      .getAllProducts()
      .then((products) => {
        console.log('Received products:', products); // Debugging
        this.products = products;
      })
      .catch((error) => console.error('Error loading products:', error));
  }
}
