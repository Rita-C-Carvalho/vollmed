package br.com.vollmed.vollmed.domain.consulta.validacoes;

import br.com.vollmed.vollmed.domain.consulta.DadosCadastroConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorDeAgendamentoDeConsulta{

    public void validar(DadosCadastroConsulta dadosConsulta){
        var dataConsulta = dadosConsulta.dataConsulta();

        // CRIANDO A VARIÁVEL DOMINGO E ATRIBUINDO O DIA DA SEMANA DOMINGO PARA A ELA
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        //CRIANDO A VARÍAVEL antesDaAberturaDaClinica E ATRIBUINDO OS HORÁRIOS QUE FOREM MENOR QUE 7 A ELA.
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;

        //CRIANDO A VARÍAVEL depoisDaAberturaDaClinica E ATRIBUINDO OS HORÁRIOS QUE FOREM MAIOR QUE 18 A ELA.
        var depoisDaAberturaDaClinica = dataConsulta.getHour() > 18;

        //VALIDACAO PARA SE O DIA FOR DOMINGO OU OS HORÁRIOS FOREM ANTES OU DEPOIS DO FUNCIONAMENTO DA CLINICA APRESENTAR UMA MENSAGEM INFORMANDO E NÃO DEIXAR AGENDAR A CONSULTA.
        if(domingo || antesDaAberturaDaClinica || depoisDaAberturaDaClinica){
            throw new RuntimeException("Consulta fora do horário de funcionamento da clinica");
        }
    }
}
