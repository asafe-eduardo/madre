package br.com.basis.madre.prescricao.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.basis.madre.prescricao.domain.enumeration.UnidadeTempo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A ItemPrescricaoMedicamento.
 */

@Data
@Entity
@Table(name = "item_prescricao_medicamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "madre-prescricao-itemprescricaomedicamento", type="prescricaomedicamento")
public class ItemPrescricaoMedicamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Field(type = FieldType.Keyword)
	private Long id;

	@NotNull
	@Column(name = "id_medicamento", nullable = false)
	@Field(type = FieldType.Long)
	private Long idMedicamento;

	private Long idListaMedicamentos;

	@NotNull
	@DecimalMin(value = "0")
	@Column(name = "dose", precision = 21, scale = 2, nullable = false)
	@Field(type = FieldType.Double)
	private BigDecimal dose;

	@Min(value = 0)
	@Column(name = "frequencia")
	@Field(type = FieldType.Integer)
	private Integer frequencia;

	@Column(name = "todas_vias")
	@Field(type = FieldType.Boolean)
	private Boolean todasVias;

	@Column(name = "bomba_infusao")
	@Field(type = FieldType.Boolean)
	private Boolean bombaInfusao;

	@DecimalMin(value = "0")
	@Column(name = "velocidade_infusao", precision = 21, scale = 2)
	@Field(type = FieldType.Double)
	private BigDecimal velocidadeInfusao;

	@Min(value = 0)
	@Column(name = "tempo_infusao")
	@Field(type = FieldType.Integer)
	private Integer tempoInfusao;

	@Enumerated(EnumType.STRING)
	@Column(name = "unidade_tempo")
	@Field(type = FieldType.Text)
	private UnidadeTempo unidadeTempo;

	@Column(name = "inicio_administracao")
	@Field(type = FieldType.Date)
	private LocalDate inicioAdministracao;

	@Field(type = FieldType.Boolean)
	@Column(name = "condicao_necessaria")
	private Boolean condicaoNecessaria;

	@Size(max = 255)
	@Column(name = "observacao_condicao", length = 255)
	@Field(type = FieldType.Text)
	private String observacaoCondicao;

	@ManyToOne
	@JsonIgnoreProperties("itemPrescricaoMedicamentos")
	private ViasAdministracao viasAdministracao;

	@ManyToOne
	@JsonIgnoreProperties("itemPrescricaoMedicamentos")
	private Diluente diluente;

	@ManyToOne
	@JsonIgnoreProperties("itemPrescricaoMedicamentos")
	private UnidadeInfusao unidadeInfusao;

	@ManyToOne
	@JsonIgnoreProperties("itemPrescricaoMedicamentos")
	private UnidadeDose unidadeDose;

	@ManyToOne
	@JsonIgnoreProperties("itemPrescricaoMedicamentos")
	private TipoAprazamento tipoAprazamento;

	@NotNull
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonIgnoreProperties("itemPrescricaoMedicamentos")
	private PrescricaoMedicamento prescricaoMedicamento;

	@Field(type = FieldType.Object)
	@Transient
	private Medicamento medicamento;
	

	public ItemPrescricaoMedicamento frequencia(Integer frequencia) {
		this.frequencia = frequencia;
		return this;
	}

	public ItemPrescricaoMedicamento idMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
		return this;
	}

	public ItemPrescricaoMedicamento dose(BigDecimal dose) {
		this.dose = dose;
		return this;
	}

	public Boolean isTodasVias() {
		return todasVias;
	}

	public ItemPrescricaoMedicamento todasVias(Boolean todasVias) {
		this.todasVias = todasVias;
		return this;
	}

	public Boolean isBombaInfusao() {
		return bombaInfusao;
	}

	public ItemPrescricaoMedicamento bombaInfusao(Boolean bombaInfusao) {
		this.bombaInfusao = bombaInfusao;
		return this;
	}

	public ItemPrescricaoMedicamento velocidadeInfusao(BigDecimal velocidadeInfusao) {
		this.velocidadeInfusao = velocidadeInfusao;
		return this;
	}

	public ItemPrescricaoMedicamento tempoInfusao(Integer tempoInfusao) {
		this.tempoInfusao = tempoInfusao;
		return this;
	}

	public ItemPrescricaoMedicamento unidadeTempo(UnidadeTempo unidadeTempo) {
		this.unidadeTempo = unidadeTempo;
		return this;
	}

	public ItemPrescricaoMedicamento inicioAdministracao(LocalDate inicioAdministracao) {
		this.inicioAdministracao = inicioAdministracao;
		return this;
	}

	public Boolean isCondicaoNecessaria() {
		return condicaoNecessaria;
	}

	public ItemPrescricaoMedicamento condicaoNecessaria(Boolean condicaoNecessaria) {
		this.condicaoNecessaria = condicaoNecessaria;
		return this;
	}

	public ItemPrescricaoMedicamento observacaoCondicao(String observacaoCondicao) {
		this.observacaoCondicao = observacaoCondicao;
		return this;
	}

	public ItemPrescricaoMedicamento viasAdministracao(ViasAdministracao viasAdministracao) {
		this.viasAdministracao = viasAdministracao;
		return this;
	}

	public ItemPrescricaoMedicamento diluente(Diluente diluente) {
		this.diluente = diluente;
		return this;
	}

	public ItemPrescricaoMedicamento unidadeInfusao(UnidadeInfusao unidadeInfusao) {
		this.unidadeInfusao = unidadeInfusao;
		return this;
	}

	public ItemPrescricaoMedicamento unidadeDose(UnidadeDose unidadeDose) {
		this.unidadeDose = unidadeDose;
		return this;
	}

	public ItemPrescricaoMedicamento prescricaoMedicamento(PrescricaoMedicamento prescricaoMedicamento) {
		this.prescricaoMedicamento = prescricaoMedicamento;
		return this;
	}

}
