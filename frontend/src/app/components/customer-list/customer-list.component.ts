import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { CustomerDTO } from '../../dto/CustomerDTO';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    FormsModule,
  ],
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css'],
})
export class CustomerListComponent implements OnInit {
  customers: CustomerDTO[] = [];

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.customerService.getAllCustomers().then((data: CustomerDTO[]) => {
      this.customers = data.map((customer) => ({
        ...customer,
        updatedAt: customer.updatedAt
          ? new Date(customer.updatedAt).toISOString().split('T')[0]
          : 'Not updated',
      }));
    });
  }

  addCustomer(): void {
    this.router.navigate(['add-customer']);
  }

  editCustomer(customerId: number): void {
    this.router.navigate([`edit-customer/${customerId}`]);
  }

  deleteCustomer(customerId: number): void {
    if (confirm('Are you sure you want to delete this customer?')) {
      this.customerService
        .deleteCustomer(customerId)
        .then(() => this.loadCustomers());
    }
  }
}
