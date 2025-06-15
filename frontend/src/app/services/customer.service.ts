import { Injectable } from '@angular/core';
import axios from 'axios';
import { CustomerDTO } from '../dto/CustomerDTO';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/api/customers';

  constructor() {}

  async getAllCustomers(): Promise<CustomerDTO[]> {
    const response = await axios.get<CustomerDTO[]>(`${this.baseUrl}`);
    return response.data;
  }

  async getCustomerById(id: number): Promise<CustomerDTO> {
    const response = await axios.get<CustomerDTO>(`${this.baseUrl}/${id}`);
    return response.data;
  }

  async addCustomer(customer: CustomerDTO): Promise<CustomerDTO> {
    try {
      console.log(customer);
      const response = await axios.post<CustomerDTO>(`${this.baseUrl}`, customer);
      return response.data;
    } catch (error) {
      this.handleError(error);
      throw error;
    }
  }
  
  async updateCustomer(id: number, customer: CustomerDTO): Promise<CustomerDTO> {
    try {
      const response = await axios.put<CustomerDTO>(`${this.baseUrl}/${id}`, customer);
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

  async deleteCustomer(id: number): Promise<void> {
    await axios.delete(`${this.baseUrl}/${id}`);
  }
}
