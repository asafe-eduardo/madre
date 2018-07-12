import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { HttpService } from '@basis/angular-components';
import { environment } from '../../environments/environment';

import { Acao } from './acao.model';
import { ResponseWrapper, createRequestOption, JhiDateUtils } from '../shared';

@Injectable()
export class AcaoService {

  resourceUrl = environment.apiUrl + '/acaos';

  searchUrl = environment.apiUrl + '/_search/acaos';

  constructor(private http: HttpService) {}

  create(acao: Acao): Observable<Acao> {
    const copy = this.convert(acao);
    return this.http.post(this.resourceUrl, copy).map((res: Response) => {
      const jsonResponse = res.json();
      return this.convertItemFromServer(jsonResponse);
    });
  }

  update(acao: Acao): Observable<Acao> {
    const copy = this.convert(acao);
    return this.http.put(this.resourceUrl, copy).map((res: Response) => {
      const jsonResponse = res.json();
      return this.convertItemFromServer(jsonResponse);
    });
  }

  find(id: number): Observable<Acao> {
    return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
      const jsonResponse = res.json();
      return this.convertItemFromServer(jsonResponse);
    });
  }

  query(req?: any): Observable<ResponseWrapper> {
    const options = createRequestOption(req);
    return this.http.get(this.resourceUrl, options)
      .map((res: Response) => this.convertResponse(res));
  }

  delete(id: number): Observable<Response> {
    return this.http.delete(`${this.resourceUrl}/${id}`);
  }

  private convertResponse(res: Response): ResponseWrapper {
    const jsonResponse = res.json();
    const result = [];
    for (let i = 0; i < jsonResponse.length; i++) {
      result.push(this.convertItemFromServer(jsonResponse[i]));
    }
    return new ResponseWrapper(res.headers, result, res.status);
  }

  /**
   * Convert a returned JSON object to Acao.
   */
  private convertItemFromServer(json: any): Acao {
    const entity: Acao = Object.assign(new Acao(), json);
    return entity;
  }

  /**
   * Convert a Acao to a JSON which can be sent to the server.
   */
  private convert(acao: Acao): Acao {
    const copy: Acao = Object.assign({}, acao);
    return copy;
  }
}
