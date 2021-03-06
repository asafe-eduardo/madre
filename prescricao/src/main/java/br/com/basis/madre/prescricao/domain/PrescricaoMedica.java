package br.com.basis.madre.prescricao.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A PrescricaoMedica.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
@Table(name = "prescricao_medica")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "madre-prescricao-prescricaomedica", type = "prescricaomedica")
public class PrescricaoMedica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Field(type = FieldType.Keyword)
	private Long id;

	@NotNull
	@Column(name = "id_leito")
	@Field(type = FieldType.Long)
	private Long idLeito;

	@NotNull
	@Column(name = "id_unidade_funcional")
	@Field(type = FieldType.Long)
	private Long idUnidadeFuncional;

	@NotNull
	@Column(name = "id_atendimento")
	@Field(type = FieldType.Long)
	private Long idAtendimento;

	@Column(name = "data_prescricao")
	@Field(type = FieldType.Date)
	private LocalDate dataPrescricao;
	
	@Column(name = "id_paciente")
	@Field(type = FieldType.Long)
	private Long idPaciente;

	@Size(max = 255)
	@Column(name = "observacao", length = 255)
	@Field(type = FieldType.Text)
	private String observacao;

	public PrescricaoMedica idLeito(Long idLeito) {	
		this.idLeito = idLeito;
		return this;
	}

	public PrescricaoMedica idUnidadeFuncional(Long idUnidadeFuncional) {
		this.idUnidadeFuncional = idUnidadeFuncional;
		return this;
	}

	public PrescricaoMedica idAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
		return this;
	}

	public PrescricaoMedica dataPrescricao(LocalDate dataPrescricao) {
		this.dataPrescricao = dataPrescricao;
		return this;
	}
	
	public PrescricaoMedica idPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
		return this;
	}

	public PrescricaoMedica observacao(String observacao) {
		this.observacao = observacao;
		return this;
	}

}
