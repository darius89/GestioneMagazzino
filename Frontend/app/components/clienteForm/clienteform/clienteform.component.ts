import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClientiService } from 'src/app/services/clienti.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, AbstractControl, NgForm } from '@angular/forms';

@Component({
    selector: 'app-clienteform',
    templateUrl: './clienteform.component.html',
    styleUrls: ['./clienteform.component.css']
})

export class ClienteformComponent implements OnInit {


    cliente: Cliente;
    submitted: boolean;

    constructor(private clientiService: ClientiService, private router: Router) {
        this.submitted = false;
    }

    ngOnInit() {
        this.cliente = this.clientiService.getter();
    }


    processForm(f: NgForm) {

        this.submitted = true; {
            if (f.valid) {

                if (this.cliente.id === undefined) {
                    this.clientiService.addCliente(this.cliente).subscribe((cliente) => {
                        this.router.navigate(['clienti']);
                    }, (error) => {
                        console.log(error);
                    });
                } else {
                    this.clientiService.updateCliente(this.cliente).subscribe((cliente) => {
                        this.router.navigate(['clienti']);
                    }, (error) => {
                        console.log(error);
                    });
                }
            }
        }

    }

}
