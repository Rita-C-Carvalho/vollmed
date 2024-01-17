package br.com.vollmed.vollmed.domain.consulta;

import java.time.LocalDateTime;

public record DadosCadastroConsulta(
        LocalDateTime dataConsulta,
        FormaDePagamento formaDePagamento,
        Long id_medico,
        Long id_paciente

        ) {

}
