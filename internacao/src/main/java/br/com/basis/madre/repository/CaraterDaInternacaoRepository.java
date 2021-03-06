package br.com.basis.madre.repository;

import br.com.basis.madre.domain.CaraterDaInternacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface CaraterDaInternacaoRepository extends JpaRepository<CaraterDaInternacao, Long> {
}
