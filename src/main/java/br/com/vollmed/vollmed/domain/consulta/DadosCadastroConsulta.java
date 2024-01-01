package br.com.vollmed.vollmed.domain.consulta;

public record DadosCadastroConsulta(
        String dataConsulta,
        FormaDePagamento formaDePagamento,
        String horaConsulta,
        Long id_medico,
        Long id_paciente

        ) {

}
