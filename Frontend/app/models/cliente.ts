import { Ordine } from './ordine';

export class Cliente {
    id : number;
    cf : string;
    nome : string;
    cognome : string;
    indirizzo : string;
    ordini: Ordine[];


}