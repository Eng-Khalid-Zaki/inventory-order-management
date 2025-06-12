import { Injectable } from '@angular/core';
import axios from 'axios';
import { Brand } from '../dto/BrandDTO';

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  private baseUrl = 'http://localhost:8080/api/brands';

  async getAllBrands(): Promise<Brand[]> {
    const response = await axios.get<Brand[]>(`${this.baseUrl}`);
    return response.data;
  }

  async addBrand(brand: Brand): Promise<Brand> {
    const response = await axios.post<Brand>(`${this.baseUrl}`, brand);
    return response.data;
  }

  async updateBrand(id: number, brand: Brand): Promise<Brand> {
    const response = await axios.put<Brand>(`${this.baseUrl}/${id}`, brand);
    return response.data;
  }

  async deleteBrand(id: number): Promise<void> {
    await axios.delete(`${this.baseUrl}/${id}`);
  }

  async getBrandById(id: number): Promise<Brand> {
    const response = await axios.get<Brand>(`${this.baseUrl}/${id}`);
    return response.data;
  }
}
