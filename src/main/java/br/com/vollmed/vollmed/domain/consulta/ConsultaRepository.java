package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Page<Consulta> findAllByAtivoTrue(Pageable paginacao);


}
