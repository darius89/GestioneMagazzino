import { Injectable } from '@angular/core';
import { Cliente } from '../models/cliente';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Ordine } from '../models/ordine';


@Injectable({
    providedIn: 'root'
})
@Injectable()
export class ClientiService {

    baseUrl: string = 'http://localhost:8080/clienti';
    cliente: Cliente;
    ordine: Ordine;
    options = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    }


    constructor(private http: HttpClient) {


    }
    getClienti() {
        return this.http.get(this.baseUrl + '/list');

    }

    deleteCliente(id: number) {

        return this.http.delete(this.baseUrl + '/cliente/' + id);
    }


    addCliente(cliente: Cliente) {

        return this.http.post(this.baseUrl + '/cliente', JSON.stringify(cliente), this.options);
    }

    updateCliente(cliente: Cliente) {

        return this.http.put(this.baseUrl + '/cliente', JSON.stringify(cliente), this.options);
    }

    setter(cliente: Cliente) {
        this.cliente = cliente;
    }

    getter() {
        return this.cliente;
    }
    setOrdine(ordine: Ordine) {
        this.ordine = ordine;
    }

    getOrdine() {
        return this.ordine;
    }
    getOrdini(idCliente, date) {

        let params = new HttpParams();
        params = params.append('myDate', date);
        return this.http.get(this.baseUrl + '/' + idCliente + '/ordini', { params });

    }
    deleteOrdine(idCliente:number, numeroOrdine:number) {

        return this.http.delete(this.baseUrl + '/' + idCliente + '/ordine/'+numeroOrdine);
    }
    addOrdine(idCliente: number, ordine: Ordine) {

        return this.http.post(this.baseUrl + '/' + idCliente + '/ordine', JSON.stringify(ordine), this.options);
    }

    updateOrdine(idCliente, ordine: Ordine ) {

        return this.http.put(this.baseUrl+'/'+idCliente+"/ordine", JSON.stringify(ordine), this.options);
    }

}
