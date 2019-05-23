import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClientiService } from 'src/app/services/clienti.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-listaclienti',
    templateUrl: './listaclienti.component.html',
    styleUrls: ['./listaclienti.component.css']
})
export class ListaClientiComponent implements OnInit {

    public clienti: Cliente[];
    private clientiService: ClientiService;
    private router: Router;

    constructor(clientiService: ClientiService, router: Router) {
        this.clientiService = clientiService;
        this.router = router;
    }

    ngOnInit() {

        this.clientiService.getClienti().subscribe((clienti: Cliente[]) => {
            this.clienti = clienti;

        })

    }
    deleteCliente(cliente) {
        this.clientiService.deleteCliente(cliente.id).subscribe((data) => this.clienti.splice(this.clienti.indexOf(cliente), 1));

    }
    updateCliente(cliente) {
        this.clientiService.setter(cliente);
        this.router.navigate(['/add']);
    }

    newCliente() {
        let cliente = new Cliente();
        this.clientiService.setter(cliente);
        this.router.navigate(['add']);
    }
}