import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { StockService } from '../../services/stock.service';
import { StockDTO } from '../../dto/StockDTO';
import { Router } from '@angular/router';
import { StockModel } from '../../dto/StockDTO';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-stock-list',
  imports: [CommonModule, MatCardModule],
  templateUrl: './stock-list.component.html',
  styleUrl: './stock-list.component.css',
})
export class StockListComponent {
  stocks: StockDTO[] = [];
  stockModels: StockModel[] = [];
  storeId: number | null = null;

  constructor(
    private stockService: StockService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.storeId = params.get('storeId')
        ? Number(params.get('storeId'))
        : null;
      this.loadStocks();
    });
  }

  private loadStocks(): void {
    if (this.storeId !== null) {
      this.stockService
        .getStocksByStore(this.storeId)
        .then(async (stocks) => {
          this.stocks = stocks;
          this.stockModels = await Promise.all(
            stocks.map((stock) =>
            this.stockService.convertStockDTOToStockModel(stock)
          )
        );
      })
        .catch((error) => console.error('Error loading store stocks:', error));
    } else {
      this.stockService
        .getAllStocks()
        .then(async (stocks) => {
          this.stocks = stocks;
          this.stockModels = await Promise.all(
            stocks.map((stock) =>
            this.stockService.convertStockDTOToStockModel(stock)
          )
        );
      })
      .catch((error) => console.error('Error loading all stocks:', error));
    }
  }

  addStock(): void {
    this.router.navigate(['/add-stock']);
  }

  editStock(productId: number, storeId: number): void {
    this.router.navigate(['/edit-stock', productId, storeId]);
  }

  deleteStock(productId: number, storeId: number): void {
    this.stockService.deleteStock(productId, storeId).then(() => {
      this.stocks = this.stocks.filter(
        (stock) => !(stock.productId === productId && stock.storeId === storeId)
      );
      this.stockModels = this.stockModels.filter(
        (stock) => !(stock.productId === productId && stock.storeId === storeId)
      );
    });
  }
}
