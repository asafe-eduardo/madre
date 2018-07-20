package br.com.basis.madre.cadastros.web.rest;

import br.com.basis.madre.cadastros.CadastrosbasicosApp;

import br.com.basis.madre.cadastros.domain.Funcionalidade_acao;
import br.com.basis.madre.cadastros.repository.Funcionalidade_acaoRepository;
import br.com.basis.madre.cadastros.service.Funcionalidade_acaoService;
import br.com.basis.madre.cadastros.repository.search.Funcionalidade_acaoSearchRepository;
import br.com.basis.madre.cadastros.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.basis.madre.cadastros.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Funcionalidade_acaoResource REST controller.
 *
 * @see Funcionalidade_acaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CadastrosbasicosApp.class)
public class Funcionalidade_acaoResourceIntTest {

    private static final Integer DEFAULT_ID_FUNCIONALIDADE = 1;
    private static final Integer UPDATED_ID_FUNCIONALIDADE = 2;

    private static final Integer DEFAULT_ID_ACAO = 1;
    private static final Integer UPDATED_ID_ACAO = 2;

    @Autowired
    private Funcionalidade_acaoRepository funcionalidade_acaoRepository;

    @Autowired
    private Funcionalidade_acaoService funcionalidade_acaoService;

    @Autowired
    private Funcionalidade_acaoSearchRepository funcionalidade_acaoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFuncionalidade_acaoMockMvc;

    private Funcionalidade_acao funcionalidade_acao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Funcionalidade_acaoResource funcionalidade_acaoResource = new Funcionalidade_acaoResource(funcionalidade_acaoService);
        this.restFuncionalidade_acaoMockMvc = MockMvcBuilders.standaloneSetup(funcionalidade_acaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Funcionalidade_acao createEntity(EntityManager em) {
        Funcionalidade_acao funcionalidade_acao = new Funcionalidade_acao()
            .id_funcionalidade(DEFAULT_ID_FUNCIONALIDADE)
            .id_acao(DEFAULT_ID_ACAO);
        return funcionalidade_acao;
    }

    @Before
    public void initTest() {
        funcionalidade_acaoSearchRepository.deleteAll();
        funcionalidade_acao = createEntity(em);
    }

    @Test
    @Transactional
    public void createFuncionalidade_acao() throws Exception {
        int databaseSizeBeforeCreate = funcionalidade_acaoRepository.findAll().size();

        // Create the Funcionalidade_acao
        restFuncionalidade_acaoMockMvc.perform(post("/api/funcionalidade-acaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionalidade_acao)))
            .andExpect(status().isCreated());

        // Validate the Funcionalidade_acao in the database
        List<Funcionalidade_acao> funcionalidade_acaoList = funcionalidade_acaoRepository.findAll();
        assertThat(funcionalidade_acaoList).hasSize(databaseSizeBeforeCreate + 1);
        Funcionalidade_acao testFuncionalidade_acao = funcionalidade_acaoList.get(funcionalidade_acaoList.size() - 1);
        assertThat(testFuncionalidade_acao.getId_funcionalidade()).isEqualTo(DEFAULT_ID_FUNCIONALIDADE);
        assertThat(testFuncionalidade_acao.getId_acao()).isEqualTo(DEFAULT_ID_ACAO);

        // Validate the Funcionalidade_acao in Elasticsearch
        Funcionalidade_acao funcionalidade_acaoEs = funcionalidade_acaoSearchRepository.findOne(testFuncionalidade_acao.getId());
        assertThat(funcionalidade_acaoEs).isEqualToIgnoringGivenFields(testFuncionalidade_acao);
    }

    @Test
    @Transactional
    public void createFuncionalidade_acaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = funcionalidade_acaoRepository.findAll().size();

        // Create the Funcionalidade_acao with an existing ID
        funcionalidade_acao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFuncionalidade_acaoMockMvc.perform(post("/api/funcionalidade-acaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionalidade_acao)))
            .andExpect(status().isBadRequest());

        // Validate the Funcionalidade_acao in the database
        List<Funcionalidade_acao> funcionalidade_acaoList = funcionalidade_acaoRepository.findAll();
        assertThat(funcionalidade_acaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkId_funcionalidadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = funcionalidade_acaoRepository.findAll().size();
        // set the field null
        funcionalidade_acao.setId_funcionalidade(null);

        // Create the Funcionalidade_acao, which fails.

        restFuncionalidade_acaoMockMvc.perform(post("/api/funcionalidade-acaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionalidade_acao)))
            .andExpect(status().isBadRequest());

        List<Funcionalidade_acao> funcionalidade_acaoList = funcionalidade_acaoRepository.findAll();
        assertThat(funcionalidade_acaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkId_acaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = funcionalidade_acaoRepository.findAll().size();
        // set the field null
        funcionalidade_acao.setId_acao(null);

        // Create the Funcionalidade_acao, which fails.

        restFuncionalidade_acaoMockMvc.perform(post("/api/funcionalidade-acaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionalidade_acao)))
            .andExpect(status().isBadRequest());

        List<Funcionalidade_acao> funcionalidade_acaoList = funcionalidade_acaoRepository.findAll();
        assertThat(funcionalidade_acaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFuncionalidade_acaos() throws Exception {
        // Initialize the database
        funcionalidade_acaoRepository.saveAndFlush(funcionalidade_acao);

        // Get all the funcionalidade_acaoList
        restFuncionalidade_acaoMockMvc.perform(get("/api/funcionalidade-acaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(funcionalidade_acao.getId().intValue())))
            .andExpect(jsonPath("$.[*].id_funcionalidade").value(hasItem(DEFAULT_ID_FUNCIONALIDADE)))
            .andExpect(jsonPath("$.[*].id_acao").value(hasItem(DEFAULT_ID_ACAO)));
    }

    @Test
    @Transactional
    public void getFuncionalidade_acao() throws Exception {
        // Initialize the database
        funcionalidade_acaoRepository.saveAndFlush(funcionalidade_acao);

        // Get the funcionalidade_acao
        restFuncionalidade_acaoMockMvc.perform(get("/api/funcionalidade-acaos/{id}", funcionalidade_acao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(funcionalidade_acao.getId().intValue()))
            .andExpect(jsonPath("$.id_funcionalidade").value(DEFAULT_ID_FUNCIONALIDADE))
            .andExpect(jsonPath("$.id_acao").value(DEFAULT_ID_ACAO));
    }

    @Test
    @Transactional
    public void getNonExistingFuncionalidade_acao() throws Exception {
        // Get the funcionalidade_acao
        restFuncionalidade_acaoMockMvc.perform(get("/api/funcionalidade-acaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFuncionalidade_acao() throws Exception {
        // Initialize the database
        funcionalidade_acaoService.save(funcionalidade_acao);

        int databaseSizeBeforeUpdate = funcionalidade_acaoRepository.findAll().size();

        // Update the funcionalidade_acao
        Funcionalidade_acao updatedFuncionalidade_acao = funcionalidade_acaoRepository.findOne(funcionalidade_acao.getId());
        // Disconnect from session so that the updates on updatedFuncionalidade_acao are not directly saved in db
        em.detach(updatedFuncionalidade_acao);
        updatedFuncionalidade_acao
            .id_funcionalidade(UPDATED_ID_FUNCIONALIDADE)
            .id_acao(UPDATED_ID_ACAO);

        restFuncionalidade_acaoMockMvc.perform(put("/api/funcionalidade-acaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFuncionalidade_acao)))
            .andExpect(status().isOk());

        // Validate the Funcionalidade_acao in the database
        List<Funcionalidade_acao> funcionalidade_acaoList = funcionalidade_acaoRepository.findAll();
        assertThat(funcionalidade_acaoList).hasSize(databaseSizeBeforeUpdate);
        Funcionalidade_acao testFuncionalidade_acao = funcionalidade_acaoList.get(funcionalidade_acaoList.size() - 1);
        assertThat(testFuncionalidade_acao.getId_funcionalidade()).isEqualTo(UPDATED_ID_FUNCIONALIDADE);
        assertThat(testFuncionalidade_acao.getId_acao()).isEqualTo(UPDATED_ID_ACAO);

        // Validate the Funcionalidade_acao in Elasticsearch
        Funcionalidade_acao funcionalidade_acaoEs = funcionalidade_acaoSearchRepository.findOne(testFuncionalidade_acao.getId());
        assertThat(funcionalidade_acaoEs).isEqualToIgnoringGivenFields(testFuncionalidade_acao);
    }

    @Test
    @Transactional
    public void updateNonExistingFuncionalidade_acao() throws Exception {
        int databaseSizeBeforeUpdate = funcionalidade_acaoRepository.findAll().size();

        // Create the Funcionalidade_acao

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFuncionalidade_acaoMockMvc.perform(put("/api/funcionalidade-acaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionalidade_acao)))
            .andExpect(status().isCreated());

        // Validate the Funcionalidade_acao in the database
        List<Funcionalidade_acao> funcionalidade_acaoList = funcionalidade_acaoRepository.findAll();
        assertThat(funcionalidade_acaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFuncionalidade_acao() throws Exception {
        // Initialize the database
        funcionalidade_acaoService.save(funcionalidade_acao);

        int databaseSizeBeforeDelete = funcionalidade_acaoRepository.findAll().size();

        // Get the funcionalidade_acao
        restFuncionalidade_acaoMockMvc.perform(delete("/api/funcionalidade-acaos/{id}", funcionalidade_acao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean funcionalidade_acaoExistsInEs = funcionalidade_acaoSearchRepository.exists(funcionalidade_acao.getId());
        assertThat(funcionalidade_acaoExistsInEs).isFalse();

        // Validate the database is empty
        List<Funcionalidade_acao> funcionalidade_acaoList = funcionalidade_acaoRepository.findAll();
        assertThat(funcionalidade_acaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFuncionalidade_acao() throws Exception {
        // Initialize the database
        funcionalidade_acaoService.save(funcionalidade_acao);

        // Search the funcionalidade_acao
        restFuncionalidade_acaoMockMvc.perform(get("/api/_search/funcionalidade-acaos?query=id:" + funcionalidade_acao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(funcionalidade_acao.getId().intValue())))
            .andExpect(jsonPath("$.[*].id_funcionalidade").value(hasItem(DEFAULT_ID_FUNCIONALIDADE)))
            .andExpect(jsonPath("$.[*].id_acao").value(hasItem(DEFAULT_ID_ACAO)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Funcionalidade_acao.class);
        Funcionalidade_acao funcionalidade_acao1 = new Funcionalidade_acao();
        funcionalidade_acao1.setId(1L);
        Funcionalidade_acao funcionalidade_acao2 = new Funcionalidade_acao();
        funcionalidade_acao2.setId(funcionalidade_acao1.getId());
        assertThat(funcionalidade_acao1).isEqualTo(funcionalidade_acao2);
        funcionalidade_acao2.setId(2L);
        assertThat(funcionalidade_acao1).isNotEqualTo(funcionalidade_acao2);
        funcionalidade_acao1.setId(null);
        assertThat(funcionalidade_acao1).isNotEqualTo(funcionalidade_acao2);
    }
}
