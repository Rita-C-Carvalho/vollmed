package br.com.vollmed.vollmed.domain.consulta.validacoes;

import br.com.vollmed.vollmed.domain.consulta.DadosCadastroConsulta;
import br.com.vollmed.vollmed.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorDeAgendamentoDeConsulta{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosCadastroConsulta dadosConsulta){

        var medicoEstaAtivo = medicoRepository.findAtivoByID(dadosConsulta.id_medico());
        if (!medicoEstaAtivo){
           throw new RuntimeException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
