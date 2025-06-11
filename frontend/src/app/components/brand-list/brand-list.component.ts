import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { BrandService } from '../../services/brand.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Brand } from '../../dto/BrandDTO';

@Component({
  selector: 'app-brand-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './brand-list.component.html',
  styleUrls: ['./brand-list.component.css'],
})
export class BrandListComponent {
  brands: Brand[] = [];
  newBrandName: string = '';
  editingBrandId: number | null = null;
  editedBrandName: string = '';
  constructor(private brandService: BrandService, private router: Router) {}

  ngOnInit(): void {
    this.loadBrands();
  }

  private loadBrands(): void {
    this.brandService
      .getAllBrands()
      .then((brands) => (this.brands = brands))
      .catch((error) => console.error('Error loading brands:', error));
  }

  viewProducts(brandId: number): void {
    this.router.navigate(['/products/brand', brandId]);
  }

  editBrand(brandId: number): void {
    this.router.navigate(['/edit', brandId]);
  }

  deleteBrand(brandId: number): void {
    this.brandService
      .deleteBrand(brandId)
      .then(() => {
        this.loadBrands();
      })
      .catch((error) => console.error('Error deleting brand:', error));
  }

  addBrand(): void {
    const brand = {id: 0, brandName: this.newBrandName, products: [] };
    this.brandService
      .addBrand(brand)
      .then(() => {
        this.newBrandName = '';
        this.loadBrands();
      })
      .catch((error) => console.error('Error adding brand:', error));
  }

  startEditing(brand: Brand): void {
    this.editingBrandId = brand.id;
    this.editedBrandName = brand.brandName;
  }

  cancelEditing(): void {
    this.editingBrandId = null;
    this.editedBrandName = '';
  }

  saveEdit(): void {
    if (!this.editedBrandName.trim() || this.editingBrandId === null) return;

    this.brandService
      .updateBrand(this.editingBrandId, {id: 0, brandName: this.editedBrandName})
      .then(() => {
        this.editingBrandId = null;
        this.editedBrandName = '';
        this.loadBrands();
      })
      .catch((error) => console.error('Error updating brand:', error));
  }


}
