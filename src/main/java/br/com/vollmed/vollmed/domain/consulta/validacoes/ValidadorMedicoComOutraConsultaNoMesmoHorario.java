package br.com.vollmed.vollmed.domain.consulta.validacoes;

import br.com.vollmed.vollmed.domain.consulta.ConsultaRepository;
import br.com.vollmed.vollmed.domain.consulta.DadosCadastroConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorDeAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosCadastroConsulta dadosConsulta){
        var medicoTemOutraConsultaNoMesmoHorarioENoMesmoDia =
                consultaRepository.existsByMedicoIdAndDataConsulta(dadosConsulta.id_medico(), dadosConsulta.dataConsulta());

        if(medicoTemOutraConsultaNoMesmoHorarioENoMesmoDia){
            throw new RuntimeException("Médico já possui outra consulta agendadade nesse dia e horário.");
        }
    }

}
