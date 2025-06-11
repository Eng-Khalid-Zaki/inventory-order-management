

import { Injectable } from '@angular/core';
import axios from 'axios';
import { Category } from '../dto/CategoryDTO';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private baseUrl = 'http://localhost:8080/api/categories';

  async getAllCategories(): Promise<Category[]> {
    const response = await axios.get<Category[]>(`${this.baseUrl}`);
    return response.data;
  }

  async addCategory(category: Category): Promise<Category> {
    const response = await axios.post<Category>(`${this.baseUrl}`, category);
    return response.data;
  }

  async updateCategory(id: number, category: Category): Promise<Category> {
    const response = await axios.put<Category>(
      `${this.baseUrl}/${id}`,
      category
    );
    return response.data;
  }

  async deleteCategory(id: number): Promise<void> {
    await axios.delete(`${this.baseUrl}/${id}`);
  }
}