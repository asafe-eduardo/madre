package br.com.basis.madre.prescricao.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link br.com.basis.madre.prescricao.domain.PrescricaoDieta} entity.
 */
public class PrescricaoDietaDTO implements Serializable {

    private Long id;

    private Long idPaciente;

    @Size(max = 255)
    private String observacao;
    
    private Set<ItemPrescricaoDietaDTO> itemPrescricaoDietas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    

    public Set<ItemPrescricaoDietaDTO> getItemPrescricaoDietas() {
		return itemPrescricaoDietas;
	}

	public void setItemPrescricaoDietas(Set<ItemPrescricaoDietaDTO> itemPrescricaoDietas) {
		this.itemPrescricaoDietas = itemPrescricaoDietas;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrescricaoDietaDTO prescricaoDietaDTO = (PrescricaoDietaDTO) o;
        if (prescricaoDietaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prescricaoDietaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "PrescricaoDietaDTO [id=" + id + ", idPaciente=" + idPaciente + ", observacao=" + observacao
				+ ", itemPrescricaoDietas=" + itemPrescricaoDietas + "]";
	}

    
}
