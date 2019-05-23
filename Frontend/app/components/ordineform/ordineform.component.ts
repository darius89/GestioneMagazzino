import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Ordine } from 'src/app/models/ordine';
import { ClientiService } from 'src/app/services/clienti.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Articolo } from 'src/app/models/articolo';
import { ArticoliService } from 'src/app/services/articoli.service';

@Component({
    selector: 'app-ordineform',
    templateUrl: './ordineform.component.html',
    styleUrls: ['./ordineform.component.css']
})
export class OrdineformComponent implements OnInit {

    idCliente: number;
    submitted: boolean;
    ordine: Ordine;
    articoli: Articolo[];
    errore: boolean;
    tipiBottiglia: Set<string>;

    constructor(private clientiService: ClientiService, private router: Router, private route: ActivatedRoute, private articoliService: ArticoliService) {
        route.params.subscribe(params => { this.idCliente = params['id']; });
    }

    ngOnInit() {
        this.errore = false;
        this.ordine = this.clientiService.getOrdine();
        this.articoliService.getArticoli().subscribe((articoli:Articolo[]) => {
            this.articoli = articoli;
        this.tipiBottiglia = new Set();
        for(let i in this.articoli) {
            this.tipiBottiglia.add(this.articoli[i].tipoBottiglia);

        }
        });
    }

    processForm(f: NgForm) {

        this.submitted = true; {
            if (f.valid) {
                if (this.ordine.numeroOrdine === undefined) {
                    this.clientiService.addOrdine(this.idCliente, this.ordine).subscribe((ordine) => {
                        this.router.navigate(['ordini', this.idCliente]);
                    }, (error) => {
                        console.log("error");
                        this.errore = true;
                    });
                } else {
                    this.clientiService.updateOrdine(this.idCliente, this.ordine).subscribe((ordine) => {
                        this.router.navigate(['ordini', this.idCliente]);
                    }, (error) => {
                        console.log(error);
                        this.errore = true;
                    });
                }
            }
        }

    }

}


