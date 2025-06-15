import { Injectable } from '@angular/core';
import axios from 'axios';
import { StoreDTO } from '../dto/StoreDTO';
import { StockDTO } from '../dto/StockDTO';

@Injectable({
  providedIn: 'root',
})
export class StoreService {
  private baseUrl = 'http://localhost:8080/api/stores';

  constructor() {}

  async getAllStores(): Promise<StoreDTO[]> {
    const response = await axios.get<StoreDTO[]>(`${this.baseUrl}`);
    return response.data;
  }

  async addStore(store: StoreDTO): Promise<StoreDTO> {
    try {
      const response = await axios.post<StoreDTO>(`${this.baseUrl}`, store);
      return response.data;
    } catch (error) {
      this.handleError(error);
      throw error;
    }
  }

  async updateStore(id: number, store: StoreDTO): Promise<StoreDTO> {
    try {
      const response = await axios.put<StoreDTO>(
        `${this.baseUrl}/${id}`,
        store
      );
      return response.data;
    } catch (error) {
      this.handleError(error);
      throw error;
    }
  }

  private handleError(error: any): void {
    if (error.response) {
      const errorMessage =
        error.response.data?.message || JSON.stringify(error.response.data);

      alert(`Error: ${errorMessage}`);
      console.error('Server Error:', errorMessage);
    } else if (error.request) {
      alert('Error: No response from the server.');
      console.error('No Response:', error.request);
    } else {
      alert(`Error: ${error.message}`);
      console.error('Request Error:', error.message);
    }
  }

  async deleteStore(id: number): Promise<void> {
    await axios.delete(`${this.baseUrl}/${id}`);
  }

  async getStoreById(id: number): Promise<StoreDTO> {
    const response = await axios.get<StoreDTO>(`${this.baseUrl}/${id}`);
    return response.data;
  }
}
