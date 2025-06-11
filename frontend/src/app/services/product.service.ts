import { Injectable } from '@angular/core';
import axios from 'axios';
import { ProductDTO } from '../dto/ProductDTO';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/api/products';

  async getAllProducts() {
    const response = await axios.get(`${this.baseUrl}`);
    console.log(response.data);
    return response.data;
  }

  async addProduct(product: ProductDTO) {
    const response = await axios.post(`${this.baseUrl}`, product);
    return response.data;
  }

  async updateProduct(id: number, product: ProductDTO) {
    const response = await axios.put(`${this.baseUrl}/${id}`, product);
    return response.data;
  }

  async deleteProduct(id: number) {
    await axios.delete(`${this.baseUrl}/${id}`);
  }

  async getProductsByBrand(brandId: number): Promise<ProductDTO[]> {
    const response = await axios.get<ProductDTO[]>(
      `${this.baseUrl}/brand/${brandId}`
    );
    return response.data;
  }
  async getProductsByCategory(categoryId: number): Promise<ProductDTO[]> {
    const response = await axios.get<ProductDTO[]>(
      `${this.baseUrl}/category/${categoryId}`
    );
    return response.data;
  }
}
