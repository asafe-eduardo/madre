import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Response } from '@angular/http';
import { Observable, Subscription } from 'rxjs/Rx';
import { SelectItem } from 'primeng/primeng';
import {NgxMaskModule} from 'ngx-mask';
import { BreadcrumbService } from '../breadcrumb/breadcrumb.service';
import { PageNotificationService } from '@basis/angular-components';
import { UnidadeHospitalar } from './unidade-hospitalar.model';
import { UnidadeHospitalarService } from './unidade-hospitalar.service';
import {FileUploadModule} from 'primeng/fileupload';
import { MessagesModule } from 'primeng/primeng';
import { UploadService } from '../upload/upload.service';
import { FileUpload } from 'primeng/primeng';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'jhi-unidade-hospitalar-form',
  templateUrl: './unidade-hospitalar-form.component.html'
})
export class UnidadeHospitalarFormComponent implements OnInit, OnDestroy {
  unidadeHospitalar: UnidadeHospitalar;
  isSaving: boolean;
  loading: boolean;
  isEdit = false;
  private routeSub: Subscription;
  logo: File;

  rForm: FormGroup;
  post:any;
  siglaValid:string = '';
  nomeValid:string = '';
  titleAlert:string = 'Campo obrigatório!';

  @ViewChild('fileInput') fileInput: FileUpload;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private breadcrumbService: BreadcrumbService,
    private pageNotificationService: PageNotificationService,
    private unidadeHospitalarService: UnidadeHospitalarService,
    private uploadService: UploadService,
    private fb: FormBuilder,
    ) 
  {
    this.rForm = fb.group({
      'nomeValid' : [null, Validators.required],
      'siglaValid' : [null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(10)])],
      'enderecoValid' : [null, Validators.required],
      'cnpjValid' : [null, Validators.required],
    });
  }

  ngOnInit() {
    this.isSaving = false;
    this.routeSub = this.route.params.subscribe(params => {
      let title = 'Cadastrar';
      this.unidadeHospitalar = new UnidadeHospitalar();
      this.unidadeHospitalar.ativo = true;
      if (params['id']) {
        this.isEdit = true;
        this.unidadeHospitalarService.find(params['id']).subscribe(unidadeHospitalar =>{
           this.unidadeHospitalar = unidadeHospitalar;
           this.getFile();
          });
        title = 'Editar';
      }
      this.breadcrumbService.setItems([
        { label: 'Unidade de Saúde', routerLink: '/unidadeHospitalar' },
        { label: title }
      ]);
    });
  }

save(post) {
  this.siglaValid = post.siglaValid;
  this.nomeValid = post.nomeValid;
  this.isSaving = true;
  if (this.unidadeHospitalar.id !== undefined) {
    this.unidadeHospitalarService.find(this.unidadeHospitalar.id).subscribe(response => {

      if(this.logo !== undefined) {
        this.uploadService.uploadFile(this.logo).subscribe(response => {
          this.unidadeHospitalar.logoId = JSON.parse(response["_body"]).id;
          this.subscribeToSaveResponse(this.unidadeHospitalarService.update(this.unidadeHospitalar));
        })
      } else {
          this.subscribeToSaveResponse(this.unidadeHospitalarService.update(this.unidadeHospitalar));
      }
    })
  } else {
      if(this.logo !== undefined) {
        this.uploadService.uploadFile(this.logo).subscribe(response => {
          this.unidadeHospitalar.logoId = JSON.parse(response["_body"]).id;
          this.subscribeToSaveResponse(this.unidadeHospitalarService.create(this.unidadeHospitalar));
        })
    } else {
      this.subscribeToSaveResponse(this.unidadeHospitalarService.create(this.unidadeHospitalar));
    }
  }
}
  private subscribeToSaveResponse(result: Observable<UnidadeHospitalar>) {
    result.subscribe((res: UnidadeHospitalar) => {
      this.isSaving = false;
      this.router.navigate(['/unidadeHospitalar']);
      this.addConfirmationMessage();
    }, (res: Response) => {
      this.isSaving = false;
      if (res.headers.toJSON()['x-cadastrosbasicosapp-errordataexists'] != null) {
        this.pageNotificationService.addErrorMessage('Registro já cadastrado');
      } else {
        this.pageNotificationService.addErrorMessage('CNPJ Inválido');
      }
    });
  }

  uploadFile(event){
    this.logo = event.files[0];
  }

  private addConfirmationMessage() {
    if (this.isEdit) {
      this.pageNotificationService.addUpdateMsg();
    } else {
      this.pageNotificationService.addCreateMsg();
    }
  }
  private addErrorMessage() {
     if (this.isEdit) {
      this.pageNotificationService.addUpdateMsg();
    } else {
      this.pageNotificationService.addErrorMessage('Dados inválidos');
    }
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
    this.breadcrumbService.reset();
  }
  getFile() {
    this.loading = true;
    this.uploadService.getFile(this.unidadeHospitalar.logoId).subscribe(response => {

      let fileInfo;
      this.uploadService.getFileInfo(this.unidadeHospitalar.logoId).subscribe(response => {
        fileInfo = response;

        this.fileInput.files.push(new File([response['_body']], fileInfo['originalName']));
        this.loading = false;
      });
    });
  }

  getFileInfo() {
    return this.uploadService.getFile(this.unidadeHospitalar.logoId).subscribe(response => {
      return response;
    });
  }
}