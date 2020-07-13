package br.com.basis.madre.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link br.com.basis.madre.domain.EstadoCivil} entity.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class EstadoCivilDTO implements Serializable {

    private Long id;

    @NotNull
    private String valor;


}
