package br.com.basis.madre.cadastros.service;

import java.util.Optional;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.basis.madre.cadastros.domain.UnidadeHospitalar;
import br.com.basis.madre.cadastros.service.dto.UnidadeHospitalarDTO;
import br.com.basis.madre.cadastros.service.exception.RelatorioException;
import br.com.basis.madre.cadastros.service.exception.UnidadeHospitalarException;

/**
 * Service Implementation for managing UnidadeHospitalar.
 */
public interface UnidadeHospitalarService {

    /**
     * Save a UnidadeHospitalar.
     *
     * @param unidadeHospitalarDTO the entity to save
     * @return the persisted entity
     */
    UnidadeHospitalarDTO save(UnidadeHospitalarDTO unidadeHospitalarDTO) throws UnidadeHospitalarException;

    /**
     * Get all the UnidadeHospitalar.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UnidadeHospitalarDTO> findAll(Optional<String> query, Pageable pageable);

    /**
     * Get the "id" UnidadeHospitalar.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UnidadeHospitalarDTO findOne(Long id);

    /**
     * Delete the "id" UnidadeHospitalar.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


    Page<UnidadeHospitalar> search(String query, Pageable pageable);



    ResponseEntity<InputStreamResource> gerarRelatorioExportacao(String tipoRelatorio) throws RelatorioException;

}


