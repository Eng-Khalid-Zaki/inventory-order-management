import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { ProductDTO } from '../../dto/ProductDTO';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css'],
})
export class ProductFormComponent {
  product: ProductDTO = {
    id: 0,
    productName: '',
    modelYear: 2025,
    listPrice: 0,
  };
  isEditing: boolean = false;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.isEditing = true;
      this.loadProduct(Number(productId));
    }
  }

  private loadProduct(productId: number): void {
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
