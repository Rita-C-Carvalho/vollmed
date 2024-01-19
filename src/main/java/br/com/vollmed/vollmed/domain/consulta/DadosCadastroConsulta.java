package br.com.vollmed.vollmed.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroConsulta(


        @Future
        LocalDateTime dataConsulta,

        @NotNull
        FormaDePagamento formaDePagamento,
        Long id_medico,

        @NotNull
        Long id_paciente

        ) {

}
