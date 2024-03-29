package br.com.vollmed.vollmed.domain.consulta;

import br.com.vollmed.vollmed.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosListagemConsulta(Long id, Long id_medico, String nomeMedico, String crmMedico, Especialidade especialidade,
                                    Long id_paciente, String nomePaciente, String  cpfPaciente,
                                    LocalDateTime dataConsulta, FormaDePagamento formaDePagamento){

    public DadosListagemConsulta(Consulta consulta){
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getMedico().getNome(),
                consulta.getMedico().getCrm(),
                consulta.getMedico().getEspecialidade(),
                consulta.getPaciente().getId(),
                consulta.getPaciente().getNome(),
                consulta.getPaciente().getCpf(),
                consulta.getDataConsulta(),
                consulta.getFormaDePagamento());
    }

}
