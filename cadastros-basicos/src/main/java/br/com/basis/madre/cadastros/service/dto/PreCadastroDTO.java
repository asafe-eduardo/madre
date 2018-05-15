package br.com.basis.madre.cadastros.service.dto;

import br.com.basis.dynamicexports.pojo.ReportObject;
import br.com.basis.madre.cadastros.util.MadreUtil;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PreCadastroDTO implements ReportObject, Serializable {

    private Long id;

    @NotNull
    @Size(min = 1, max = 150)
    private String nomeDoPaciente;

    @Size(max = 150)
    private String nomeSocial;

    @NotNull
    @Size(min = 1, max = 150)
    private String nomeDaMae;

    @NotNull
    private LocalDate dataDeNascimento;

    @Size(min = 15, max = 15)
    private String numCartaoSus;

    @NotNull
    private Boolean ativo;

    private String dataNascimentoString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoPaciente() {
        return nomeDoPaciente;
    }

    public void setNomeDoPaciente(String nomeDoPaciente) {
        this.nomeDoPaciente = nomeDoPaciente;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNumCartaoSus() {
        return numCartaoSus;
    }

    public void setNumCartaoSus(String numCartaoSus) {
        this.numCartaoSus = numCartaoSus;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (o instanceof PreCadastroDTO) {
            return getId().equals(((PreCadastroDTO) o).getId());
        } else if (o instanceof String) {
            return getId().equals(o);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PreCadastro{" +
            "id=" + getId() +
            ", nomeDoPaciente='" + getNomeDoPaciente() + "'" +
            ", nomeSocial='" + getNomeSocial() + "'" +
            ", nomeDaMae='" + getNomeDaMae() + "'" +
            ", dataDeNascimento='" + getDataDeNascimento() + "'" +
            ", numCartaoSus='" + getNumCartaoSus() + "'" +
            ", ativo='" + isAtivo() + "'" +
            "}";
    }

    public String getDataNascimentoString() {
        dataNascimentoString = ObjectUtils.allNotNull(this.dataDeNascimento) ?
            MadreUtil.transformaLocalDateTimeEmString(this.dataDeNascimento) :
            null;
        return dataNascimentoString;
    }

    public void setDataNascimentoString(String dataNascimentoString) {
        this.dataNascimentoString = dataNascimentoString;
    }
}


