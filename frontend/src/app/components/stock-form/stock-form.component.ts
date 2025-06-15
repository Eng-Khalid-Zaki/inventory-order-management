import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router } from '@angular/router';
import { StockService } from '../../services/stock.service';
import { ProductDTO } from '../../dto/ProductDTO';
import { StoreDTO } from '../../dto/StoreDTO';
import { StockDTO } from '../../dto/StockDTO';
import axios from 'axios';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-stock-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
  ],
  templateUrl: './stock-form.component.html',
  styleUrl: './stock-form.component.css',
})
export class StockFormComponent implements OnInit {
  products: ProductDTO[] = [];
  stores: StoreDTO[] = [];
  selectedProductId!: number;
  selectedStoreId!: number;
  quantity!: number;
  stockId!: number;
  isEditing = false;

  constructor(
    private stockService: StockService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    axios
      .get('http://localhost:8080/api/products')
      .then((response) => {
        this.products = response.data;
      })
      .catch((error) => console.error('Error fetching products:', error));

    axios
      .get('http://localhost:8080/api/stores')
      .then((response) => {
        this.stores = response.data;
      })
      .catch((error) => console.error('Error fetching stores:', error));

    this.route.paramMap.subscribe((params) => {
      const productId = params.get('productId');
      const storeId = params.get('storeId');
      if (productId && storeId) {
        this.isEditing = true;
        this.stockId = Number(productId);

        axios
          .get(`http://localhost:8080/api/stocks/${storeId}/${productId}`)
          .then((response) => {
            const stock = response.data;
            this.selectedProductId = stock.productId;
            this.selectedStoreId = stock.storeId;
            this.quantity = stock.quantity;
          })
          .catch((error) => console.error('Error fetching stock:', error));
      }
    });
  }

  onSubmit(): void {
    const stockData: StockDTO = {
      productId: this.selectedProductId,
      quantity: this.quantity,
      storeId: this.selectedStoreId,
    };

    if (this.isEditing) {
      this.stockService
        .updateStock(this.stockId, this.selectedStoreId, stockData)
        .then(() => {
          console.log('Stock updated successfully');
          this.router.navigate(['/stocks']);
        })
        .catch((error) => console.error('Error updating stock:', error));
    } else {
      this.stockService
        .addStock(stockData)
        .then(() => {
          console.log('Stock added successfully');
          this.router.navigate(['/stocks']);
        })
        .catch((error) => console.error('Error adding stock:', error));
    }
  }
}
