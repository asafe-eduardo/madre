import { NgModule, ModuleWithProviders } from '@angular/core';

import { JhiDateUtils } from './date-util.service';
import { UnidadeHospitalarService } from '../unidade-hospitalar/unidade-hospitalar.service';
import { UsuarioService } from '../usuario/usuario.service';
import { PreCadastroService } from '../pre-cadastro/pre-cadastro.service';
/* jhipster-needle-add-shared-service-import - JHipster will add shared services imports here */

@NgModule({})
export class SharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: [
        JhiDateUtils,
        UnidadeHospitalarService,
        UsuarioService,
        PreCadastroService,
        /* jhipster-needle-add-shared-services - JHipster will add shared services here */
      ]
    };
  }
}
