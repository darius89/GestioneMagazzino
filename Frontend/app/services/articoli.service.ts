import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Articolo } from '../models/articolo';

@Injectable({
    providedIn: 'root'
})
@Injectable()
export class ArticoliService {

    baseUrl: string = 'http://localhost:8080/articoli';
    options = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    }
    articolo : Articolo;

    constructor(private http: HttpClient) {


    }
    getArticoli() {
        return this.http.get(this.baseUrl + '/list');

    }
    deleteArticolo(id: number) {

        return this.http.delete(this.baseUrl + '/articolo/' + id);
    }


    addArticolo(articolo: Articolo) {

        return this.http.post(this.baseUrl + '/articolo', JSON.stringify(articolo), this.options);
    }

    updateArticolo(articolo: Articolo) {

        return this.http.put(this.baseUrl + '/articolo', JSON.stringify(articolo), this.options);
    }

    setter(articolo: Articolo) {
        this.articolo = articolo;
    }

    getter() {
        return this.articolo;
    }

}
