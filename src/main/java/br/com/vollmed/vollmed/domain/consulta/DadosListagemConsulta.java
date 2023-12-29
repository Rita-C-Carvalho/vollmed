package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.Medico;
import br.com.vollmed.vollmed.domain.paciente.Paciente;

public record DadosListagemConsulta(Long id, Medico medico, Paciente paciente) {

    public DadosListagemConsulta(Consulta consulta){
        this(consulta.getId(), consulta.getMedico(), consulta.getPaciente());
    }
}
