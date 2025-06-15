import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'inventory-management-frontend';

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.navigate(['/products']);
  }

  goToProducts(): void {
    this.router.navigate(['/products']);
  }

  goToCategories(): void {
    this.router.navigate(['/categories']);
  }

  goToBrands(): void {
    this.router.navigate(['/brands']);
  }

  goToStocks(): void {
    this.router.navigate(['/stocks']);
  }

  goToStores(): void {
    this.router.navigate(['/stores']);
  }
}
