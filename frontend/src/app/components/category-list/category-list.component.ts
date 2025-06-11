import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { CategoryService } from '../../services/category.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Category } from '../../dto/CategoryDTO';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css'],
})
export class CategoryListComponent {
  categories: Category[] = [];
  newCategoryName: string = '';
  editingCategoryId: number | null = null;
  editedCategoryName: string = '';

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  private loadCategories(): void {
    this.categoryService
      .getAllCategories()
      .then((categories) => (this.categories = categories))
      .catch((error) => console.error('Error loading categories:', error));
  }

  addCategory(): void {
    const category = { id: 0, categoryName: this.newCategoryName };
    this.categoryService
      .addCategory(category)
      .then(() => {
        this.newCategoryName = '';
        this.loadCategories();
      })
      .catch((error) => console.error('Error adding category:', error));
  }
  
  viewProducts(categoryId: number): void {
    this.router.navigate(['/products/category', categoryId]);
  }

  startEditing(category: Category): void {
    this.editingCategoryId = category.id;
    this.editedCategoryName = category.categoryName;
  }

  cancelEditing(): void {
    this.editingCategoryId = null;
    this.editedCategoryName = '';
  }

  saveEdit(): void {
    if (!this.editedCategoryName.trim() || this.editingCategoryId === null)
      return;

    this.categoryService
      .updateCategory(this.editingCategoryId, {
        id: 0,
        categoryName: this.editedCategoryName,
      })
      .then(() => {
        this.editingCategoryId = null;
        this.editedCategoryName = '';
        this.loadCategories();
      })
      .catch((error) => console.error('Error updating category:', error));
  }

  deleteCategory(categoryId: number): void {
    this.categoryService
      .deleteCategory(categoryId)
      .then(() => this.loadCategories())
      .catch((error) => console.error('Error deleting category:', error));
  }
}
