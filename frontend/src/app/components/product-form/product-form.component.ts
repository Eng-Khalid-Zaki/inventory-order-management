import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select'; // Import Angular Material Select
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { ResponseProductDTO } from '../../dto/ProductDTO';
import { BrandService } from '../../services/brand.service';
import { CategoryService } from '../../services/category.service';
import { Brand } from '../../dto/BrandDTO';
import { Category } from '../../dto/CategoryDTO';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
  ],
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css'],
})
export class ProductFormComponent {
  brands: Brand[] = [];
  categories: Category[] = [];
  product: ResponseProductDTO = {
    id: 0,
    productName: '',
    brandId: 0,
    categoryId: 0,
    modelYear: 2025,
    listPrice: 0,
  };

  isEditing: boolean = false;

  constructor(
    private productService: ProductService,
    private brandService: BrandService,
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('productId');
    this.loadBrands();
    this.loadCategories();
    if (productId) {
      this.isEditing = true;
      this.loadProduct(Number(productId));
    }
  }

  private loadProduct(productId: number): void {
    this.productService
      .getProductById(productId)
      .then((product) => (this.product = product))
      .catch((error) => console.error('Error loading product:', error));
  }

  private loadBrands(): void {
    this.brandService
      .getAllBrands()
      .then((brands) => (this.brands = brands))
      .catch((error) => console.error('Error loading brands:', error));
  }

  private loadCategories(): void {
    this.categoryService
      .getAllCategories()
      .then((categories) => (this.categories = categories))
      .catch((error) => console.error('Error loading categories:', error));
  }

  saveProduct(): void {
    if (this.isEditing) {
      this.productService
        .updateProduct(this.product.id, this.product)
        .then(() => this.router.navigate(['/products']))
        .catch((error) => console.error('Error updating product:', error));
    } else {
      this.productService
        .addProduct(this.product)
        .then(() => this.router.navigate(['/products']))
        .catch((error) => console.error('Error adding product:', error));
    }
  }
}