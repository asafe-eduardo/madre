import {
    AfterViewInit,
    Component,
    ElementRef,
    NgZone,
    OnDestroy,
    OnInit,
    Renderer2,
    ViewChild,
} from '@angular/core';
import { MenusService } from '@nuvem/primeng-components';
import { ScrollPanel } from 'primeng';

enum MenuOrientation {
    STATIC,
    OVERLAY,
    SLIM,
    HORIZONTAL,
}

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
})
export class AppComponent implements AfterViewInit, OnDestroy, OnInit {
    layoutCompact = true;

    layoutMode: MenuOrientation = MenuOrientation.STATIC;

    darkMenu = false;

    profileMode = 'inline';

    rotateMenuButton: boolean;

    topbarMenuActive: boolean;

    rightPanelActive: boolean;

    rightPanelClick: boolean;

    layoutContainer: HTMLDivElement;

    layoutMenuScroller: HTMLDivElement;

    menuClick: boolean;

    topbarItemClick: boolean;

    activeTopbarItem: any;

    menuHoverActive: boolean;

    @ViewChild('layoutContainer', { static: true }) layourContainerViewChild: ElementRef;

    @ViewChild('scrollPanel', { static: true }) layoutMenuScrollerViewChild: ScrollPanel;

    rippleInitListener: any;

    rippleMouseDownListener: any;

    constructor(
        public renderer2: Renderer2,
        public zone: NgZone,
        public menuService: MenusService,
    ) {}

    ngOnInit() {
        this.zone.runOutsideAngular(() => {
            this.bindRipple();
        });

        this.menuService.itens = [
            { label: 'Dashboard', icon: 'dashboard', routerLink: ['/'] },
            {
                label: 'Pacientes',
                icon: 'person_add',
                items: [
                    {
                        label: 'Pesquisa de Paciente',
                        icon: 'add',
                        routerLink: ['/paciente/list'],
                    },
                    {
                        label: 'Cadastro de Paciente',
                        icon: 'add',
                        routerLink: ['/paciente/save'],
                    }
                ],
            },
            {
                label: 'Triagem',
                icon: 'local_hospital',
                items: [
                    {
                        label: 'Pré-Cadastro',
                        icon: 'view_headline',
                        items: [
                            {
                                label: 'Listar Pré-Cadastro',
                                icon: 'add',
                                routerLink: ['pacientes/lista-pre-cadastro'],
                            },
                            {
                                label: 'Incluir Pré-Cadastro',
                                icon: 'add',
                                routerLink: ['pacientes/pre-cadastro'],
                            },
                        ],
                    },
                    {
                        label: 'Triagem',
                        icon: 'add',
                        routerLink: ['pacientes/triagem'],
                    },
                ],
            },
            {
                label: 'Marcar Consultas',
                icon: 'watch_later',
                items: [
                    {
                        label: 'Listar Consultas',
                        icon: 'add',
                        routerLink: ['consulta/listar-consultas'],
                    },
                    {
                        label: 'Calendário',
                        icon: 'add',
                        routerLink: ['consulta/consulta-calendario'],
                    },
                    {
                        label: 'Emergência',
                        icon: 'add',
                        routerLink: ['consulta/emergencia'],
                    },
                ],
            },

            {
                label: 'Prescrição',
                icon: 'assignment_ind',
                items: [
                    {
                        label: 'Médica',
                        icon: 'add',
                        items: [
                            {
                                label: 'Prescrever',
                                icon: 'add',
                                routerLink: ['/prescricao-medica'],
                            },
                        ],
                    },
                ],
            },
            {
                label: 'Internação',
                icon: 'airline_seat_individual_suite',
                items: [
                    {
                        label: 'Solicitar Internação',
                        icon: 'add',
                        routerLink: ['internacao/lista-de-pacientes'],
                    },
                    {
                        label: 'Internar Paciente',
                        icon: 'add',
                        routerLink: ['internacao/solicitacoes-de-internacao'],
                    },
                    {
                        label: 'Cadastrar Unidade',
                        icon: 'add',
                        routerLink: ['internacao/cadastro-unidades'],
                    },
                    {
                        label: 'Cadastrar Clínicas',
                        icon: 'add',
                        routerLink: ['internacao/cadastro-clinicas'],
                    },
                    {
                        label: 'Leitos',
                        icon: 'add',
                        items: [
                            {
                                label: 'Calendário',
                                icon: 'add',
                                routerLink: ['internacao/calendario-leito'],
                            },
                            {
                                label: 'Reservar Leito',
                                icon: 'add',
                                routerLink: ['internacao/reserva-de-leito'],
                            },
                            {
                                label: 'Bloquear Leito',
                                icon: 'add',
                                routerLink: ['internacao/bloqueio-de-leito'],
                            },
                            {
                                label: 'Liberar Leito',
                                icon: 'add',
                                routerLink: ['internacao/liberacao-de-leito'],
                            },
                        ],
                    },
                ],
            },
            {
                label: 'Farmacia',
                icon: 'local_pharmacy',
                items: [
                    {
                        label: 'Dispensação medica',
                        icon: 'add',
                        routerLink: ['/farmacia'],
                    },
                    {
                        label: 'Cadastro de Medicamento',
                        icon: 'add',
                        routerLink: ['/cadastrar-medicamento'],
                    },
                    {
                        label: 'Medicamentos',
                        icon: 'add',
                        routerLink: ['/medicamentos'],
                    },
                ],
            },
            {
                label: 'Suprimentos',
                icon: 'local_shipping',
                items: [
                    {
                        label: 'Almoxarifado',
                        icon: 'add',
                        items: [
                            {
                                label: 'Recebimento',
                                icon: 'add',
                                routerLink: ['/suprimentos/recebimentos/novo'],
                            },
                            {
                                label: 'Consultar Estoque',
                                icon: 'add',
                                routerLink: ['/suprimentos/consulta-estoque'],
                            },
                            {
                                label: 'Documentos Fiscais',
                                icon: 'add',
                                items: [
                                    {
                                        label: 'Nota Fiscal',
                                        icon: 'add',
                                        routerLink: [
                                            '/suprimentos/documentos-fiscais/notas-fiscais/nova',
                                        ],
                                    },
                                ],
                            },
                            {
                                label: 'Transferências',
                                icon: 'add',
                                items: [
                                    {
                                        label: 'Automáticas',
                                        icon: 'add',
                                        routerLink: ['/suprimentos/transferencias-automaticas'],
                                    },
                                    {
                                        label: 'Efetivação',
                                        icon: 'add',
                                        routerLink: [
                                            '/suprimentos/transferencias-automaticas/nao-efetivadas',
                                        ],
                                    },
                                ],
                            },
                            {
                                label: 'Requisições',
                                icon: 'add',
                                routerLink: ['/suprimentos/requisicoes-materiais'],
                                items: [
                                    {
                                        label: 'Efetivar Requisição',
                                        icon: 'add',
                                        routerLink: [
                                            '/suprimentos/requisicoes-materiais/nao-efetivadas',
                                        ],
                                    },
                                ],
                            },
                            {
                                label: 'Inclusão Saldo de Estoque',
                                icon: 'add',
                                routerLink: ['/suprimentos/inclusao-saldo-estoque'],
                            },
                        ],
                    },
                ],
            },
        ];
    }

    bindRipple() {
        this.rippleInitListener = this.init.bind(this);
        document.addEventListener('DOMContentLoaded', this.rippleInitListener);
    }

    init() {
        this.rippleMouseDownListener = this.rippleMouseDown.bind(this);
        document.addEventListener('mousedown', this.rippleMouseDownListener, false);
    }

    rippleMouseDown(e) {
        for (let target = e.target; target && target !== this; target = target['parentNode']) {
            if (!this.isVisible(target)) {
                continue;
            }

            // Element.matches() -> https://developer.mozilla.org/en-US/docs/Web/API/Element/matches
            if (this.selectorMatches(target, '.ripplelink, .ui-button')) {
                const element = target;
                this.rippleEffect(element, e);
                break;
            }
        }
    }

    selectorMatches(el, selector) {
        const p = Element.prototype;
        const f =
            p['matches'] ||
            p['webkitMatchesSelector'] ||
            p['mozMatchesSelector'] ||
            p['msMatchesSelector'] ||
            function (s) {
                return [].indexOf.call(document.querySelectorAll(s), this) !== -1;
            };
        return f.call(el, selector);
    }

    isVisible(el) {
        return !!(el.offsetWidth || el.offsetHeight);
    }

    rippleEffect(element, e) {
        if (element.querySelector('.ink') === null) {
            const inkEl = document.createElement('span');
            this.addClass(inkEl, 'ink');

            if (this.hasClass(element, 'ripplelink') && element.querySelector('span')) {
                element
                    .querySelector('span')
                    .insertAdjacentHTML('afterend', "<span class='ink'></span>");
            } else {
                element.appendChild(inkEl);
            }
        }

        const ink = element.querySelector('.ink');
        this.removeClass(ink, 'ripple-animate');

        if (!ink.offsetHeight && !ink.offsetWidth) {
            const d = Math.max(element.offsetWidth, element.offsetHeight);
            ink.style.height = d + 'px';
            ink.style.width = d + 'px';
        }

        const x = e.pageX - this.getOffset(element).left - ink.offsetWidth / 2;
        const y = e.pageY - this.getOffset(element).top - ink.offsetHeight / 2;

        ink.style.top = y + 'px';
        ink.style.left = x + 'px';
        ink.style.pointerEvents = 'none';
        this.addClass(ink, 'ripple-animate');
    }
    hasClass(element, className) {
        if (element.classList) {
            return element.classList.contains(className);
        } else {
            return new RegExp('(^| )' + className + '( |$)', 'gi').test(element.className);
        }
    }

    addClass(element, className) {
        if (element.classList) {
            element.classList.add(className);
        } else {
            element.className += ' ' + className;
        }
    }

    removeClass(element, className) {
        if (element.classList) {
            element.classList.remove(className);
        } else {
            element.className = element.className.replace(
                new RegExp('(^|\\b)' + className.split(' ').join('|') + '(\\b|$)', 'gi'),
                ' ',
            );
        }
    }

    getOffset(el) {
        const rect = el.getBoundingClientRect();

        return {
            top:
                rect.top +
                (window.pageYOffset ||
                    document.documentElement.scrollTop ||
                    document.body.scrollTop ||
                    0),
            left:
                rect.left +
                (window.pageXOffset ||
                    document.documentElement.scrollLeft ||
                    document.body.scrollLeft ||
                    0),
        };
    }

    unbindRipple() {
        if (this.rippleInitListener) {
            document.removeEventListener('DOMContentLoaded', this.rippleInitListener);
        }
        if (this.rippleMouseDownListener) {
            document.removeEventListener('mousedown', this.rippleMouseDownListener);
        }
    }

    ngAfterViewInit() {
        this.layoutContainer = <HTMLDivElement>this.layourContainerViewChild.nativeElement;
        setTimeout(() => {
            this.layoutMenuScrollerViewChild.moveBar();
        }, 100);
    }

    onLayoutClick() {
        if (!this.topbarItemClick) {
            this.activeTopbarItem = null;
            this.topbarMenuActive = false;
        }

        if (!this.menuClick) {
            if (this.menuService.isHorizontal() || this.menuService.isSlim()) {
                this.menuService.resetMenu = true;
            }

            if (this.menuService.overlayMenuActive || this.menuService.staticMenuMobileActive) {
                this.hideOverlayMenu();
            }

            this.menuService.menuHoverActive = false;
        }

        if (!this.rightPanelClick) {
            this.rightPanelActive = false;
        }

        this.topbarItemClick = false;
        this.menuClick = false;
        this.rightPanelClick = false;
    }

    onMenuButtonClick(event) {
        this.menuClick = true;
        this.rotateMenuButton = !this.rotateMenuButton;
        this.topbarMenuActive = false;

        if (this.layoutMode === MenuOrientation.OVERLAY) {
            this.menuService.overlayMenuActive = !this.menuService.overlayMenuActive;
        } else {
            if (this.isDesktop()) {
                this.menuService.staticMenuDesktopInactive = !this.menuService
                    .staticMenuDesktopInactive;
            } else {
                this.menuService.staticMenuMobileActive = !this.menuService.staticMenuMobileActive;
            }
        }

        event.preventDefault();
    }

    onMenuClick($event) {
        this.menuClick = true;
    }

    onTopbarMenuButtonClick(event) {
        this.topbarItemClick = true;
        this.topbarMenuActive = !this.topbarMenuActive;

        this.hideOverlayMenu();

        event.preventDefault();
    }

    onTopbarItemClick(event, item) {
        this.topbarItemClick = true;

        if (this.activeTopbarItem === item) {
            this.activeTopbarItem = null;
        } else {
            this.activeTopbarItem = item;
        }

        event.preventDefault();
    }

    onTopbarSubItemClick(event) {
        event.preventDefault();
    }

    onRightPanelButtonClick(event) {
        this.rightPanelClick = true;
        this.rightPanelActive = !this.rightPanelActive;
        event.preventDefault();
    }

    onRightPanelClick() {
        this.rightPanelClick = true;
    }

    hideOverlayMenu() {
        this.rotateMenuButton = false;
        this.menuService.overlayMenuActive = false;
        this.menuService.staticMenuMobileActive = false;
    }

    isTablet() {
        const width = window.innerWidth;
        return width <= 1024 && width > 640;
    }

    isDesktop() {
        return window.innerWidth > 1024;
    }

    isMobile() {
        return window.innerWidth <= 640;
    }

    ngOnDestroy() {
        this.unbindRipple();
    }
}
