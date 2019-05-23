import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientiService } from 'src/app/services/clienti.service';
import { Ordine } from 'src/app/models/ordine';
import { stringify } from '@angular/compiler/src/util';
import { Articolo } from 'src/app/models/articolo';

@Component({
    selector: 'app-ordini',
    templateUrl: './ordini.component.html',
    styleUrls: ['./ordini.component.css']
})
export class OrdiniComponent implements OnInit {

    idCliente: number;
    ordini: Ordine[];
    myDate: string;
    quantitaTotale: number;
    sommaPrezzi: number;

    constructor(private route: ActivatedRoute, private clientiService: ClientiService, private router: Router) {
        route.params.subscribe(params => { this.idCliente = params['id']; });

    }

    ngOnInit() {

        this.myDate = new Date().toISOString().split('T')[0];
        this.quantitaTotale = 0;
        this.sommaPrezzi = 0;
    }

    canAddOrder() {


        if (this.getCurrentDate().indexOf(this.myDate) !== -1) {
            return true;
        }
        return false;

    }

    cercaOrdini() {
        this.clientiService.getOrdini(this.idCliente, this.myDate).subscribe((ordini: Ordine[]) => {
            this.ordini = ordini;
            this.getTotal();

        })

    }

    getCurrentDate() {

        var today = new Date();

        let currentDate: string;
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0');
        var yyyy = today.getFullYear();

        currentDate = yyyy + '-' + mm + '-' + dd;
        return currentDate;

    }

    newOrdine() {
        let ordine = new Ordine();
        ordine.articolo = new Articolo();
        this.clientiService.setOrdine(ordine);
        this.router.navigate(['ordineForm', this.idCliente]);
    }

    updateOrdine(ordine) {
        this.clientiService.setOrdine(ordine);
        this.router.navigate(['ordineForm', this.idCliente]);
    }

    deleteOrdine(ordine) {
        this.clientiService.deleteOrdine(this.idCliente, ordine.numeroOrdine).subscribe(
            data => {
                this.ordini.splice(this.ordini.indexOf(ordine), 1)
                console.log("success delete");
                this.getTotal();
            },
            error => {
                console.log("error delete");
            }
        )

    }
    getTotal() {
        this.quantitaTotale = 0;
        this.sommaPrezzi = 0;
        for (var i in this.ordini) {
            this.quantitaTotale += this.ordini[i].quantita;
            this.sommaPrezzi += this.ordini[i].prezzoTotale;
        }
    }
}
