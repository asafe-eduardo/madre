package br.com.basis.madre.cadastros.service.mapper;

import org.mapstruct.Mapper;

import br.com.basis.madre.cadastros.domain.PreCadastro;
import br.com.basis.madre.cadastros.service.dto.PreCadastroDTO;

/**
 * Mapper for the entity PreCadastro and its DTO PreCadastroDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PreCadastroMapper extends EntityMapper<PreCadastroDTO, PreCadastro> {



    default PreCadastro fromId(Long id) {
        if (id == null) {
            return null;
        }
        PreCadastro preCadastro = new PreCadastro();
        preCadastro.setId(id);
        return preCadastro;
    }
}