package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;

public record DadosAtualizacaoConsulta(
        Long id,

        Medico medico,

        Paciente paciente
) {
}
