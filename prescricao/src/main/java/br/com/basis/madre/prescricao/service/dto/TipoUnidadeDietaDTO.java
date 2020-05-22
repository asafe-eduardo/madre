package br.com.basis.madre.prescricao.service.dto;
import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link br.com.basis.madre.prescricao.domain.TipoUnidadeDieta} entity.
 */
public class TipoUnidadeDietaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 80)
    private String descricao;

    @NotNull
    @Size(max = 3)
    private String sigla;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipoUnidadeDietaDTO tipoUnidadeDietaDTO = (TipoUnidadeDietaDTO) o;
        if (tipoUnidadeDietaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoUnidadeDietaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoUnidadeDietaDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", sigla='" + getSigla() + "'" +
            "}";
    }
}
