import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router } from '@angular/router';
import { StoreService } from '../../services/store.service';
import { StoreDTO } from '../../dto/StoreDTO';

@Component({
  selector: 'app-store-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './store-form.component.html',
  styleUrls: ['./store-form.component.css'],
})
export class StoreFormComponent {
  store: StoreDTO = {
    id: 0,
    storeName: '',
    phone: '',
    email: '',
    street: '',
    city: '',
    zipCode: '',
  };

  isEditing: boolean = false;

  constructor(
    private storeService: StoreService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const storeId = this.route.snapshot.paramMap.get('storeId');
    if (storeId) {
      this.isEditing = true;
      this.loadStore(Number(storeId));
    }
  }

  private loadStore(storeId: number): void {
    this.storeService
      .getStoreById(storeId)
      .then((store) => (this.store = store))
      .catch((error) => console.error('Error loading store:', error));
  }

  saveStore(): void {
    if (this.isEditing) {
      this.storeService
        .updateStore(this.store.id, this.store)
        .then(() => this.router.navigate(['/stores']))
        .catch((error) => console.error('Error updating store:', error));
    } else {
      this.storeService
        .addStore(this.store)
        .then(() => this.router.navigate(['/stores']))
        .catch((error) => console.error('Error adding store:', error));
    }
  }
}
