package br.com.vollmed.vollmed.domain.consulta.validacoes;

import br.com.vollmed.vollmed.domain.consulta.DadosCadastroConsulta;
import br.com.vollmed.vollmed.domain.medico.MedicoRepository;
import br.com.vollmed.vollmed.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorDeAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosCadastroConsulta dadosConsulta){

        var pacienteEstaAtivo = pacienteRepository.findAtivoByID(dadosConsulta.id_paciente());
        if (!pacienteEstaAtivo){
           throw new RuntimeException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
