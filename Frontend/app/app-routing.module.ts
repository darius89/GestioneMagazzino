import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaClientiComponent } from './components/listaclienti/listaclienti.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ClienteformComponent } from './components/clienteForm/clienteform/clienteform.component';
import { OrdiniComponent } from './components/ordini/ordini.component';
import { OrdineformComponent } from './components/ordineform/ordineform.component';
import { ArticoliComponent } from './components/articoli/articoli.component';
import { ArticoloformComponent } from './components/articoloform/articoloform.component';

const routes: Routes = [
{path: 'clienti', component: ListaClientiComponent},
{path: 'home',component:HomeComponent},
{path: '',component:HomeComponent},
{path: 'add',component:ClienteformComponent},
{path: 'ordini/:id',component:OrdiniComponent},
{path: 'ordineForm/:id',component:OrdineformComponent},
{path: 'articoli',component: ArticoliComponent},
{path: 'articoloForm',component: ArticoloformComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
