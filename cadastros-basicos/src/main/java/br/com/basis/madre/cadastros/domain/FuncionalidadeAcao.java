package br.com.basis.madre.cadastros.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * A FuncionalidadeAcao.
 */
@Entity
@Table(name = "funcionalidade_acao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "funcionalidade_acao")
public class FuncionalidadeAcao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_funcionalidade", nullable = false)
    private Integer id_funcionalidade;

    @NotNull
    @Column(name = "id_acao", nullable = false)
    private Integer id_acao;

    @OneToOne(mappedBy="funcionalidade_acao", fetch=FetchType.LAZY)
    private PerfilFuncionalidadeAcao perfil_funcionalidade_acao;

    /**
     * @return the perfil_funcionalidade_acao
     */
    public PerfilFuncionalidadeAcao getPerfil_funcionalidade_acao() {
        return perfil_funcionalidade_acao;
    }

    /**
     * @param perfil_funcionalidade_acao the perfil_funcionalidade_acao to set
     */
    public void setPerfil_funcionalidade_acao(PerfilFuncionalidadeAcao perfil_funcionalidade_acao) {
        this.perfil_funcionalidade_acao = perfil_funcionalidade_acao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getId_funcionalidade() {
        return id_funcionalidade;
    }

    public FuncionalidadeAcao id_funcionalidade(Integer id_funcionalidade) {
        this.id_funcionalidade = id_funcionalidade;
        return this;
    }

    public void setId_funcionalidade(Integer id_funcionalidade) {
        this.id_funcionalidade = id_funcionalidade;
    }

    public Integer getId_acao() {
        return id_acao;
    }

    public FuncionalidadeAcao id_acao(Integer id_acao) {
        this.id_acao = id_acao;
        return this;
    }

    public void setId_acao(Integer id_acao) {
        this.id_acao = id_acao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FuncionalidadeAcao funcionalidade_acao = (FuncionalidadeAcao) o;
        if (funcionalidade_acao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), funcionalidade_acao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FuncionalidadeAcao{" +
            "id=" + getId() +
            ", id_funcionalidade=" + getId_funcionalidade() +
            ", id_acao=" + getId_acao() +
            "}";
    }
}
