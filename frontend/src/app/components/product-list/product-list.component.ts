import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { ProductService } from '../../services/product.service';
import { ResponseProductDTO } from '../../dto/ProductDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent {
  products: ResponseProductDTO[] = [];
  brandId: number | null = null;
  categoryId: number | null = null;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.brandId = params.get('brandId')
        ? Number(params.get('brandId'))
        : null;
      this.categoryId = params.get('categoryId')
        ? Number(params.get('categoryId'))
        : null;
      this.loadProducts();
    });
  }

  private loadProducts(): void {
    if (this.categoryId !== null) {
      this.productService
        .getProductsByCategory(this.categoryId)
        .then((products) => (this.products = products))
        .catch((error) =>
          console.error('Error loading category products:', error)
        );
    } else if (this.brandId !== null) {
      this.productService
        .getProductsByBrand(this.brandId)
        .then((products) => (this.products = products))
        .catch((error) =>
          console.error('Error loading brand products:', error)
        );
    } else {
      this.productService
        .getAllProducts()
        .then((products) => (this.products = products))
        .catch((error) => console.error('Error loading all products:', error));
    }
  }

  editProduct(productId: number): void {
    this.router.navigate(['/edit-product', productId]);
  }

  addProduct(): void {
    this.router.navigate(['/add-product']);
  }

  deleteProduct(productId: number): void {
    this.productService
      .deleteProduct(productId)
      .then(() => this.loadProducts())
      .catch((error) => console.error('Error deleting product:', error));
  }
}
