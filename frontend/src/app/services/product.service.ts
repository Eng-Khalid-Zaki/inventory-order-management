import { Injectable } from '@angular/core';
import axios from 'axios';

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

  async addProduct(product: any) {
    const response = await axios.post(`${this.baseUrl}`, product);
    return response.data;
  }

  async deleteProduct(id: number) {
    await axios.delete(`${this.baseUrl}/${id}`);
  }
}
