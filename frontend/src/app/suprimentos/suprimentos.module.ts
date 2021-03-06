import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from '@shared/shared.module';
import { AutorizacaoFornecimentoComponent } from './components/autorizacao-fornecimento/autorizacao-fornecimento.component';
import { ConsultaEstoqueComponent } from './components/consulta-estoque/consulta-estoque.component';
import { DocumentoFiscalEntradaComponent } from './components/documento-fiscal-entrada/documento-fiscal-entrada.component';
import { NotaFiscalFormComponent } from './components/documento-fiscal-entrada/nota-fiscal/nota-fiscal-form.component';
import { EfetivacaoTransferenciaAutomaticaComponent } from './components/efetivacao-transferencia-automatica/efetivacao-transferencia-automatica.component';
import { InclusaoSaldoEstoqueComponent } from './components/inclusao-saldo-estoque/inclusao-saldo-estoque.component';
import { RecebimentoFormComponent } from './components/recebimento/recebimento-form.component';
import { RequisicaoMaterialNaoEfetivadaComponent } from './components/requisicao-material-nao-efetivada/requisicao-material-nao-efetivada.component';
import { RequisicaoMaterialFormComponent } from './components/requisicao-material/requisicao-material-form.component';
import { RequisicaoMaterialComponent } from './components/requisicao-material/requisicao-material.component';
import { TransferenciaAutomaticaNaoEfetivadaComponent } from './components/transferencia-automatica-nao-efetivada/transferencia-automatica-nao-efetivada.component';
import { TransferenciaAutomaticaFormComponent } from './components/transferencia-automatica/transferencia-automatica-form.component';
import { TransferenciaAutomaticaComponent } from './components/transferencia-automatica/transferencia-automatica.component';
import { routes } from './suprimentos.routes';

@NgModule({
    declarations: [
        DocumentoFiscalEntradaComponent,
        AutorizacaoFornecimentoComponent,
        RecebimentoFormComponent,
        NotaFiscalFormComponent,
        TransferenciaAutomaticaComponent,
        TransferenciaAutomaticaFormComponent,
        EfetivacaoTransferenciaAutomaticaComponent,
        TransferenciaAutomaticaNaoEfetivadaComponent,
        RequisicaoMaterialComponent,
        RequisicaoMaterialFormComponent,
        InclusaoSaldoEstoqueComponent,
        RequisicaoMaterialNaoEfetivadaComponent,
        ConsultaEstoqueComponent,
    ],
    imports: [CommonModule, SharedModule, RouterModule.forChild(routes)],
})
export class SuprimentosModule {}
