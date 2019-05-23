import { Component, OnInit } from '@angular/core';
import { Articolo } from 'src/app/models/articolo';
import { ArticoliService } from 'src/app/services/articoli.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
    selector: 'app-articoli',
    templateUrl: './articoli.component.html',
    styleUrls: ['./articoli.component.css']
})
export class ArticoliComponent implements OnInit {

    articoli: Articolo[];
    errore: boolean;

    constructor(private articoliservice: ArticoliService,private router:Router) { }

    ngOnInit() {
        this.articoliservice.getArticoli().subscribe((articoli: Articolo[]) => this.articoli = articoli);
        this.errore = false;
    }
    newArticolo() {
        let articolo = new Articolo();
        this.articoliservice.setter(articolo);
        this.router.navigate(['articoloForm']);
    }

    updateArticolo(articolo) {
        this.articoliservice.setter(articolo);
        this.router.navigate(['articoloForm']);
    }

    deleteArticolo(articolo) {
        this.articoliservice.deleteArticolo(articolo.id).subscribe(
            data => {
                this.articoli.splice(this.articoli.indexOf(articolo), 1)
                console.log("success delete");
            },
            error => {
                console.log("error delete");
                this.errore = true;
                
            }
        )

    }
   

}
