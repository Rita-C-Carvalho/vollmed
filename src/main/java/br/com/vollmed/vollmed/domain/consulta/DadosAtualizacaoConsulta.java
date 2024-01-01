package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;

public record DadosAtualizacaoConsulta(
        Long id,

        Long id_medico,

        Long id_paciente,

        String dataConsulta,

        String horaConsulta,

        FormaDePagamento formaDePagamento

) {
}
