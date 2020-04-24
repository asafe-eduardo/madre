import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pageable } from '../shared/pageable';
import { PacienteResumo } from './models/paciente-resumo';

@Injectable({
    providedIn: 'root',
})
export class PacientesService {
    private readonly apiUrl = 'pacientes/api/pacientes';

    constructor(private httpService: HttpClient) {}

    getListaDePacientes(): Observable<Pageable<PacienteResumo>> {
        return this.httpService.get<Pageable<PacienteResumo>>(`${this.apiUrl}/_resumo`);
    }
    getListaDePacientesElastic(nome: string): Observable<Pageable<PacienteResumo>> {
        const params = new HttpParams().set('nome', nome);

        return this.httpService.get<Pageable<PacienteResumo>>(
            `${this.apiUrl}/lista-de-pacientes-elastic`,
            {
                params,
            },
        );
    }
}