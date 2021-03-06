import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class ClassificacaoDeRiscoService {
    private readonly apiUrl = '/api/triagens';

    constructor(private httpService: HttpClient) {}

    cadastrarRisco(cadastroRisco: Observable<any>) {
        this.httpService.post(this.apiUrl, cadastroRisco);
        console.log(cadastroRisco);
    }
}
