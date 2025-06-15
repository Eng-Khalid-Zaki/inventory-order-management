import { Injectable } from '@angular/core';
import axios from 'axios';
import { StockDTO } from '../dto/StockDTO';
import { StockModel } from '../dto/StockDTO';
@Injectable({
  providedIn: 'root',
})
export class StockService {
  private baseUrl = 'http://localhost:8080/api/stocks';

  constructor() {}

  async getAllStocks(): Promise<StockDTO[]> {
    const response = await axios.get<StockDTO[]>(`${this.baseUrl}`);
    return response.data;
  }

  async addStock(stock: StockDTO): Promise<StockDTO> {
    try {
      const response = await axios.post<StockDTO>(`${this.baseUrl}`, stock);
      return response.data;
    } catch (error) {
      this.handleError(error);
      throw error;
    }
  }

  async updateStock(
    productId: number,
    storeId: number,
    stock: StockDTO
  ): Promise<StockDTO> {
    try {
      const response = await axios.put<StockDTO>(
        `${this.baseUrl}/${storeId}/${productId}`,
        stock
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
      console.error('Server Error:', error.response.data);
    } else if (error.request) {
      alert('Error: No response from the server.');
      console.error('No Response:', error.request);
    } else {
      alert(`Error: ${error.message}`);
      console.error('Request Error:', error.message);
    }
  }

  async deleteStock(productId: number, storeId: number): Promise<void> {
    await axios.delete(`${this.baseUrl}/${storeId}/${productId}`);
  }

  async getStocksByStore(storeId: number): Promise<StockDTO[]> {
    const response = await axios.get<StockDTO[]>(
      `${this.baseUrl}/stores/${storeId}`
    );
    return response.data;
  }

  async convertStockDTOToStockModel(stockDTO: StockDTO): Promise<StockModel> {
    try {
      const productResponse = await axios.get(
        `http://localhost:8080/api/products/${stockDTO.productId}`
      );
      const productName =
        productResponse.data?.productName || 'Unknown Product';

      const storeResponse = await axios.get(
        `http://localhost:8080/api/stores/${stockDTO.storeId}`
      );
      const storeName = storeResponse.data?.storeName || 'Unknown Store';

      return {
        productId: stockDTO.productId,
        productName: productName,
        quantity: stockDTO.quantity,
        storeId: stockDTO.storeId,
        storeName: storeName,
      };
    } catch (error) {
      console.error('Error converting stock:', error);
      return {
        productId: stockDTO.productId,
        productName: 'Error fetching product',
        quantity: stockDTO.quantity,
        storeId: stockDTO.storeId,
        storeName: 'Error fetching store',
      };
    }
  }
}
