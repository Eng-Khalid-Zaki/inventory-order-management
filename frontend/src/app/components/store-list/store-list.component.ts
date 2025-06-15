import { Component } from '@angular/core';
import { StoreService } from '../../services/store.service';
import { StoreDTO } from '../../dto/StoreDTO';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-store-list',
  imports: [CommonModule, MatCardModule],
  templateUrl: './store-list.component.html',
  styleUrl: './store-list.component.css',
})
export class StoreListComponent {
  stores: StoreDTO[] = [];

  constructor(private storeService: StoreService, private router: Router) {}

  ngOnInit(): void {
    this.storeService.getAllStores().then((stores) => (this.stores = stores));
  }

  addStore(): void {
    this.router.navigate(['/add-store']);
  }

  editStore(storeId: number): void {
    this.router.navigate(['/edit-store', storeId]);
  }

  deleteStore(id: number): void {
    this.storeService.deleteStore(id).then(() => {
      this.stores = this.stores.filter((store) => store.id !== id);
    });
  }

  viewStocks(storeId: number): void {
    this.router.navigate(['/stocks/stores', storeId]);
  }
}
