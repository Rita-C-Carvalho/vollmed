package br.com.vollmed.vollmed.domain.consulta.validacoes;

import br.com.vollmed.vollmed.domain.consulta.DadosCadastroConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorDeAgendamentoDeConsulta{
    public void validar(DadosCadastroConsulta dadosConsulta){
        var horaConsulta = dadosConsulta.dataConsulta();
        var agora = LocalDateTime.now();

        //CRIANDO A VARIÁVEL diferencaEntreMinutos E ATRIBUINDO A ELA A DIFERENÇA DE MINUTOS ENTRE A DATA DA CONSULTA E A DATA DE AGORA.
        var diferencaEntreMinutos = Duration.between(agora, horaConsulta).toMinutes();

        if (diferencaEntreMinutos < 30){
            throw new RuntimeException("As coinsultas devem ser agendadas com no mímino 30 minutos de antecendência.");
        }
    }
}
