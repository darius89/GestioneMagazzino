import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaClientiComponent } from './components/listaclienti/listaclienti.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { ClienteformComponent } from './components/clienteForm/clienteform/clienteform.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { OrdiniComponent } from './components/ordini/ordini.component';
import { OrdineformComponent } from './components/ordineform/ordineform.component';
import { ArticoliComponent } from './components/articoli/articoli.component';
import { ArticoloformComponent } from './components/articoloform/articoloform.component';


@NgModule({
  declarations: [
    AppComponent,
    ListaClientiComponent,
    HomeComponent,
    ClienteformComponent,
    OrdiniComponent,
    OrdineformComponent,
    ArticoliComponent,
    ArticoloformComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
