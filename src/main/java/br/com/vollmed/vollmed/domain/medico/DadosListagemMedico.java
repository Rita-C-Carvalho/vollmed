package br.com.vollmed.vollmed.domain.medico;

import br.com.vollmed.vollmed.domain.consulta.Consulta;

public record DadosListagemMedico (Long id, String nome, String email, String crm, Especialidade especialidade){

    public DadosListagemMedico (Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }


}
