import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { MarcaComercial } from '@suprimentos/models/marca-comercial';
import { Material } from '@suprimentos/models/material';
import { UnidadeMedida } from '@suprimentos/models/unidade-medida';
import { MarcaComercialService } from '@suprimentos/services/marca-comercial.service';
import { MaterialService } from '@suprimentos/services/material.service';
import { RecebimentoService } from '@suprimentos/services/recebimento.service';
import { UnidadeMedidaService } from '@suprimentos/services/unidade-medida.service';

@Component({
    selector: 'app-recebimento',
    templateUrl: './recebimento-form.component.html',
})
export class RecebimentoFormComponent implements OnInit {
    public comAutorizacaoFornecimento = true;
    public unidadesMedida: UnidadeMedida[];
    public marcasComerciais: MarcaComercial[];
    public materiais: Material[];
    itens = [];

    public formGroup = this.fb.group({
        documentoFiscal: this.fb.group(
            {
                id: this.fb.control({ value: null }, Validators.required),
                serie: this.fb.control({ value: '', disabled: true }),
                tipoNotaFiscal: this.fb.control({ value: '', disabled: true }),
                dataEmissao: this.fb.control({ value: '', disabled: true }),
                dataEntrada: this.fb.control({ value: '', disabled: true }),
                valorTotal: this.fb.control({ value: '', disabled: true }),
                valorComprometido: this.fb.control({ value: '', disabled: true }),
            },
            Validators.required,
        ),
        autorizacaoFornecimento: this.fb.group({
            id: this.fb.control({ value: null }),
            complemento: this.fb.control({ value: '', disabled: true }),
            tipoItem: this.fb.control({ value: '', disabled: true }),
            fornecedor: this.fb.control({ value: '', disabled: true }),
        }),
        itens: this.fb.array([]),
    });

    public itemForm = this.fb.group({
        material: [null, Validators.required],
        marcaComercial: [null, Validators.required],
        quantidadeReceber: [null, Validators.required],
        unidadeMedida: [null, Validators.required],
        quantidadeConvertida: [null, Validators.required],
        valorTotal: [null, Validators.required],
    });

    constructor(
        private fb: FormBuilder,
        private materialService: MaterialService,
        private unidadeMedidaService: UnidadeMedidaService,
        private marcaComercialService: MarcaComercialService,
        private recebimentoService: RecebimentoService,
    ) {}

    ngOnInit(): void {}

    public aoMarcarNaoParaAF(): void {
        this.formGroup.controls['autorizacaoFornecimento'].reset();
    }

    public getItensControlAsFormArray(): FormArray {
        return this.formGroup.get('itens') as FormArray;
    }

    public adicionarItemALista(): void {
        const item = this.itemForm.value;

        console.log(item);
        this.itens.push(item);
        this.itemForm.reset();
    }

    public obterUnidadeMedidaPorDescricao(event: {
        originalEvent: InputEvent;
        query: string;
    }): void {
        this.unidadeMedidaService
            .obterUnidadeMedidaPorDescricao(event.query)
            .subscribe((unidadesMedida) => (this.unidadesMedida = unidadesMedida));
    }

    public obterMarcaComercialPorDescricao(event: {
        originalEvent: InputEvent;
        query: string;
    }): void {
        this.marcaComercialService
            .obterMarcaComercialPorDescricao(event.query)
            .subscribe((marcasComerciais) => (this.marcasComerciais = marcasComerciais));
    }

    public obterMateriaisPeloNome(event: { originalEvent: InputEvent; query: string }): void {
        this.materialService
            .obterMateriaisPeloNome(event.query)
            .subscribe((materiais) => (this.materiais = materiais));
    }

    public gravar(): void {
        const itensArr = this.itens;
        const itensNotaRecebimento = itensArr.map((item) => {
            return {
                quantidadeReceber: item.quantidadeReceber,
                quantidadeConvertida: item.quantidadeConvertida,
                valorTotal: Number(item.valorTotal),
                marcaComercialId: item.marcaComercial.id,
                materialId: item.material.id,
                unidadeMedidaId: item.unidadeMedida.id,
            };
        });

        const {
            documentoFiscal: { id: documentoFiscalId },
            autorizacaoFornecimento: { id: autorizacaoFornecimentoId },
        } = this.formGroup.value;

        const dto = {
            notaFiscalEntradaId: documentoFiscalId,
            autorizacaoFornecimentoId,
            itensNotaRecebimento,
        };

        console.log(dto);

        this.recebimentoService.salvarRecebimento(dto).subscribe((resposta) => {
            console.log(resposta);
        });
    }
}
