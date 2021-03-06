package br.com.basis.madre.farmacia.service.dto;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.basis.madre.farmacia.domain.Medicamento} entity.
 */
public class MedicamentoDTO implements Serializable {

    private Long id;


    private String codigo;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private String concentracao;

    @NotNull
    private Boolean ativo;


    private TipoMedicamentoDTO tipoMedicamentoId;

    @NotNull
    private ApresentacaoDTO apresentacaoId;

    @NotNull
    private UnidadeDTO unidadeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public TipoMedicamentoDTO getTipoMedicamentoId() {
        return tipoMedicamentoId;
    }

    public void setTipoMedicamentoId(TipoMedicamentoDTO tipoMedicamentoId) {
        this.tipoMedicamentoId = tipoMedicamentoId;
    }

    public ApresentacaoDTO getApresentacaoId() {
        return apresentacaoId;
    }

    public void setApresentacaoId(ApresentacaoDTO apresentacaoId) {
        this.apresentacaoId = apresentacaoId;
    }

    public UnidadeDTO getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(UnidadeDTO unidadeId) {
        this.unidadeId = unidadeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicamentoDTO medicamentoDTO = (MedicamentoDTO) o;
        if (medicamentoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medicamentoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedicamentoDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", concentracao='" + getConcentracao() + "'" +
            ", ativo='" + isAtivo() + "'" +
            ", tipoMedicamento=" + getTipoMedicamentoId() +
            ", apresentacao=" + getApresentacaoId() +
            ", unidade=" + getUnidadeId() +
            "}";
    }
}
