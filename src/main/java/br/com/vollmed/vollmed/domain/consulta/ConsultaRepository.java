package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Page<Consulta> findAllByAtivoTrue(Pageable paginacao);


    boolean existsByMedicoIdAndDataConsulta(Long id_medico, LocalDateTime dataConsulta);
}
