import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerDTO } from '../../dto/CustomerDTO';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
  ],
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css'],
})
export class CustomerFormComponent implements OnInit {
  customer: CustomerDTO = {
    id: 0,
    firstName: '',
    lastName: '',
    phone: '',
    email: '',
    street: '',
    city: '',
    state: '',
    zipCode: '',
    createdAt: new Date(),
    updatedAt: 'Not updated',
  };

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const customerId = this.getCustomerIdFromRoute();
    if (customerId) {
      this.loadCustomer(customerId);
    }
  }

  getCustomerIdFromRoute(): number | null {
    const urlSegments = this.router.url.split('/');
    const idSegment = urlSegments[urlSegments.length - 1];
    return !isNaN(Number(idSegment)) ? Number(idSegment) : null;
  }

  loadCustomer(customerId: number): void {
    this.customerService
      .getCustomerById(customerId)
      .then((data: CustomerDTO) => {
        this.customer = {
          ...data,
          updatedAt: data.updatedAt
            ? new Date(data.updatedAt).toISOString().split('T')[0]
            : 'Not updated',
        };
      });
  }

  saveCustomer(form: NgForm): void {
    if (form.invalid) return;

    if (
      this.customer.createdAt &&
      !isNaN(new Date(this.customer.createdAt).getTime())
    ) {
      this.customer.createdAt = new Date(this.customer.createdAt)
        .toISOString()
        .split('T')[0];
    } else {
      this.customer.createdAt = null;
    }

    if (this.customer.updatedAt === 'Not updated') {
      this.customer.updatedAt = null;
    } else if (
      this.customer.updatedAt &&
      typeof this.customer.updatedAt === 'string'
    ) {
      this.customer.updatedAt = new Date(this.customer.updatedAt)
        .toISOString()
        .split('T')[0];
    }

    const customerPayload = { ...this.customer };

    if (this.customer.id) {
      this.customerService
        .updateCustomer(this.customer.id, customerPayload)
        .then(() => this.router.navigate(['/customers']));
    } else {
      this.customerService
        .addCustomer(customerPayload)
        .then(() => this.router.navigate(['/customers']));
    }
  }
}
