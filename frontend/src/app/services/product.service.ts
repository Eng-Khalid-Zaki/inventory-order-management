import { Injectable } from '@angular/core';
import axios from 'axios';
import { ResponseProductDTO } from '../dto/ProductDTO';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/api/products';

  async getAllProducts() {
    const response = await axios.get(`${this.baseUrl}`);
    return response.data;
  }

  async updateProduct(id: number, product: ResponseProductDTO) {
    try {
      const response = await axios.put(`${this.baseUrl}/${id}`, product);
      return response.data;
    } catch (error) {
      this.handleError(error);
      throw error;
    }
  }

  async addProduct(product: ResponseProductDTO) {
    try {
      const response = await axios.post(`${this.baseUrl}`, product);
      return response.data;
    } catch (error) {
      this.handleError(error);
      throw error;
    }
  }

  private handleError(error: any): void {
    if (error.response) {
      const backendError = error.response.data;

      const errorMessage =
        backendError?.message || JSON.stringify(backendError);

      alert(`Error: ${errorMessage}`);
      console.error('Server Error:', backendError);
    } else if (error.request) {
      alert('Error: No response from server.');
      console.error('No Response:', error.request);
    } else {
      alert(`Error: ${error.message}`);
      console.error('Request Error:', error.message);
    }
  }

  async deleteProduct(id: number) {
    await axios.delete(`${this.baseUrl}/${id}`);
  }

  async getProductsByBrand(brandId: number): Promise<ResponseProductDTO[]> {
    const response = await axios.get<ResponseProductDTO[]>(
      `${this.baseUrl}/brand/${brandId}`
    );
    return response.data;
  }
  async getProductsByCategory(
    categoryId: number
  ): Promise<ResponseProductDTO[]> {
    const response = await axios.get<ResponseProductDTO[]>(
      `${this.baseUrl}/category/${categoryId}`
    );
    return response.data;
  }

  async getProductById(id: number): Promise<ResponseProductDTO> {
    const response = await axios.get<ResponseProductDTO>(
      `${this.baseUrl}/${id}`
    );
    return response.data;
  }
}
