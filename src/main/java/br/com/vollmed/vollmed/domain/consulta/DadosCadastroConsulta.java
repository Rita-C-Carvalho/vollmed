package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;

public record DadosCadastroConsulta(

        Medico mediico,

        Paciente paciente
) {
}
