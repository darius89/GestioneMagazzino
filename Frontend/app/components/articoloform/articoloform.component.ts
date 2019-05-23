import { Component, OnInit } from '@angular/core';
import { Articolo } from 'src/app/models/articolo';
import { ArticoliService } from 'src/app/services/articoli.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app-articoloform',
    templateUrl: './articoloform.component.html',
    styleUrls: ['./articoloform.component.css']
})
export class ArticoloformComponent implements OnInit {

    articolo: Articolo;
    submitted: boolean;
    errore:boolean;

    constructor(private articoliservice: ArticoliService, private router: Router) { }

    ngOnInit() {
        this.articolo = this.articoliservice.getter();
    }

    processForm(f: NgForm) {

        this.submitted = true; {
            if (f.valid) {


                if (this.articolo.id === undefined) {
                    this.articoliservice.addArticolo(this.articolo).subscribe((articolo) => {
                        this.router.navigate(['articoli']);
                    }, (error) => {
                        console.log("error");
                    });
                } else {
                    this.articoliservice.updateArticolo(this.articolo).subscribe((articolo) => {
                        this.router.navigate(['articoli']);
                    }, (error) => {
                        console.log(error);

                    });
                }
            }
        }

    }

}
