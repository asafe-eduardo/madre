package br.com.basis.madre.prescricao.web.rest;

import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.javafaker.Faker;

import br.com.basis.madre.prescricao.domain.Paciente;
import br.com.basis.madre.prescricao.repository.search.PacienteRepositorySearch;
import br.com.basis.madre.prescricao.service.PacienteService;
import io.github.jhipster.web.util.PaginationUtil;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteResource {
	
	private final Logger log = LoggerFactory.getLogger(PacienteResource.class);
	
	@Autowired
	private PacienteRepositorySearch pacienteRepositorySearch;
	
	private PacienteService pacienteService;
			
	private Faker faker = new Faker(new Locale("pt-BR"));
	
	@GetMapping()
	public Page<Paciente> listar(@PageableDefault(size = 10)Pageable pageable){
		Page<Paciente> pacientesPage = pacienteRepositorySearch.findAll(pageable);
		
		List<Paciente> paciente = pacientesPage.getContent();
		
		Page<Paciente> pacienteModelPage = new PageImpl<>(paciente, pageable,
				pacientesPage.getTotalElements());
		
		return pacienteModelPage;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void criarPaciente(Paciente paciente) {
		pacienteRepositorySearch.save(paciente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
		Optional<Paciente> paciente = pacienteRepositorySearch.findById(id);
		if (paciente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(paciente.get());
	}
	
	
	@GetMapping("/pacientes")
    public ResponseEntity<List<Paciente>> obterTodosPacientes(Pageable pageable) {
		log.debug("Request REST para obter uma página de pacientes.");
        Page<Paciente> page = pacienteService.obterTodosPacientes(pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
