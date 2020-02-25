import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

export interface RestaurantList {
  'restaurantlist': string[];
}

export interface RestaurantMenu {
  'menu': MenuItem[];
}

export interface MenuItem {
  'name': string;
  'calories': number;
  'price': number;
}

export interface Restaurant {
  'name': string;
  'menu': RestaurantMenu;
  'location': string;
  'hours': string;
  'image': string;
}
