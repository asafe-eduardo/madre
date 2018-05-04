import { Component, ViewChild, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService } from 'primeng/primeng';
import { DatatableComponent, DatatableClickEvent } from '@basis/angular-components';

import { BreadcrumbService } from '../breadcrumb/breadcrumb.service';
import { PageNotificationService } from '@basis/angular-components';
import { environment } from '../../environments/environment';
import { UnidadeHospitalar } from './unidade-hospitalar.model';
import { UnidadeHospitalarService } from './unidade-hospitalar.service';
import { ToastrService } from 'ngx-toastr';
import { NgBlockUI, BlockUI } from 'ng-block-ui';
import { MessageUtil } from './../util/message.util';
import { ExportacaoUtil } from './../util/exportacao.util';
import { ExportacaoUtilService } from './../util/service/exportacao-util.service';
import { ElasticQuery } from '../shared/elastic-query';


@Component({
  selector: 'jhi-unidade-hospitalar',
  templateUrl: './unidade-hospitalar.component.html'
})
export class UnidadeHospitalarComponent implements OnInit, OnDestroy {

  @ViewChild(DatatableComponent) datatable: DatatableComponent;

  searchUrl: string = this.unidadeHospitalarService.searchUrl;

  elasticQuery: ElasticQuery = new ElasticQuery();

  constructor(
    private router: Router,
    private unidadeHospitalarService: UnidadeHospitalarService,
    private confirmationService: ConfirmationService,
    private breadcrumbService: BreadcrumbService,
    private pageNotificationService: PageNotificationService
  ) {}

  ngOnInit() {
    this.breadcrumbService.setItems([{ label: 'Unidade Hospitalars' }]);
  }

  datatableClick(event: DatatableClickEvent) {
    if (!event.selection) {
      return;
    }
    switch (event.button) {
      case 'edit':
        this.router.navigate(['/unidadeHospitalar', event.selection.id, 'edit']);
        break;
      case 'delete':
        this.confirmDelete(event.selection.id);
        break;
      case 'view':
        this.router.navigate(['/unidadeHospitalar', event.selection.id]);
        break;
    }
  }

  confirmDelete(id: any) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir o registro?',
      accept: () => {
        this.unidadeHospitalarService.delete(id).subscribe(() => {
          this.datatable.refresh(undefined);
          this.pageNotificationService.addDeleteMsg();
        });
      }
    });
  }

  ngOnDestroy() {
    this.breadcrumbService.reset();
  }
}
