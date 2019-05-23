import { Cliente } from './cliente';
import { Articolo } from './articolo';

export class Ordine {
    numeroOrdine: number;
    cliente : number;
    stato : string;
    prezzoTotale :  number;
    articolo : Articolo;
    quantita : number;
    data : string;
    
}