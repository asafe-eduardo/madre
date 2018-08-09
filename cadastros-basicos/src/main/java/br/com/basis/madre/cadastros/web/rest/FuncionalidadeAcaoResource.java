package br.com.basis.madre.cadastros.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.basis.madre.cadastros.domain.FuncionalidadeAcao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.basis.madre.cadastros.service.FuncionalidadeAcaoService;
import br.com.basis.madre.cadastros.web.rest.errors.BadRequestAlertException;
import br.com.basis.madre.cadastros.web.rest.util.HeaderUtil;
import br.com.basis.madre.cadastros.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FuncionalidadeAcao.
 */
@RestController
@RequestMapping("/api")
public class FuncionalidadeAcaoResource {

    private final Logger log = LoggerFactory.getLogger(FuncionalidadeAcaoResource.class);

    private static final String ENTITY_NAME = "funcionalidade_acao";

    private final FuncionalidadeAcaoService funcionalidadeAcaoService;


    public FuncionalidadeAcaoResource(FuncionalidadeAcaoService funcionalidadeAcaoService) {
        this.funcionalidadeAcaoService = funcionalidadeAcaoService;
    }

    /**
     * POST  /funcionalidade-acaos : Create a new funcionalidade_acao.
     *
     * @param funcionalidadeAcao the funcionalidade_acao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new funcionalidade_acao, or with status 400 (Bad Request) if the funcionalidade_acao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/funcionalidade-acaos")
    @Timed
    public ResponseEntity<FuncionalidadeAcao> createFuncionalidadeAcao(@Valid @RequestBody FuncionalidadeAcao funcionalidadeAcao) throws URISyntaxException {
        log.debug("REST request to save FuncionalidadeAcao : {}", funcionalidadeAcao);

        if (funcionalidadeAcao.getId() != null) {
            throw new BadRequestAlertException("A new funcionalidade_acao cannot already have an ID", ENTITY_NAME, "idexists");
        }

        FuncionalidadeAcao result = funcionalidadeAcaoService.save(funcionalidadeAcao);

        return ResponseEntity.created(new URI("/api/funcionalidade-acaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /funcionalidade-acaos : Updates an existing funcionalidade_acao.
     *
     * @param funcionalidadeAcao the funcionalidade_acao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated funcionalidade_acao,
     * or with status 400 (Bad Request) if the funcionalidade_acao is not valid,
     * or with status 500 (Internal Server Error) if the funcionalidade_acao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/funcionalidade-acaos")
    @Timed
    public ResponseEntity<FuncionalidadeAcao> updateFuncionalidadeAcao(@Valid @RequestBody FuncionalidadeAcao funcionalidadeAcao) throws URISyntaxException {
        log.debug("REST request to update FuncionalidadeAcao : {}", funcionalidadeAcao);
        if (funcionalidadeAcao.getId() == null) {
            return createFuncionalidadeAcao(funcionalidadeAcao);
        }
        FuncionalidadeAcao result = funcionalidadeAcaoService.save(funcionalidadeAcao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, funcionalidadeAcao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /funcionalidade-acaos : get all the funcionalidade_acaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of funcionalidade_acaos in body
     */
    @GetMapping("/funcionalidade-acaos")
    @Timed
    public ResponseEntity<List<FuncionalidadeAcao>> getAllFuncionalidadeAcaos(Pageable pageable) {
        log.debug("REST request to get a page of Funcionalidade_acaos");
        Page<FuncionalidadeAcao> page = funcionalidadeAcaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/funcionalidade-acaos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /funcionalidade-acaos/:id : get the "id" funcionalidade_acao.
     *
     * @param id the id of the funcionalidade_acao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the funcionalidade_acao, or with status 404 (Not Found)
     */
    @GetMapping("/funcionalidade-acaos/{id}")
    @Timed
    public ResponseEntity<FuncionalidadeAcao> getFuncionalidadeAcao(@PathVariable Long id) {
        log.debug("REST request to get FuncionalidadeAcao : {}", id);
        FuncionalidadeAcao funcionalidadeAcao = funcionalidadeAcaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(funcionalidadeAcao));
    }

    /**
     * DELETE  /funcionalidade-acaos/:id : delete the "id" funcionalidade_acao.
     *
     * @param id the id of the funcionalidade_acao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/funcionalidade-acaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFuncionalidadeAcao(@PathVariable Long id) {
        log.debug("REST request to delete FuncionalidadeAcao : {}", id);
        funcionalidadeAcaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/funcionalidade-acaos?query=:query : search for the funcionalidade_acao corresponding
     * to the query.
     *
     * @param query    the query of the funcionalidade_acao search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/funcionalidade-acaos")
    @Timed
    public ResponseEntity<List<FuncionalidadeAcao>> searchFuncionalidadeAcaos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Funcionalidade_acaos for query {}", query);
        Page<FuncionalidadeAcao> page = funcionalidadeAcaoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/funcionalidade-acaos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}